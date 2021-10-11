package com.gryffindor.frontend.scenes.mainscene.field.translate;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.backend.utils.TextUtils;
import com.gryffindor.frontend.scenes.mainscene.field.IController;

public class TranslateController implements IController {
  private Word word;

  private TranslateField translateField;

  /** Khởi tạo controller của vùng dịch. */
  public TranslateController(TranslateField translateField) {
    this.translateField = translateField;
    translateField.getPane().setVisible(false); // mặc định ẩn

    onClickPronouncedButton();
  }

  void onClickPronouncedButton() {
    translateField.getPronouncedButton().setOnAction(event -> {
      System.out.println("pron clicked");
      try {
        TextUtils.toSpeech(translateField.getWordTarget().getText());
      } catch (Exception e) {
        DictionaryApplication.INSTANCE.exceptionHandler.add(e);
      }
    });
  }

  /**
   * Khởi tạo các thuộc tính liên quan đến {@link Word}.
   * 
   * @param word từ muốn đặt
   */
  public void setWord(Word word) {
    this.word = word;

    translateField.getPane().setVisible(true);
    translateField.getPronouncedText().setText(word.getWordSpelling());
    translateField.getWordTarget().setText(word.getWordTarget());
  }

  public Word getWord() {
    return word;
  }
}
