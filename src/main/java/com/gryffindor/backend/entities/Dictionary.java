package com.gryffindor.backend.entities;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
  private final List<Word> words;

  public Dictionary() {
    words = new ArrayList<>();
  }

  /** Thêm từ mới vào từ điển. */
  public void addWord(Word word) {
    words.add(word);
  }

  /**
   * Tìm một từ trong từ điển.
   * @param wordTarget từ mới muốn tìm
   * @return trả về từ mới nếu tìm thấy, null nếu không tìm được
   */
  public Word searchWord(String wordTarget) {
    for (Word w : words) {
      if (w.getWordTarget() == wordTarget) {
        return w;
      }
    }

    return null;
  }

  /**
   * Tìm tất cả từ đồng âm.
   * @param wordTarget từ mới muốn tìm
   * @return danh sách các từ tìm được
   */
  public List<Word> searchWords(String wordTarget) {
    List<Word> wordFounds = new ArrayList<>();

    for (Word w : words) {
      if (w.getWordTarget() == wordTarget) {
        wordFounds.add(w);
      }
    }

    return wordFounds;
  }

  /**
   * Tìm tất cả các từ đồng nghĩa.
   * @param wordExplain nghĩa muốn tìm
   * @return danh sách các từ tìm được
   */
  public List<Word> getSynonyms(String wordExplain) {
    List<Word> synonyms = new ArrayList<>();

    for (Word w : words) {
      if (w.getWordExplain() == wordExplain) {
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
