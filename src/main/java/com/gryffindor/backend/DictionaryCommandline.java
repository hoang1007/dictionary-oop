package com.gryffindor.backend;

import com.gryffindor.backend.entities.Word;
import com.gryffindor.backend.utils.DictionaryManagement;
import com.gryffindor.backend.api.*;

public class DictionaryCommandline {
  private DictionaryManagement dictionaryManagement;

  public DictionaryCommandline() {
    dictionaryManagement = AppData.INSTANCE.dictionaryManagement;
  }

  public void setDictionaryManagement(DictionaryManagement dictionaryManagement) {
    this.dictionaryManagement = dictionaryManagement;
  }

  public DictionaryManagement getDictionaryManagement() {
    return dictionaryManagement;
  }

  public void showAllWords() {
    System.out.println("No | English | Vietnamese");
    int i = 1;
    for (Word w : dictionaryManagement.dictionary.getAllWords()) {
      System.out.println(String.format("%d | %s | %s", i++, w.getWordTarget(), w.getWordExplain()));
    }
  }

  public void dictionaryBasic() {
    dictionaryManagement.insertFromCommandline();
    showAllWords();
  }

  public void dictionaryAdvanced() {
    dictionaryManagement.insertFromFile();
    showAllWords();
    dictionaryManagement.dictionaryLookup();
  }

  /**
   * tìm kiếm các từ.
   */
  public void dictionarySearcher() {
    System.out.print("Nhập từ cần tra: ");
    String w_input = Scan.scanner.nextLine();
    for (Word w : dictionaryManagement.dictionary.getAllWords()) {
      if (w.getWordTarget().toLowerCase().contains(w_input.toLowerCase())
          && w.getWordTarget().toLowerCase().substring(0, w_input.length()).equals(w_input.toLowerCase())) {
        System.out.println(w.toString());
      }
    }
  }

}