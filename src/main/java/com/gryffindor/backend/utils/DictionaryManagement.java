package com.gryffindor.backend.utils;

import com.google.common.collect.Iterables;
import com.gryffindor.Config;
import com.gryffindor.DictionaryApplication;
import com.gryffindor.backend.entities.Dictionary;
import com.gryffindor.backend.entities.ExampleSentence;
import com.gryffindor.backend.entities.Translation;
import com.gryffindor.backend.entities.Word;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class DictionaryManagement {
  public final Dictionary dictionary;

  public DictionaryManagement() {
    dictionary = new Dictionary();
  }

  /** Nhập từ mới từ command line. */
  public void insertFromCommandline() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Nhập số từ bạn muốn thêm:");

    int n = scanner.nextInt();
    scanner.nextLine(); // bỏ qua 1 dòng thừa

    for (int i = 1; i <= n; i++) {
      System.out.println(String.format("Đang nhập từ thứ %d...", i));
      System.out.println("Nhập từ mới:");

      String word_target = scanner.nextLine();

      System.out.println("Nhập nghĩa:");
      String word_explain = scanner.nextLine();

      dictionary.addWord(new Word(word_target, word_explain));
    }

    scanner.close();
  }

  public Dictionary getDictionary() {
    return dictionary;
  }

  /** Nhập từ mới từ file dictionaries.txt. */
  public void insertFromFile() {
    // url file dictionaries.txt
    String url = ".\\src\\resources\\dictionary.txt";

    // Đọc dữ liệu từ File với BufferedReader.
    FileInputStream fileInputStream = null;
    BufferedReader bufferedReader = null;

    try {
      fileInputStream = new FileInputStream(url);
      bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
      String line = bufferedReader.readLine();

      while (line != null) {
        // Xử lí xâu từ file text truyền vào mảng Word.
        for (int i = 1; i < line.length(); i++) {
          if (line.charAt(i) == '\t') {
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

  /** Tra cuu tu dien bang commandline. */
  public void dictionaryLookup() {
    System.out.println("Nhập từ cần tra: ");
    Scanner scanner = new Scanner(System.in);
    String word_target = scanner.nextLine();

    if (dictionary.searchWord(word_target) != null) {
      System.out.println(word_target + ":    " + dictionary.searchWord(word_target).getTranslations().get(0).getWordExplain());
    } else {
      System.out.println("Chưa có từ " + word_target + " trong từ điển");
    }
    
    scanner.close();
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

  /**
   * Nhạp dât từ file nâng cao có giải thích ..... chưa xử lí hoàn toàn xong xâu
   * nghĩa lỗi khi đọc vào dòng trống
   */
  public void addDataFromFile() {
    Config config = DictionaryApplication.INSTANCE.config;
    Stack<Word> words = new Stack<>();

    try (InputStreamReader reader = new InputStreamReader(config.getDataDictionaryStream(), StandardCharsets.UTF_8);
      BufferedReader bufferedReader = new BufferedReader(reader)) {

      String word_target = "";
      String word_spelling = "";
      String word_class = "";

      for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
        if (words.size() > 200) break;
        // word target and word spelling is in the same line
        if (line.startsWith(config.getWordTargetSign())) {
          int posTarget = line.indexOf(config.getWordTargetSign());
          int posSpelling = line.indexOf(config.getWordSpellingSign());

          try {
            word_target = line.substring(posTarget + 1, posSpelling);
            word_spelling = line.substring(posSpelling);

            word_target = TextUtils.format(word_target);
            word_spelling = TextUtils.format(word_spelling);
          } catch (Exception e) {
            word_target = line.substring(posTarget + 1);
            word_spelling = TextUtils.empty();
          }

          System.out.println("Found spelling: " + word_spelling);
          System.out.println("Found word target: " + word_target);

        } else if (line.startsWith(config.getWordClassSign())) { // word class
          // mỗi loại từ là một từ
          words.add(new Word(word_target, word_spelling));

          word_class = line.substring(line.indexOf(config.getWordClassSign()) + 1);
          word_class = TextUtils.format(word_class);

          System.out.println("Found word type " + word_class);

          if (!words.empty()) {
            words.peek().setWordClass(word_class);
          }
        } else if (line.startsWith(config.getWordExplainSign())) {
          // mỗi từ giải thích bắt đầu 1 phần giải thích

          String word_explain = line.substring(line.indexOf(config.getWordExplainSign()) + 1);
          word_explain = TextUtils.format(word_explain);

          System.out.println("Found explain: " + word_explain);

          words.peek().addTranslation(new Translation(word_explain));

          if (words.peek().getWordClass().length() == 0) {
            words.peek().setWordClass(word_class);
          }
          if (words.peek().getWordTarget().length() == 0) {
            words.peek().setWordTarget(word_target);
          }
          if (words.peek().getWordSpelling().length() == 0) {
            words.peek().setWordSpelling(word_spelling);
          }
        } else if (line.startsWith(config.getExampleSign())) {
          String[] example = line.substring(line.indexOf(config.getExampleSign()) + 1).split(config.getExampleDelim());

          example = TextUtils.format(example);
          ExampleSentence eSentence = new ExampleSentence(example[0], example[1]);
          System.out.println("Found example " + eSentence);

          Iterables.getLast(words.peek().getTranslations()).addExampleSentences(eSentence);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Add all words loaded to the dictionary
    for (Word word : words) {
      try {
        dictionary.addWord(word);
      } catch (Exception e) {
        System.out.println(word.getWordTarget());
        System.out.println(word.getWordTarget().length());
      }
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
