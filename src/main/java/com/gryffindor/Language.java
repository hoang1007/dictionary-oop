package com.gryffindor;

public enum Language {
  DETECT("", ""),
  ENGLISH("en", "English"),
  VIETNAMESE("vi", "Tiếng Việt");

  private final String shortString;
  private final String longString;

  Language(String shortString, String longString) {
    this.shortString = shortString;
    this.longString = longString;
  }

  public String toShortString() {
    return shortString;
  }

  public String toLongString() {
    return longString;
  }
}
