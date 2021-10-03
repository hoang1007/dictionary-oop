package com.gryffindor.backend.utils;

import java.util.Scanner;

import com.gryffindor.backend.entities.Dictionary;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.backend.api.*;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.*;


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
      String url = "D:\\IT\\Java\\Project\\Dictionary\\src\\resources\\dictionary.txt";

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

    /** Export to file. */
  public void dictionaryExportToFile() {
      String url = "D:\\IT\\Java\\Project\\Dictionary\\src\\resources\\output.txt";
      /* Create new file. */
      File file = null;
      boolean isCreate = false;
      try{
          file = new File(url);
          isCreate = file.createNewFile();
          if (isCreate)
              System.out.print("Da tao file thanh cong!");
          else
              System.out.print("Tao file that bai");
      }
      catch (Exception e){
          System.out.print(e);
      }
      /* Write word to file. */
      FileWriter fileWriter = null;
      BufferedWriter bufferedWriter = null;
      try {
          fileWriter = new FileWriter(url, false);
          bufferedWriter = new BufferedWriter(fileWriter);
          for (Word word : dictionary.getAllWords()) {
              bufferedWriter.write(word.getWordTarget() + "\t" + word.getWordExplain());
              bufferedWriter.newLine();
              bufferedWriter.flush();
          }
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      } finally {
          try {
              fileWriter.close();
              bufferedWriter.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  }

  /** Xóa word từ dòng lệnh */
  public void deleteWordFromCommandline() {
      System.out.println("Nhập từ cần xóa: ");
      Scanner sc = new Scanner(System.in);
      String wordDeleteTarget = sc.nextLine();
      Word wordDelete = dictionary.searchWord(wordDeleteTarget);
      if (wordDelete == null) {
          System.out.println("Không có từ " + wordDeleteTarget + "trong từ điển.");
      } else {
          dictionary.removeWord(wordDelete);
      }

      sc.close();
  }

  /** Sửa word trong từ điển từ dòng lệnh. */
  public void updateWordFromCommandline() {
      System.out.println("Nhập từ cần sửa: ");
      Scanner sc = new Scanner(System.in);
      String wordUpdateTarget = sc.nextLine();
      Word wordUpdate = dictionary.searchWord(wordUpdateTarget);
      int index = dictionary.getAllWords().indexOf(wordUpdate);
      if (index != -1) {
          System.out.print("Nhập nghĩa: ");
          String wordUpdateExplain = sc.nextLine();
          dictionary.getAllWords().set(index, new Word(wordUpdateTarget, wordUpdateExplain));
          System.out.print("Bạn đã sửa " + wordUpdateTarget + " thành " + wordUpdateExplain + ".");
      } else {
          System.out.println(wordUpdateTarget + " không có.");
      }

      sc.close();
  }

  /** Nhạp dât từ file nâng cao có giải thích .....
   * chưa xử lí hoàn toàn xong xâu nghĩa
   * lỗi khi đọc vào dòng trống
   */
  public void addDataFromFile() {
      //url file dictionaries.txt
      String url = "D:\\IT\\Java\\Project\\Dictionary\\src\\resources\\dictionaries.txt";

      // Đọc dữ liệu từ File với BufferedReader.
      FileInputStream fileInputStream = null;
      BufferedReader bufferedReader = null;
      try {
          fileInputStream = new FileInputStream(url);
          bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
          /** Khởi tạo*/
          String word_target = null;
          String word_spelling = null;
          String word_type = null;
          String word_explain = "";
          int index = 0;
          String line = bufferedReader.readLine();
          while (true) {
              // giới hạn vòng while
              index++;
              //if (index > 400000) break;
              System.out.println(index);
              if (line.charAt(0) == '@') {
                  //Xử lí xâu từ file text truyền vào mảng Word.
                  for (int i = 0; i < line.length(); i++) {
                      if (line.charAt(i) == '/') {
                          word_target = line.substring(1, i);
                          word_spelling = line.substring(i);
                          break;
                      }
                  }
              } else if (line.charAt(0) == '*') {
                  word_type = line.substring(1);
              } else if (line.charAt(0) == '-') {
                  word_explain += line.substring(1) + "\n";
              } else {
                  for (int i = 1; i < line.length(); i++) {
                      if (line.charAt(i) == '+') {
                          word_explain += line.substring(1, i) + "  :   " + line.substring(i+1) + "\n";
                          break;
                      }
                  }
              }

              line = bufferedReader.readLine();

              if (line == null) {
                  break;
              }

              if (line.equals("")) {
                  dictionary.addWord(new Word(word_target, word_spelling, word_type, word_explain));
                  word_target = null;
                  word_spelling = null;
                  word_type = null;
                  word_explain = "";
                  while (line.equals("")){
                      line = bufferedReader.readLine();
                  }
              }

              if (line.charAt(0) == '*' && word_type != null) {
                  dictionary.addWord(new Word(word_target, word_spelling, word_type, word_explain));
                  word_type = null;
                  word_explain = "";
              }

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
}
