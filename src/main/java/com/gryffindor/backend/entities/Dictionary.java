package com.gryffindor.backend.entities;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
  private List<Word> words;

  public Dictionary() {
    words = new ArrayList<>();
  }

  /** Thêm từ mới vào từ điển. */
  public void addWord(Word word) {
    words.add(word);
  }

  public void setWords(List<Word> words) {
    this.words = words;
  }

  /**
   * Tìm một từ trong từ điển.
   * 
   * @param word_target từ mới muốn tìm
   * @return trả về từ mới nếu tìm thấy, null nếu không tìm được
   */
  public Word searchWord(String word_target) {
    for (Word w : words) {
      if (w.getWordTarget().toLowerCase().equals(word_target.toLowerCase())) {
        return w;
      }
    }

    return null;
  }

  /**
   * Tìm tất cả từ đồng âm.
   * 
   * @param word_target từ mới muốn tìm
   * @return danh sách các từ tìm được
   */
  public List<Word> searchWords(String word_target) {
    List<Word> wordFounds = new ArrayList<>();

    for (Word w : words) {
      if (w.getWordTarget().toLowerCase().equals(word_target.toLowerCase())) {
        wordFounds.add(w);
      }
    }

    return wordFounds;
  }

  /**
   * Tìm tất cả các từ đồng nghĩa.
   * 
   * @param word_explain nghĩa muốn tìm
   * @return danh sách các từ tìm được
   */
  public List<Word> getSynonyms(String word_explain) {
    List<Word> synonyms = new ArrayList<>();

    for (Word w : words) {
      if (w.getWordExplain().equals(word_explain)) {
        synonyms.add(w);
      }
    }

    return synonyms;
  }

  /** Lấy tất cả các từ có trong từ điển. */
  public List<Word> getAllWords() {
    return words;
  }

}
