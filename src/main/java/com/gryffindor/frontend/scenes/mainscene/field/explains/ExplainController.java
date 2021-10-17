package com.gryffindor.frontend.scenes.mainscene.field.explains;

import com.gryffindor.backend.api.WordNetDictionary;
import com.gryffindor.backend.entities.Translation;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.frontend.scenes.mainscene.field.IController;
import com.gryffindor.frontend.scenes.mainscene.field.search.SearchController;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ExplainController implements IController {
  ExplainField explainField;
  HBox synonymsPane;
  Word word;

  /** Khởi tạo phần dịch. */
  public ExplainController(ExplainField explainField) {
    this.explainField = explainField;
    synonymsPane = new HBox();
    // ManagedUtils.bindVisible(explainPane);
    explainField.getPane().setVisible(false);

    onClickAddTransButton();
  }

  // Khởi tạo hành động khi click nút thêm bản dịch
  private void onClickAddTransButton() {
    Button addTransButton = explainField.getAddTransButton();

    addTransButton.setOnAction(event -> {
      // Tạo một vùng dịch mới
      TranslationField transField = new TranslationField();
      transField.getWordExplain().setPromptText("Click here to add a translation");
      transField.getWordExplain().setEditable(true);

      transField.getController().setTranslation(word, new Translation());

      // thêm vùng dịch mới vào trước nút thêm bản dịch
      int addIndex = explainField.getPane().getChildren().size() - 2;
      explainField.getPane().getChildren().add(addIndex, transField.getPane());
    });
  }

  /**
   * Hàm khởi tạo.
   * 
   * @param word
   */
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

  /**
   * Khởi tạo các nút chứa từ đồng nghĩa.
   * 
   * @param syn
   * @return Button
   */
  private Button initSynonymButtons(String syn) {
    Button button = new Button(syn);
    button.getStyleClass().add("synonym-button");

    // Khi nhấn nút sẽ gửi request dịch từ có trong nút
    button.setOnAction(event -> SearchController.onSearchRequest(button, button.getText()));
    return button;
  }

  // Xóa hết các node có trong pane
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
    this.word = word;

    explainField.getPane().setVisible(true);

    // Đặt các bản dịch
    if (!word.getTranslations().isEmpty()) {
      for (Translation translation : word.getTranslations()) {
        TranslationField translationField = new TranslationField();
        translationField.getController().setTranslation(word, translation);

        explainField.getTranslationFields().add(translationField);

        explainField.getPane().getChildren().add(translationField.getPane());
      }
    }

    // Đặt loại từ
    explainField.getWordClass().setText(word.getWordClass());

    // Nếu nguồn tìm kiếm từ google
    // Không thực hiện tìm kiếm các từ đồng nghĩa
    // vì có thể từ tìm kiếm là đoạn văn
    if (!word.getSource().equals(Word.Source.GOOGLE)) {
      initSynoymsPane(word);
      explainField.getPane().getChildren().add(synonymsPane);
    }

    // thêm nút thêm bản dịch sau khi bị xóa bởi hàm clear
    explainField.getPane().getChildren().add(explainField.getAddTransButton());
  }
}
