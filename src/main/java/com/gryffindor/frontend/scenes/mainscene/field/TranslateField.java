package com.gryffindor.frontend.scenes.mainscene.field;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TranslateField implements IField {
  private final VBox parentPane;
  private final BorderPane translatePane;
  private Text wordTarget;
  private Text pronouncedText;

  /** Khởi tạo TranslateField. */
  public TranslateField() {
    translatePane = new BorderPane();
    translatePane.getStyleClass().add("text-pane");

    initWordTarget();
    initPronouncedText();

    parentPane = new VBox();
    parentPane.getStyleClass().add("padding-pane");
    parentPane.getChildren().add(translatePane);
  }

  void initWordTarget() {
    wordTarget = new Text("flower");
    wordTarget.getStyleClass().add("word-header");
    translatePane.setTop(wordTarget);
  }

  void initPronouncedText() {
    pronouncedText = new Text("/ˈflauə/");

    translatePane.setLeft(pronouncedText);
  }

  @Override
  public Pane getPane() {
    return parentPane;
  }

  public Text getWordTarget() {
    return wordTarget;
  }

  public Text getPronouncedText() {
    return pronouncedText;
  }
}
