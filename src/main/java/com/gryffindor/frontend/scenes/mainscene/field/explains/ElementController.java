package com.gryffindor.frontend.scenes.mainscene.field.explains;

import java.util.List;

import com.gryffindor.backend.api.WordNetDictionary;
import com.gryffindor.backend.entities.Translation;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.frontend.scenes.mainscene.field.IController;
import com.gryffindor.frontend.scenes.mainscene.field.search.SearchController;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ElementController implements IController {
  ExplainsField.Element element;
  HBox synonymsPane;
  Word word;

  /** Khởi tạo phần dịch. */
  public ElementController(ExplainsField.Element element) {
    this.element = element;
    synonymsPane = new HBox();
  }

  private void initSynoymsPane(Word word) {
    if (word == null) {
      return;
    }

    List<String> synonyms = WordNetDictionary.getSynonyms(word);

    if (synonyms == null) {
      return;
    }

    element.getSynonymsButton().clear();

    for (String syn : synonyms) {
      element.getSynonymsButton().add(initSynonymButtons(syn));
    }

    System.out.println(element.getSynonymsButton().size());
    synonymsPane.getChildren().addAll(element.getSynonymsButton());
  }

  private Button initSynonymButtons(String syn) {
    Button button = new Button(syn);
    button.getStyleClass().add("synonym-button");
    button.setOnAction(event -> SearchController.onSearchRequest(button, button.getText()));
    return button;
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

        element.getPane().getChildren().add(translationField.getPane());
      }
    }

    element.getWordClass().setText(word.getWordClass());

    initSynoymsPane(word);
    element.getPane().getChildren().add(synonymsPane);
  }

  public Word getWord() {
    return word;
  }
}
