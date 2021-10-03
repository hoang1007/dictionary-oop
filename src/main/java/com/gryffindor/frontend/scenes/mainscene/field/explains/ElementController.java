package com.gryffindor.frontend.scenes.mainscene.field.explains;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.frontend.scenes.mainscene.field.IController;

import java.util.Collection;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

public class ElementController implements IController {
  ExplainsField.Element element;
  Word word;

  /** Khởi tạo phần dịch. */
  public ElementController(ExplainsField.Element element) {
    this.element = element;

    onClickEditButton();
    onTranslationEdited();
    onClickDeleteButton();
  }

  private void setSynoymsButton(Collection<Word> words) {
    if (words == null) {
      return;
    }

    for (Word word : words) {
      element.getSynonymsButton().add(new Button(word.getWordTarget()));
    }
  }

  /**
   * Khởi tạo các thuộc tính liên quan đến {@link Word}.
   */
  public void setWord(Word word) {
    this.word = word;

    element.getExampleSentence().setText(String.format("\"%s\"", "ví dụ"));
    element.getWordClass().setText("danh-động- trạng");
    element.getWordDefinition().setText("mô tả");

    element.getWordMeaning().setText(word.getWordExplain());
    setSynoymsButton(null);
  }

  void onClickEditButton() {
    // chỉnh sửa bản dịch
    element.getEditExplainButton().setOnAction(event -> {
      element.getWordMeaning().setEditable(true);
    });
  }

  void onClickDeleteButton() {
    // xóa bản dịch
    element.getDeleteExplainButton().setOnAction(event -> {
      // do delete
      element.getWordClass().setVisible(false);
      element.getExampleSentence().setVisible(false);
      element.getWordMeaning().setVisible(false);
      element.getDeleteExplainButton().setVisible(false);
      element.getEditExplainButton().setVisible(false);

      element.getWordDefinition().setText(DictionaryApplication.INSTANCE.config.getContributeThanks());
    });
  }

  void onTranslationEdited() {
    element.getWordMeaning().setOnKeyPressed(event -> {
      if (event.getCode().equals(KeyCode.ENTER)) {
        element.getWordMeaning().setEditable(false);

        element.getWordClass().setVisible(false);
        element.getExampleSentence().setVisible(false);
        element.getWordMeaning().setVisible(false);
        element.getDeleteExplainButton().setVisible(false);
        element.getEditExplainButton().setVisible(false);

        element.getWordDefinition().setText(DictionaryApplication.INSTANCE.config.getContributeThanks());
      }
    });
  }

  public Word getWord() {
    return word;
  }
}
