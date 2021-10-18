package com.gryffindor.backend.entities;

import com.gryffindor.backend.utils.SortedList;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
  /* Tập dữ liệu từ các kí tự từ a-z
   * đến danh sách các từ bắt đầu bằng kí tự đó
   */
  private List<SortedList<Word>> dataset;

  /** Tạo từ điển. */
  public Dictionary() {
    dataset = new ArrayList<>();

    for (char c = 'a'; c <= 'z'; c++) {
      dataset.add(new SortedList<>());
    }
  }

  // get word list which word should be in
  public List<Word> getWordList(String c) {
    return dataset.get(c.charAt(0) - 'a');
  }

  /** Thêm từ mới vào từ điển. */
  public void addWord(Word word) {
    getWordList(word.getWordTarget()).add(word);
  }

  /** Xóa từ khỏi từ điển. */
  public void removeWord(Word word) {
    getWordList(word.getWordTarget()).remove(word);
  }

  /**
   * Tìm một từ trong từ điển.
   *
   * @param wordTarget từ mới muốn tìm
   * @return trả về từ mới nếu tìm thấy, null nếu không tìm được
   */
  public Word searchWord(String wordTarget) {
    for (Word w : getWordList(wordTarget)) {
      if (w.getWordTarget().equals(wordTarget)) {
        return w;
      }
    }

    return null;
  }

  /**
   * Tìm tất cả các từ liên quan. VD: "tra" trả về tradition, translate Tìm tất cả từ đồng âm.
   *
   * @param wordTarget từ mới muốn tìm
   * @return danh sách các từ tìm được
   */
  public List<Word> searchWords(String wordTarget) {
    List<Word> wordFounds = new ArrayList<>();

    for (Word w : getWordList(wordTarget)) {
      if (w.getWordTarget().startsWith(wordTarget)) {
        wordFounds.add(w);
      }
    }

    return wordFounds;
  }

  /**
   * Tìm tất cả các từ đồng nghĩa.
   *
   * @param wordExplain nghĩa muốn tìm
   * @return danh sách các từ tìm được
   */
  public List<Word> getSynonyms(String wordExplain) {
    List<Word> synonyms = new ArrayList<>();

    return synonyms;
  }

  /** Xóa tất cả các từ trong từ điển. */
  public void clear() {
    for (List<Word> list : dataset) {
      list.clear();
    }
  }

  /** Lấy tất cả các từ có trong từ điển. */
  public List<Word> getAllWords() {
    List<Word> allWords = dataset.get(0);

    for (int i = 1; i < dataset.size(); i++) {
      allWords.addAll(dataset.get(i));
    }

    return allWords;
  }
}
