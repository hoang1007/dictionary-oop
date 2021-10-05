package com.gryffindor.frontend.scenes.mainscene.field.explains;

import com.gryffindor.backend.entities.Translation;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.frontend.scenes.mainscene.field.IController;

import java.util.Collection;

import javafx.scene.control.Button;

public class ElementController implements IController {
  ExplainsField.Element element;
  Word word;

  /** Khởi tạo phần dịch. */
  public ElementController(ExplainsField.Element element) {
    this.element = element;
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

    if (!word.getTranslations().isEmpty()) {
      for (Translation translation : word.getTranslations()) {
        TranslationField translationField = new TranslationField();
        translationField.getController().setTranslation(translation);

        element.getTranslationFields().add(translationField);
      }
    }

    element.getWordClass().setText(word.getWordClass());

    setSynoymsButton(null);
  }

  public Word getWord() {
    return word;
  }
}
