package com.gryffindor.frontend.scenes.mainscene.entities;

import com.gryffindor.Language;
import com.gryffindor.backend.api.GoogleTranslator;
import com.gryffindor.frontend.IEntity;

import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class SearchField implements IEntity {
  private final HBox searchPane;
  private TextField searchBox;
  private Button searchButton;

  private double spacing = 2;

  /** Khởi tạo SearchField. */
  public SearchField() {
    searchPane = new HBox();
    searchPane.setSpacing(spacing);

    initSearchBox();
    initButton();

    searchPane.getChildren().addAll(searchBox, searchButton);
    searchPane.autosize();
  }

  void initButton() {
    searchButton = new Button();
    searchButton.getStyleClass().add("search-button");

    searchButton.setOnAction(event -> {
      String input = searchBox.getText();

      try {
        String explain = GoogleTranslator.translate(input, Language.DETECT, Language.VIETNAMESE);

        System.out.println(explain);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  void initSearchBox() {
    searchBox = new TextField();
    searchBox.getStyleClass().add("search-box");
  }

  @Override
  public Pane getPane() {
    return searchPane;
  }
}
