package com.gryffindor.backend.utils;

import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.gryffindor.Config;
import com.gryffindor.DictionaryApplication;
import com.gryffindor.Language;
import com.gryffindor.backend.api.FireStore;
import com.gryffindor.backend.api.GoogleTranslator;
import com.gryffindor.backend.entities.Dictionary;
import com.gryffindor.backend.entities.ExampleSentence;
import com.gryffindor.backend.entities.Translation;
import com.gryffindor.backend.entities.Word;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
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
  public Word dictionaryLookup(String wordTarget) {
    Word ans = null;
    if (dictionary.searchWord(wordTarget) != null) {
      ans = dictionary.searchWord(wordTarget);
      ans.setSource(Word.Source.LOCAL);
    } else {
      System.out.println("Chưa có từ " + wordTarget + " trong từ điển");
    }
    return ans;
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
   * Đọc dữ liệu từ file text
   * 
   * @deprecated Hàm này đã không còn được sử dụng vì chương trình chuyển sang nạp
   *             dữ liệu từ json
   *             <p>
   *             Sử dụng {@link DictionaryManagement#insertFromJson()} để thay thế
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
        // if (words.size() > 200)
        // break;
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
    dictionary.clear();
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

  public Word searchWordFromFireBase(String wordTarget) {
    Word ans = null; // answer
    try {
      ans = FireStore.find(wordTarget);
      ans.setSource(Word.Source.FIRESTORE);
    } catch (InterruptedException | ExecutionException e) {
      DictionaryApplication.INSTANCE.exceptionHandler.add(e);
    } catch (TimeoutException e) {
      System.out.println("Time out in firebase search");
    }

    return ans;
  }

  public Word searchWordFromGoogleTranslator(String wordTarget) {
    Word ans = null; // answer
    try {
      ans = new Word(wordTarget);
      String trans = GoogleTranslator.translate(wordTarget, Language.DETECT, Language.VIETNAMESE);
      System.out.println("Translated: " + trans);
      ans.addTranslation(new Translation(trans));
      ans.setSource(Word.Source.GOOGLE);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ans;
  }

  public Word searchWordOnline(String wordTarget) {
    // nếu số từ trong string >= 3 thì
    // string là một câu hoặc đoạn văn
    // nên tìm kiếm bằng google
    if (wordTarget.split(" ").length >= 3) {
      return searchWordFromGoogleTranslator(wordTarget);
    }

    Word ans = null;
    ans = searchWordFromFireBase(wordTarget);
    // nếu không tìm thấy từ trong database
    // tìm bằng google
    if (ans == null) {
      ans = searchWordFromGoogleTranslator(wordTarget);
    }
    return ans;
  }

  public void insertFromJson() {
    JsonElement element = JsonParser
        .parseReader(new InputStreamReader(DictionaryApplication.INSTANCE.config.getDictionaryJson()));

    Word[] words = new Gson().fromJson(element, Word[].class);

    dictionary.clear();
    for (Word word : words) {
      dictionary.addWord(word);
    }
  }

  public void exportToJson() throws IOException {
    List<Word> allWords = dictionary.getAllWords();

    String jsonData = new Gson().toJson(allWords);

    try (FileWriter writer = new FileWriter(
        new File(new URI(DictionaryApplication.INSTANCE.config.getRootPath() + "/dictionary.txt")))) {
      writer.write(jsonData);
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
  }

  public boolean updateTranslation(Word word, Translation oldTrans, Translation newTrans) {
    switch (word.getSource()) {
      case LOCAL:
        int wordId = dictionary.getWordList(word.getWordTarget()).indexOf(word);
        int transId = word.getTranslations().indexOf(oldTrans);

        dictionary.getWordList(word.getWordTarget()).get(wordId)
            .getTranslations().set(transId, newTrans);
        break;
      case FIRESTORE:
        try {
          FireStore.updateTranslation(word, oldTrans, newTrans);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
          DictionaryApplication.INSTANCE.exceptionHandler.add(e);
          return false;
        }
      default:
        break;
    }

    return true;
  }

  public boolean deleteTranslation(Word word, Translation trans) {
    switch (word.getSource()) {
      case LOCAL:
        int wordId = dictionary.getWordList(word.getWordTarget()).indexOf(word);

        dictionary.getWordList(word.getWordTarget()).get(wordId)
          .getTranslations().remove(trans);
        break;
      case FIRESTORE:
        try {
          FireStore.deleteTranslation(word, trans);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
          DictionaryApplication.INSTANCE.exceptionHandler.add(e);
          return false;
        }
        break;
      default:
        break;
    }

    return true;
  }
}