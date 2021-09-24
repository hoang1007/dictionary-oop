package com.gryffindor.backend.utils;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.List;

import com.gryffindor.backend.entities.Dictionary;
import com.gryffindor.backend.entities.Word;

public class DictionaryManagement {
  public final Dictionary dictionary;

  private static Scanner scanner = new Scanner(System.in);

  public DictionaryManagement() {
    dictionary = new Dictionary();
  }

  /** Nhập từ mới từ command line. */
  public void insertFromCommandline() {
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
  }

  /**
   * Xóa từ trong list words
   */
  public void deleteFromCommand() {
    System.out.println("Nhap tu muon xoa: ");
    String input = scanner.next();
    List<Word> w = dictionary.searchWords(input);
    List<Word> clone_words = dictionary.getAllWords();
    System.out.println(clone_words.size());
    clone_words.removeAll(w);
    System.out.println(clone_words.size());
  }

}
