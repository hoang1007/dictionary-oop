package com.gryffindor.backend.api;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteBatch;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.internal.NonNull;
import com.gryffindor.DictionaryApplication;
import com.gryffindor.backend.entities.Translation;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.backend.utils.TextUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FireStore {
  private static Firestore database;
  private static final long TIMEOUT = 3;

  static {
    try {
      GoogleCredentials credentials = GoogleCredentials
          .fromStream(DictionaryApplication.INSTANCE.config.getGoogleServiceStream());

      FirebaseOptions options = FirebaseOptions.builder().setCredentials(credentials).build();

      FirebaseApp.initializeApp(options);
      database = FirestoreClient.getFirestore();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Thêm một từ mới và database.
   * 
   * @param word từ muốn thêm
   * @throws InterruptedException nếu luồng bị gián đoạn khi đang đợi request
   * @throws ExecutionException   nếu máy chú tính toán ném ra một ngoại lệ
   */
  public static void add(Word word) throws InterruptedException, ExecutionException {
    ApiFuture<WriteResult> future = database.collection("dictionary")
        .document(word.getWordTarget()).set(word);

    System.out.println("added to database " + future.get().getUpdateTime());
  }

  /**
   * Thêm một danh sách các từ vào database.
   * 
   * @param words danh sách muốn thêm
   */
  public static void add(List<Word> words) {
    final int Max_Request = 400;

    for (int itr = 0; itr < words.size();) {
      boolean start = true;
      WriteBatch batch = database.batch();

      for (; itr < words.size() && itr % Max_Request != 0 || start; itr++) {
        Word word = words.get(itr);
        DocumentReference docRef = database.collection("dictionary").document(word.getWordTarget());
        batch.set(docRef, word);
        start = false;
        System.out.print(itr + " ");
      }

      ApiFuture<List<WriteResult>> future = batch.commit();

      try {
        for (WriteResult result : future.get()) {
          System.out.println("Update time : " + result.getUpdateTime());
        }
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Tìm và trả về một từ trong database.
   * @param wordTarget từ muốn tìm
   * @return trả về từ muốn tìm nếu có trong database
   *        trả về null nếu không tìm thấy
   * @throws InterruptedException nếu luồng thực thi bị gián đoạn khi đang đợi request
   * @throws ExecutionException nếu máy chủ tính toán ném ra một ngoại lệ
   * @throws TimeoutException thời gian request quá lâu
   * @throws NullPointerException không tìm thấy từ trong firebase
   */
  public static Word find(String wordTarget) 
      throws InterruptedException, ExecutionException, TimeoutException, NullPointerException {
    ApiFuture<DocumentSnapshot> future = database.collection("dictionary")
        .document(wordTarget).get();

    Word wordFound = future.get(TIMEOUT, TimeUnit.SECONDS).toObject(Word.class);

    if (wordFound == null) {
      throw new NullPointerException("Not found");
    }

    return wordFound;
  }

  /**
   * Xóa một bản dịch trong database.
   * @param word từ chứa bản dịch
   * @param translation bản dịch muốn xóa
   * @throws InterruptedException nếu luồng thực thi bị gián đoạn khi đang đợi request
   * @throws ExecutionException nếu máy chủ tính toán ném ra một ngoại lệ
   * @throws TimeoutException thời gian request quá lâu
   */
  public static void deleteTranslation(Word word, Translation translation)
      throws InterruptedException, ExecutionException, TimeoutException {
    DocumentReference docRef = database.collection("dictionary").document(word.getWordTarget());

    System.out.println(docRef.update("translations", FieldValue.arrayRemove(translation))
        .get(TIMEOUT, TimeUnit.MINUTES).getUpdateTime());
  }

  /**
   * Cập nhật bản dịch trong database.
   * @param word từ chứa bản dịch
   * @param oldTrans bản dịch cũ
   * @param newTrans bản dịch mới
   * @throws InterruptedException nếu luồng thực thi bị gián đoạn khi đang đợi request
   * @throws ExecutionException nếu máy chủ tính toán ném ra một ngoại lệ
   * @throws TimeoutException thời gian request quá lâu
   */
  public static void updateTranslation(Word word, Translation oldTrans, 
                                                  @NonNull Translation newTrans)
      throws InterruptedException, ExecutionException, TimeoutException {
    DocumentReference docRef = database.collection("dictionary").document(word.getWordTarget());
    WriteBatch batch = database.batch();

    if (oldTrans.getWordExplain().equals(TextUtils.empty()) || oldTrans == null) {
      System.out.println("Old trans is null or empty");
    } else {
      batch.update(docRef, "translations", FieldValue.arrayRemove(oldTrans));
    }

    batch.update(docRef, "translations", FieldValue.arrayUnion(newTrans));

    System.out.println("Updated " + batch.commit().get(TIMEOUT, TimeUnit.SECONDS));
  }

  /**
   * Thêm một bản dịch trong database.
   * @param word từ chứa bản dịch
   * @param translation bản dịch muốn thêm
   * @throws InterruptedException nếu luồng thực thi bị gián đoạn khi đang đợi request
   * @throws ExecutionException nếu máy chủ tính toán ném ra một ngoại lệ
   * @throws TimeoutException thời gian request quá lâu
   */
  public static void addTranslation(Word word, Translation translation)
      throws InterruptedException, ExecutionException, TimeoutException {
    DocumentReference docRef = database.collection("dictionary").document(word.getWordTarget());

    System.out.println(docRef.update("translations", FieldValue.arrayUnion(translation))
        .get(TIMEOUT, TimeUnit.MINUTES).getUpdateTime());
  }
}