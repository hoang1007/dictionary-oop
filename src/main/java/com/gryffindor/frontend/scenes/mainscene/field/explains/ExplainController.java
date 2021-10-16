package com.gryffindor.frontend.scenes.mainscene.field.explains;

import java.util.List;

import com.gryffindor.backend.api.WordNetDictionary;
import com.gryffindor.backend.entities.Translation;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.frontend.scenes.mainscene.field.IController;
import com.gryffindor.frontend.scenes.mainscene.field.search.SearchController;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ExplainController implements IController {
  ExplainField explainField;
  HBox synonymsPane;

  /** Khởi tạo phần dịch. */
  public ExplainController(ExplainField explainField) {
    this.explainField = explainField;
    synonymsPane = new HBox();
    // ManagedUtils.bindVisible(explainPane);
    explainField.getPane().setVisible(false);
  }

  private void initSynoymsPane(Word word) {
    if (word == null) {
      return;
    }

    List<String> synonyms = WordNetDictionary.getSynonyms(word);

    if (synonyms == null) {
      return;
    }

    for (String syn : synonyms) {
      synonymsPane.getChildren().add(initSynonymButtons(syn));
    }
  }

  private Button initSynonymButtons(String syn) {
    Button button = new Button(syn);
    button.getStyleClass().add("synonym-button");
    button.setOnAction(event -> SearchController.onSearchRequest(button, button.getText()));
    return button;
  }

  private void clear() {
    synonymsPane.getChildren().clear();
    explainField.getTranslationFields().clear();
    explainField.getPane().getChildren().clear();
  }

  /**
   * Khởi tạo các thuộc tính liên quan đến {@link Word}.
   */
  public void setWord(Word word) {
    clear();
    explainField.getPane().setVisible(true);

    if (!word.getTranslations().isEmpty()) {
      for (Translation translation : word.getTranslations()) {
        TranslationField translationField = new TranslationField();
        translationField.getController().setTranslation(word, translation);

        explainField.getTranslationFields().add(translationField);

        explainField.getPane().getChildren().add(translationField.getPane());
      }
    }

    explainField.getWordClass().setText(word.getWordClass());

    if (!word.getSource().equals(Word.Source.GOOGLE)) {
      initSynoymsPane(word);
      explainField.getPane().getChildren().add(synonymsPane);
    }
  }
}
