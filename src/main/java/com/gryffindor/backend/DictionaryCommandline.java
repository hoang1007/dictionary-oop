package com.gryffindor.backend;

import com.gryffindor.backend.entities.Dictionary;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.backend.utils.DictionaryManagement;

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