package com.gryffindor.backend;

import com.gryffindor.backend.entities.Word;
import com.gryffindor.backend.utils.DictionaryManagement;
import com.gryffindor.backend.api.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline {
  private DictionaryManagement dictionaryManagement;

  public DictionaryCommandline() {
    dictionaryManagement = AppData.INSTANCE.dictionaryManagement;
  }

  public void showAllWords() {
    System.out.println("No | English |  Spelling | WordType | Vietnamese");
    int i = 1;
    for (Word w : dictionaryManagement.dictionary.getAllWords()) {
      System.out.println(String.format("%d | %s | %s | %s | %s", i++, w.getWordTarget(), w.getWordSpelling(),
          w.getWordType(), w.getWordExplain()));
    }
  }

  public void dictionaryBasic() {
    dictionaryManagement.insertFromCommandline();

    showAllWords();
  }

  public void dictionaryAdvance() {
    // dictionaryManagement.insertFromFile();
    System.out.println("Bắt đầu chương trình\n");
    dictionaryManagement.addDataFromFile();

    showAllWords();

    // dictionaryManagement.dictionaryLookup();

    // dictionaryManagement.dictionaryExportToFile();
  }

  public List<Word> dictionarySearch() {
    System.out.println("Nhap tu can tra: ");
    Scanner scanner = new Scanner(System.in);
    String word_target = scanner.nextLine();
    return (List<Word>) dictionaryManagement.dictionary.searchWord(word_target);
  }

}
