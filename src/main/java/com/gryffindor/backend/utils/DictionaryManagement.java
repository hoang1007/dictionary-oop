package com.gryffindor.backend.utils;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.backend.entities.Dictionary;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.frontend.scenes.mainscene.field.export.ExportController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

public class DictionaryManagement {
  public final Dictionary dictionary;
  public final ExportController exportController; // quản lý xuất file

  public DictionaryManagement() {
    dictionary = new Dictionary();
    exportController = new ExportController();
  }

  public Dictionary getDictionary() {
    return dictionary;
  }

  /**
   * Nhập từ mới từ command line.
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

  // export to file chooser
  public void dictionaryExportToFile(File file) {
    if (file != null) {
      System.out.println("Start saving file");
      PrintWriter pw = null;
      try {
        pw = new PrintWriter(file);

        // get All Word in dictionary
        List<Word> ls = DictionaryApplication.INSTANCE.getDictionaryManagement().getDictionary().getAllWords();
        ls.add(new Word("father", "bo"));
        ls.add(new Word("grandfather", "ong"));
        ls.add(new Word("mother", "me"));
        ls.add(new Word("nephew", "chau trai"));
        int list_size = ls.size();
        for (int i = 0; i < list_size; i++) {
          pw.write(ls.get(i).toString());
          pw.write("\n");
        }

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } finally {
        pw.close();
      }
      System.out.println("You saved file '" + file.getName() + "' to '" + file.getPath() + "'");
      System.out.println("Finish saving file");
    } else {
      System.out.println("Failed saving file");
    }
  }
}