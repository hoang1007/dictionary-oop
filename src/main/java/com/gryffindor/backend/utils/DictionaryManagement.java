package com.gryffindor.backend.utils;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;

import com.gryffindor.backend.entities.Dictionary;
import com.gryffindor.backend.entities.Word;
import java.io.*;
import java.io.FileInputStream;


public class DictionaryManagement {
  public final Dictionary dictionary;

  private static Scanner scanner = new Scanner(System.in);

  public DictionaryManagement() {
    dictionary = new Dictionary();
  }

  /** Nhập từ mới từ command line. */
  public void insertFromCommandline() {
    System.out.println("Nhập số từ muốn thêm:");
    int n = scanner.nextInt();
    scanner.nextLine(); // bỏ qua 1 dòng thừa

    for (int i = 1; i <= n; i++) {
      System.out.println(String.format("Đang nhập từ thứ %d...", i));

      System.out.println("Nhập từ mới: ");
      String word_target = scanner.nextLine();

      System.out.println("Nhập nghĩa: ");
      String word_explain = scanner.nextLine();

      dictionary.addWord(new Word(word_target, word_explain));
    }
  }

  /** Nhập từ mới từ file dictionaries.txt. */
  public void insertFromFile() {
      //url file dictionaries.txt
      String url = ".\\src\\resources\\data.txt";
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
      System.out.println("Nhập từ cần tra: ");
      String word_target = scanner.nextLine();
      Word word = dictionary.searchWord(word_target);
      if (word != null) {
          System.out.println(word_target + " : " + word.getWordExplain());
      } else {
          System.out.println("Không có từ \"" + word_target + "\" trong từ điển. Xin lỗi về sự bất tiện này");
      }
  }

    /** Export to file. */
  public void dictionaryExportToFile() {
      String url = ".\\src\\resources\\output.txt";
      /* Create new file. */
      File file = null;
      try{
          file = new File(url);
          boolean isCreat = file.createNewFile();
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
      System.out.println("Xuất file thành công!");
  }

  /** Xóa word từ dòng lệnh */
  public void deleteWordFromCommandline() {
      System.out.println("Nhập số từ bạn muốn xóa:");
      int n = Integer.parseInt(scanner.nextLine());

      for (int i = 0; i < n; i++) {
          System.out.println("Nhập từ cần xóa: ");
          String wordDeleteTarget = scanner.nextLine();

          Word wordDelete = dictionary.searchWord(wordDeleteTarget);

          if (wordDelete == null) {
              System.out.println("Không có từ " + wordDeleteTarget + "trong từ điển.");
          } else {
              dictionary.removeWord(wordDelete);
          }
      }
  }

  /** Sửa word trong từ điển từ dòng lệnh. */
  public void updateWordFromCommandline() {
      System.out.println("Nhập từ cần sửa: ");
      String wordUpdateTarget = scanner.nextLine();

      Word wordUpdate = dictionary.searchWord(wordUpdateTarget);
      int index = dictionary.getAllWords().indexOf(wordUpdate);

      if (index != -1) {
          System.out.print("Nhập nghĩa: ");
          String wordUpdateExplain = scanner.nextLine();
          dictionary.getAllWords().set(index, new Word(wordUpdateTarget, wordUpdateExplain));
          System.out.print("Bạn đã sửa " + wordUpdateTarget + " thành " + wordUpdateExplain + ".");
      } else {
          System.out.println("\"" + wordUpdateTarget + "\" không có.");
      }
  }
}
