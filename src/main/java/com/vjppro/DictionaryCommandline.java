package com.vjppro;

import com.vjppro.entities.Dictionary;
import com.vjppro.entities.Word;
import com.vjppro.utils.DictionaryManagement;

public class DictionaryCommandline {
  private Dictionary dictionary;

  public DictionaryCommandline(Dictionary dictionary) {
    this.dictionary = dictionary;
  }

  public void showAllWords() {
    System.out.println("No | English | Vietnamese");
    int i = 1;
    for (Word w : dictionary.getAllWords()) {
      System.out.println(String.format("%d | %s | %s", i++, w.getWordTarget(), w.getWordExplain()));
    }
  }

  public static void dictionaryBasic() {
    DictionaryManagement.INSTANCE.insertFromCommandline();

    new DictionaryCommandline(DictionaryManagement.INSTANCE.getDictionary()).showAllWords();
  }
}