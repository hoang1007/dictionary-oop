package com.gryffindor.backend.utils;

import com.gryffindor.backend.entities.Dictionary;
import com.gryffindor.backend.entities.Word;

import java.util.Scanner;

public class DictionaryManagement {
  public final Dictionary dictionary;

  public DictionaryManagement() {
    dictionary = new Dictionary();
  }

  /** Nhập từ mới từ command line. */
  public void insertFromCommandline() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Nhap so tu moi muon them:");

    int n = scanner.nextInt();
    scanner.nextLine(); // bỏ qua 1 dòng thừa

    for (int i = 1; i <= n; i++) {
      System.out.println(String.format("Dang nhap tu thu %d...", i));
      System.out.println("Nhap tu moi:");

      String wordTarget = scanner.nextLine();

      System.out.println("Nhap nghia:");
      String wordExplain = scanner.nextLine();

      dictionary.addWord(new Word(wordTarget, wordExplain));
    }
    
    scanner.close();
  }
}