package com.gryffindor.backend.entities;

import com.gryffindor.backend.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class Word implements Comparable<Word> {
  private String wordTarget = TextUtils.empty();
  private String wordSpelling = TextUtils.empty(); // phiên âm.
  private String wordClass = TextUtils.empty();
  private Source source = Source.LOCAL;
  private List<Translation> translations = new ArrayList<>();

  public enum Source {
    FIRESTORE, LOCAL, GOOGLE
  }

  public Word() {
  }

  public Word(String wordTarget) {
    this.wordTarget = wordTarget;
  }

  /** Constructor have spelling. */
  public Word(String wordTarget, String wordSpelling) {
    this.wordTarget = wordTarget;
    this.wordSpelling = wordSpelling;
  }

  /**
   * Khởi tạo word.
   * @param wordTarget từ muốn dịch
   * @param wordSpelling phiên âm của từ
   * @param translations các bản dịch của từ
   * @see Translation
   * @param source nguồn tìm kiếm của từ [Local, Database, Google Translate]
   */
  public Word(String wordTarget, String wordSpelling, 
        List<Translation> translations, Source source) {
    this.wordTarget = wordTarget;
    this.wordSpelling = wordSpelling;
    this.translations = translations;
    this.source = source;
  }

  public Word setWordTarget(String wordTarget) {
    this.wordTarget = wordTarget;
    return this;
  }

  public String getWordTarget() {
    return this.wordTarget;
  }

  public Word setWordSpelling(String wordSpelling) {
    this.wordSpelling = wordSpelling;
    return this;
  }

  public String getWordSpelling() {
    return this.wordSpelling;
  }

  public Word setWordClass(String wordClass) {
    this.wordClass = wordClass;
    return this;
  }

  public String getWordClass() {
    return this.wordClass;
  }

  /**
   * Thêm các bản dịch cho từ.
   * @param translations các bản dịch muốn thêm
   * @return trả về đối tượng hiện tại
   * @see Translation
   */
  public Word addTranslation(Translation... translations) {
    for (Translation translation : translations) {
      this.translations.add(translation);
    }

    return this;
  }

  public List<Translation> getTranslations() {
    return this.translations;
  }

  public Source getSource() {
    return this.source;
  }

  public Word setSource(Source source) {
    this.source = source;
    return this;
  }

  @Override
  public String toString() {
    return wordTarget;
  }

  @Override
  public int compareTo(Word o) {
    return this.wordTarget.compareTo(o.wordTarget);
  }
}
