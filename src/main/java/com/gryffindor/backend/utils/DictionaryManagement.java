package com.gryffindor.backend.utils;

import java.util.Scanner;

import com.gryffindor.backend.entities.Dictionary;
import com.gryffindor.backend.entities.Word;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;


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

      String word_target = scanner.nextLine();

      System.out.println("Nhap nghia:");
      String word_explain = scanner.nextLine();

      dictionary.addWord(new Word(word_target, word_explain));
    }

    scanner.close();
  }

  /** Nhập từ mới từ file dictionaries.txt. */
  public void insertFromFile() {
      //url file dictionaries.txt
      String url = "D:\\IT\\Java\\Project\\Dictionary\\src\\resources\\dictionaries.txt";

      // Đọc dữ liệu từ File với BufferedReader.
      FileInputStream fileInputStream = null;
      BufferedReader bufferedReader = null;
      try {
          fileInputStream = new FileInputStream(url);
          bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
          String line = bufferedReader.readLine();
          while (line != null) {
              //Xử lí xâu từ file text truyền vào mảng Word.
              for (int i = 1; i < line.length(); i++) {
                  if ( line.charAt(i) == '\t') {
                      String word_target = line.substring(0, i);
                      String word_explain = line.substring(i + 1);
                      dictionary.addWord(new Word(word_target, word_explain));
                      break;
                  }
              }
              line = bufferedReader.readLine();
          }
      } catch (IOException e) {
          e.printStackTrace();
      } finally {
          // Đóng file.
          try {
              bufferedReader.close();
              fileInputStream.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  }

  /**   Tra cuu tu dien bang commandline. */
  public void dictionaryLookup() {
      System.out.println("Nhap tu can tra: ");
      Scanner scanner = new Scanner(System.in);
      String word_target = scanner.nextLine();
      if (dictionary.searchWord(word_target) != null) {
          System.out.println(word_target + " co nghia la: " + dictionary.searchWord(word_target).getWordExplain());
      } else {
          System.out.println("Khong co tu " + word_target + " trong tu dien");
      }
  }
}
