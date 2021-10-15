package com.gryffindor.backend.entities;

public class Word {
  private String word_target;
  private String word_explain;

  public Word(String word_target, String word_explain) {
    this.word_target = word_target;
    this.word_explain = word_explain;
  }

  /**
   * @param word_target
   */
  public void setWordTarget(String word_target) {
    this.word_target = word_target;
  }

  /**
   * Từ tiếng Anh.
   * 
   * @return String
   */
  public String getWordTarget() {
    return this.word_target;
  }

  /**
   * Nghĩa.
   * 
   * @param word_explain
   */
  public void setWordExplain(String word_explain) {
    this.word_explain = word_explain;
  }

  /**
   * @return String
   */
  public String getWordExplain() {
    return this.word_explain;
  }
}