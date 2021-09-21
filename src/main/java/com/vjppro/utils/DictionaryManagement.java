package com.vjppro.utils;

import java.util.Scanner;

import com.vjppro.entities.Dictionary;
import com.vjppro.entities.Word;

public class DictionaryManagement {
  public static final DictionaryManagement INSTANCE = new DictionaryManagement();
  private final Dictionary dictionary;

  DictionaryManagement() {
    dictionary = new Dictionary();
  }

  public Dictionary getDictionary() {
    return dictionary;
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

      String word_target = scanner.nextLine();

      System.out.println("Nhap nghia:");
      String word_explain = scanner.nextLine();

      dictionary.addWord(new Word(word_target, word_explain));
    }
    
    scanner.close();
  }
}
