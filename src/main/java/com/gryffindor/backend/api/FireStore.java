package com.gryffindor.backend.api;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteBatch;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.gryffindor.DictionaryApplication;
import com.gryffindor.backend.entities.Word;

public class FireStore {
  private static Firestore database;

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

    Word wordFound = future.get(10, TimeUnit.SECONDS).toObject(Word.class);
    // if (wordFound == null) {
    // throw new NullPointerException("Not found");
    // }

    if (wordFound == null) {
      System.out.println("Not found on FireBase");
    }
    return wordFound;
  }
}
