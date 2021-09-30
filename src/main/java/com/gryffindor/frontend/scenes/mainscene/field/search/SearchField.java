package com.gryffindor.frontend.scenes.mainscene.field.search;

import com.gryffindor.backend.entities.Word;
import com.gryffindor.frontend.scenes.mainscene.field.IField;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SearchField implements IField {
  private final VBox searchPane;
  private final SearchController controller;

  private TextField searchBox;
  /** Lịch sử tìm kiếm và gợi ý khi nhập. */
  private ListView<Word> searchList;
  
  /** Khởi tạo SearchField. */
  public SearchField() {
    searchPane = new VBox();

    searchPane.getStyleClass().add("padding-pane");
  
    initSearchBox();
    initSearchList();

    searchPane.getChildren().addAll(searchBox, searchList);

    controller = new SearchController(this); // should be last since need to init other components
  }

  void initSearchBox() {
    searchBox = new TextField();
    searchBox.setPromptText("Tap to search...");
    searchBox.getStyleClass().add("search-box");
  }

  void initSearchList() {
    searchList = new ListView<>();
    searchList.getStyleClass().add("search-list");

    searchList.managedProperty().bind(searchList.visibleProperty());
  }

  @Override
  public Pane getPane() {
    return searchPane;
  }

  public TextField getSearchBox() {
    return searchBox;
  }

  /** Lịch sử tìm kiếm và gợi ý khi nhập. */
  public ListView<Word> getSearchList() {
    return searchList;
  }

  @Override
  public SearchController getController() {
    return controller;
  }
}
