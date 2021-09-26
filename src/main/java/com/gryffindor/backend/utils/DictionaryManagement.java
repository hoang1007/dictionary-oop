package com.gryffindor.backend.utils;

import com.gryffindor.backend.entities.Dictionary;
import com.gryffindor.backend.entities.Word;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DictionaryManagement {
  public final Dictionary dictionary;

  public DictionaryManagement() {
    dictionary = new Dictionary();
  }

  /** Nhập từ mới từ command line. 
   */
  public void insertFromCommandline() {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      System.out.println("Nhập số từ muốn thêm:");

      int n = Integer.parseInt(reader.readLine()); // bỏ qua 1 dòng thừa

      for (int i = 1; i <= n; i++) {
        System.out.println(String.format("Đang nhập từ thứ %d...", i));
        System.out.println("Nhập từ mới:");

        String wordTarget = reader.readLine();

        System.out.println("Nhập nghĩa:");
        String wordExplain = reader.readLine();

        dictionary.addWord(new Word(wordTarget, wordExplain));
      }

      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}