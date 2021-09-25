package com.gryffindor.frontend.scenes.mainscene.field;

import com.gryffindor.backend.entities.Word;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SearchField implements IField {
  private final VBox searchPane;
  private TextField searchBox;
  /** Lịch sử tìm kiếm và gợi ý khi nhập. */
  private ListView<Word> searchList;
  
  public final String searchTextHolder = "Tap to search...";

  /** Khởi tạo SearchField. */
  public SearchField() {
    searchPane = new VBox();
    searchPane.getStyleClass().add("padding-pane");
  
    initSearchBox();
    initSearchList();

    searchPane.getChildren().addAll(searchBox, searchList);
  }

  void initSearchBox() {
    searchBox = new TextField(searchTextHolder);
    searchBox.getStyleClass().add("search-box");
  }

  void initSearchList() {
    searchList = new ListView<>();
    searchList.getStyleClass().add("search-list");
  
    searchList.getItems().addAll(new Word("banana", "chuoi"), new Word("apple", "tao"));
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
}
