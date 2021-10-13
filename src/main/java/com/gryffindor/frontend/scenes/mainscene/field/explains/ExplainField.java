package com.gryffindor.frontend.scenes.mainscene.field.explains;

import com.gryffindor.frontend.scenes.mainscene.field.IField;
import com.gryffindor.frontend.utils.ManagedUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/** Lớp chứa phần giải thích. */
public class ExplainField implements IField {
  private final VBox explainPane;
  private final ExplainController controller;

  private Text wordClass;
  private ObservableList<TranslationField> translationFields;

  /** Khởi tạo ExplainField. */
  public ExplainField() {
    explainPane = new VBox();
    explainPane.getStyleClass().add("explain-pane");

    initWordClass();
    initTranslationFields();

    controller = new ExplainController(this);
  }

  void initWordClass() {
    wordClass = new Text();
    ManagedUtils.bindVisible(wordClass);
    wordClass.getStyleClass().add("word-class");

    explainPane.getChildren().add(wordClass);
  }

  void initTranslationFields() {
    translationFields = FXCollections.observableArrayList();
  }

  public Text getWordClass() {
    return wordClass;
  }

  public ObservableList<TranslationField> getTranslationFields() {
    return translationFields;
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
