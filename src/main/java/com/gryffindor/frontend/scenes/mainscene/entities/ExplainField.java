package com.gryffindor.frontend.scenes.mainscene.entities;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ExplainField implements IEntity {
  private final Pane parentPane;
  private final VBox explainPane;
  private Text wordMeaning;

  /** Khởi tạo ExplainField. */
  public ExplainField() {
    explainPane = new VBox();
    explainPane.getStyleClass().add("text-pane");

    initWordMeaning();

    explainPane.getChildren().addAll(wordMeaning);

    parentPane = new VBox();
    parentPane.getStyleClass().add("padding-pane");
    parentPane.getChildren().add(explainPane);
  }

  void initWordMeaning() {
    wordMeaning = new Text("hoa");
    wordMeaning.getStyleClass().add("word_header");
  }

  @Override
  public Pane getPane() {
    return parentPane;
  }
}
