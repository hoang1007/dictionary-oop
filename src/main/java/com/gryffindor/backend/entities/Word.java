package com.gryffindor.backend.entities;

public class Word {
  private String wordTarget;
  private String wordExplain;

  public Word(String wordTarget, String wordExplain) {
    this.wordTarget = wordTarget;
    this.wordExplain = wordExplain;
  }

  public void setWordTarget(String wordTarget) {
    this.wordTarget = wordTarget;
  }

  public String getWordTarget() {
    return this.wordTarget;
  }

  public void setWordExplain(String wordExplain) {
    this.wordExplain = wordExplain;
  }

  public String getWordExplain() {
    return this.wordExplain;
  }

  @Override
  public String toString() {
    return wordTarget + " - " + wordExplain;
  }
}