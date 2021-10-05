package com.gryffindor.backend.entities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Dictionary {
  /* Tập dữ liệu ánh xạ từ các kí tự từ a-z
  * đến danh sách các từ bắt đầu bằng kí tự đó
  */
  private Hashtable<Character, List<Word>> dataset;

  public Dictionary() {
    dataset = new Hashtable<>();

    for (char c = 'a'; c <= 'z'; c++) {
      dataset.put(c, new ArrayList<Word>());
    }
  }

  // get word list which word should be in
  private List<Word> getWordList(Word word) {
    return dataset.get(word.getWordTarget().charAt(0));
  }

  /** Thêm từ mới vào từ điển. */
  public void addWord(Word word) {
    getWordList(word).add(word);
  }

  /** Xóa từ khỏi từ điển. */
  public void removeWord(Word word) {
    getWordList(word).remove(word);
  }

  /**
   * Tìm một từ trong từ điển.
   * @param word_target từ mới muốn tìm
   * @return trả về từ mới nếu tìm thấy, null nếu không tìm được
   */
  public Word searchWord(String word_target) {
    for (Word w : words) {
      if (w.getWordTarget().equals(word_target)) {
        return w;
      }
    }

    return null;
  }

  /**
   * Tìm tất cả các từ liên quan.
   * VD: "tra" trả về tradition, translate
   * Tìm tất cả từ đồng âm.
   * @param word_target từ mới muốn tìm
   * @return danh sách các từ tìm được
   */
  public List<Word> searchWords(String word_target) {
    List<Word> wordFounds = new ArrayList<>();

    for (Word w : words) {
      if (w.getWordTarget().startsWith(word_target)) {
        wordFounds.add(w);
      }
    }

    return wordFounds;
  }

  /**
   * Tìm tất cả các từ đồng nghĩa.
   * @param word_explain nghĩa muốn tìm
   * @return danh sách các từ tìm được
   */
  public List<Word> getSynonyms(String word_explain) {
    List<Word> synonyms = new ArrayList<>();

    return synonyms;
  }

  /** Lấy tất cả các từ có trong từ điển. */
  public List<Word> getAllWords() {
    return words;
  }
}
