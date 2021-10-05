package com.gryffindor.backend.api;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteBatch;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.gryffindor.backend.entities.Word;

public class FireStore {
    private static Firestore database;

    static {
        try {
            InputStream serviceAccount = new FileInputStream(
                    "C:/Users/hoang/OneDrive/Documents/CodeSpace/Java/OASIS/dictionary/src/main/resources/serviceAccount.json");

            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

            FirebaseOptions options = FirebaseOptions.builder().setCredentials(credentials).build();

            FirebaseApp.initializeApp(options);
            database = FirestoreClient.getFirestore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void add(Word word) throws InterruptedException, ExecutionException {
        ApiFuture<DocumentReference> future = database.collection("dictionary").add(word);

        System.out.println("added to database " + future.get().getId());
    }

    public static void add(Collection<Word> words) {
        WriteBatch batch = database.batch();

        for (Word word : words) {
            DocumentReference docRef = database.collection("dictionary").document();
            batch.set(docRef, word);
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

    public static Word find(String wordTarget) throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = database.collection("dictionary")
                .whereGreaterThanOrEqualTo("wordTarget", wordTarget).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        DocumentSnapshot documentSnapshot = documents.get(0);

        return documentSnapshot.toObject(Word.class);
    }
}
