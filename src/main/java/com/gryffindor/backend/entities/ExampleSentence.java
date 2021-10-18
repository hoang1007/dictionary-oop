package com.gryffindor.backend.entities;

public class ExampleSentence {
  private String langTo;
  private String langFrom;

  /** Constructor. */
  public ExampleSentence(String langFrom, String langTo) {
    this.langTo = langTo;
    this.langFrom = langFrom;
  }

  /** Constructor. */
  public ExampleSentence() {
    langTo = "";
    langFrom = "";
  }

  /** @return String */
  public String getLangTo() {
    return langTo;
  }

  /** @return String */
  public String getLangFrom() {
    return langFrom;
  }

  /** @return String */
  @Override
  public String toString() {
    return langFrom + ":\t" + langTo;
  }
}
