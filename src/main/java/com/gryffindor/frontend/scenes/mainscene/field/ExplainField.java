package com.gryffindor.frontend.scenes.mainscene.field;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ExplainField implements IField {
  private final Pane parentPane;
  private final VBox explainPane;

  private Label wordMeaning;
  private Text wordClass;
  private Label wordDefinition;
  private Label exampleSentence;
  private List<Button> synonymsButtons;


  /** Khởi tạo ExplainField. */
  public ExplainField() {
    explainPane = new VBox();
    explainPane.getStyleClass().add("text-pane");

    initWordMeaning();
    initWordClass();
    initWordDefinition();

    explainPane.getChildren().addAll(wordClass, wordMeaning, wordDefinition);

    parentPane = new VBox();
    parentPane.getStyleClass().add("padding-pane");
    parentPane.getChildren().add(explainPane);
  }

  void initWordMeaning() {
    wordMeaning = new Label("hoa");
    wordMeaning.setWrapText(true);
    wordMeaning.getStyleClass().add("word-meaning");
  }

  void initWordClass() {
    wordClass = new Text("danh từ");
    wordClass.getStyleClass().add("word-class");
  }

  void initWordDefinition() {
    wordDefinition = 
      new Label("cơ quan sinh sản hữu tính của cây hạt kín, thường có màu sắc và hương thơm");

    wordDefinition.setWrapText(true);
    wordDefinition.setTextAlignment(TextAlignment.LEFT);
  }

  public Label getWordMeaning() {
    return wordMeaning;
  }

  @Override
  public Pane getPane() {
    return parentPane;
  }
}
