package com.gryffindor.backend.utils;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;

import com.gryffindor.backend.entities.Dictionary;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.backend.api.*;

public class DictionaryManagement {
  public final Dictionary dictionary;
  // direction of directories.txt
  private static String path = "src/main/java/com/gryffindor/backend/storage/dictionaries.txt";

  public DictionaryManagement() {
    dictionary = new Dictionary();
  }

  /**
   * Nhập từ mới từ command line.
   * 
   */
  public void insertFromCommandline() {
    System.out.println("Nhap so tu moi muon them:");

    int n = Scan.scanner.nextInt();
    Scan.scanner.nextLine(); // bỏ qua 1 dòng thừa

    for (int i = 1; i <= n; i++) {
      System.out.println(String.format("Dang nhap tu thu %d...", i));
      System.out.println("Nhap tu moi:");

      String word_target = Scan.scanner.nextLine();

      System.out.println("Nhap nghia:");
      String word_explain = Scan.scanner.nextLine();

      dictionary.addWord(new Word(word_target, word_explain));
    }
  }

  /**
   * Luu dữ liệu tu dictionaries.txt
   */
  public void insertFromFile() {
    FileReader fileReader = null;
    BufferedReader bufferedReader = null;
    try {
      fileReader = new FileReader(path);
      bufferedReader = new BufferedReader(fileReader);
      String data;
      while ((data = bufferedReader.readLine()) != null) {
        String[] w = data.trim().split("_");
        String word_target = w[0];
        String word_explain = w[1];
        dictionary.addWord(new Word(word_target, word_explain));
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        bufferedReader.close();
        fileReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * tra từ trong từ điển.Tìm chuẩn đúng các từ.
   * 
   * Example : my => return my NOT my mother ,...
   */
  public void dictionaryLookup() {
    FileReader fileReader = null;
    BufferedReader bufferedReader = null;
    try {
      System.out.println("Nhập từ cần tra: ");
      String input = Scan.scanner.nextLine();

      fileReader = new FileReader(path);
      bufferedReader = new BufferedReader(fileReader);
      String data;
      while ((data = bufferedReader.readLine()) != null) {
        if (data.toLowerCase().contains(input.toLowerCase())) {
          String[] text = data.trim().split("_");
          String word_target = text[0].trim();
          String word_explain = text[1].trim();
          if (word_target.equals(input)) {
            System.out.println(word_target + " | " + word_explain);
          }
        }
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        bufferedReader.close();
        fileReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * xóa từ trong list words.
   * 
   * @param w_deleted từ cần xóa
   */
  public void deleteWord(String w_deleted) {
    Word w = dictionary.searchWord(w_deleted);
    List<Word> clone_words = dictionary.getAllWords();
    clone_words.remove(w);
    dictionary.setWords(clone_words);
  }

  /**
   * Sửa từ .
   * 
   * @param w_input từ cần sửa.
   */
  public void updateWord(String w_input) {
    Word w = dictionary.searchWord(w_input);
    // index of w_input in list of words
    int index = dictionary.getAllWords().indexOf(w);

    if (index != -1) {
      System.out.print("Sửa " + w_input + " thành :");
      String w_update = Scan.scanner.nextLine();
      System.out.print("Nhập nghĩa: ");
      String w_update_explain = Scan.scanner.nextLine();
      dictionary.getAllWords().set(index, new Word(w_update, w_update_explain));

    } else {
      System.err.println(w_input + " không có.");
    }
  }

  /**
   * xuất từ điển ra file;
   */
  public void dictionaryExportToFile() {
    FileWriter fw = null;
    BufferedWriter bw = null;
    try {
      fw = new FileWriter(path, false);// not appending
      bw = new BufferedWriter(fw);
      for (Word w : dictionary.getAllWords()) {
        bw.write(w.getWordTarget() + "_" + w.getWordExplain());
        bw.newLine();
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        bw.close();
        fw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}