package com.gryffindor.backend.api;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
import com.gryffindor.DictionaryApplication;
import com.gryffindor.backend.entities.Translation;
import com.gryffindor.backend.entities.Word;

public class FireStore {
  private static Firestore database;
  private static final long TIMEOUT = 10;
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

  public static void add(Word word) throws InterruptedException, ExecutionException {
    ApiFuture<WriteResult> future = database.collection("dictionary").document(word.getWordTarget()).set(word);

    System.out.println("added to database " + future.get().getUpdateTime());
  }

  public static void add(List<Word> words) {
    final int MAX_REQUEST = 400;

    for (int itr = 0; itr < words.size();) {
      boolean start = true;
      WriteBatch batch = database.batch();

      for (; itr < words.size() && itr % MAX_REQUEST != 0 || start; itr++) {
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

  public static Word find(String wordTarget) throws InterruptedException, ExecutionException, TimeoutException {
    ApiFuture<DocumentSnapshot> future = database.collection("dictionary").document(wordTarget).get();

    Word wordFound = future.get(TIMEOUT, TimeUnit.SECONDS).toObject(Word.class);
    // if (wordFound == null) {
    // throw new NullPointerException("Not found");
    // }

    if (wordFound == null) {
      System.out.println("Not found on FireBase");
    }
    return wordFound;
  }

  public static void deleteTranslation(Word word, Translation translation) 
      throws InterruptedException, ExecutionException, TimeoutException {
    DocumentReference docRef = database.collection("dictionary").document(word.getWordTarget());

    System.out.println(docRef.update("translations", FieldValue.arrayRemove(translation))
        .get(TIMEOUT, TimeUnit.MINUTES).getUpdateTime());
  }

  public static void updateTranslation(Word word, Translation oldTrans, Translation newTrans) 
      throws InterruptedException, ExecutionException, TimeoutException {
    DocumentReference docRef = database.collection("dictionary").document(word.getWordTarget());
    WriteBatch batch = database.batch();

    batch.update(docRef, "translations", FieldValue.arrayRemove(oldTrans));
    batch.update(docRef, "translations", FieldValue.arrayUnion(newTrans));

    System.out.println("Updated " + batch.commit().get(TIMEOUT, TimeUnit.SECONDS));
  }
}