package com.gryffindor.backend.entities;

import com.gryffindor.backend.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class Translation {
  private String wordExplain = TextUtils.empty();
  private List<ExampleSentence> exampleSentences = new ArrayList<>();

  public Translation() {
  }

  public Translation(String wordExplain) {
    this.wordExplain = wordExplain;
  }

  /**
   * Thêm các câu ví dụ cho từ.
   * @param exampleSentences các câu ví dụ
   * @return trả về đối tượng hiện tại
   */
  public Translation addExampleSentences(ExampleSentence... exampleSentences) {
    for (ExampleSentence exampleSentence : exampleSentences) {
      this.exampleSentences.add(exampleSentence);
    }

    return this;
  }

  public List<ExampleSentence> getExampleSentences() {
    return this.exampleSentences;
  }

  public Translation setWordExplain(String wordExplain) {
    this.wordExplain = wordExplain;
    return this;
  }

  public String getWordExplain() {
    return this.wordExplain;
  }

  /** Tạo một bản sao của đối tượng hiện tại. */
  public Translation clone() {
    Translation o = new Translation();
    o.setWordExplain(this.wordExplain);
    o.exampleSentences.addAll(this.exampleSentences);

    return o;
  }
}
