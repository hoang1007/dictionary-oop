package com.gryffindor.frontend.scenes.mainscene.field.explains;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.scenes.mainscene.field.IField;
import com.gryffindor.frontend.utils.ImageUtils;
import com.gryffindor.frontend.utils.ManagedUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/** Lớp chứa phần giải thích. */
public class ExplainField implements IField {
  private final VBox explainPane;
  private final ExplainController controller;

  private Text wordClass;
  private ObservableList<TranslationField> translationFields;
  private Button addTranslationButton;

  /** Khởi tạo ExplainField. */
  public ExplainField() {
    explainPane = new VBox();
    explainPane.setAlignment(Pos.CENTER);
    explainPane.getStyleClass().add("explain-pane");

    initWordClass();
    initTranslationFields();
    initAddTranslationButton();

    controller = new ExplainController(this);
  }

  private void initWordClass() {
    wordClass = new Text();
    ManagedUtils.bindVisible(wordClass);
    wordClass.getStyleClass().add("word-class");

    explainPane.getChildren().add(wordClass);
  }

  private void initTranslationFields() {
    translationFields = FXCollections.observableArrayList();
  }

  private void initAddTranslationButton() {
    addTranslationButton = new Button("Add translation to this word");
    addTranslationButton.getStyleClass().add("add-trans-button");
    addTranslationButton.setTooltip(new Tooltip("Thêm bản dịch cho từ này"));

    ImageView img = ImageUtils.getFitSquareImage(
        DictionaryApplication.INSTANCE.config.getImagesPath() + "/plus.png", 30);

    addTranslationButton.setGraphic(img);

    explainPane.getChildren().add(addTranslationButton);
  }

  public Text getWordClass() {
    return wordClass;
  }

  public ObservableList<TranslationField> getTranslationFields() {
    return translationFields;
  }

  public Button getAddTransButton() {
    return addTranslationButton;
  }

  @Override
  public Pane getPane() {
    return explainPane;
  }

  @Override
  public ExplainController getController() {
    return controller;
  }
}
