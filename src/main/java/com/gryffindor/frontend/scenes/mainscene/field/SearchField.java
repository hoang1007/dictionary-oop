package com.gryffindor.frontend.scenes.mainscene.field;

import com.gryffindor.backend.entities.Word;
import com.gryffindor.frontend.utils.BlockingListUtils;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SearchField implements IField {
  private final VBox searchPane;
  private TextField searchBox;
  /** Lịch sử tìm kiếm và gợi ý khi nhập. */
  private ListView<Word> searchList;

  private BlockingListUtils<Word> searchListUtils;
  
  /** Khởi tạo SearchField. */
  public SearchField() {
    searchPane = new VBox();
    searchPane.getStyleClass().add("padding-pane");
  
    initSearchBox();
    initSearchList();

    searchPane.getChildren().addAll(searchBox, searchList);
  }

  void initSearchBox() {
    searchBox = new TextField();
    searchBox.setPromptText("Tap to search...");
    searchBox.getStyleClass().add("search-box");
  }

  void initSearchList() {
    searchList = new ListView<>();
    searchList.getStyleClass().add("search-list");

    searchListUtils = new BlockingListUtils<>(4, searchList); // giới hạn 4 phần tử

    searchListUtils.addAll(new Word("banana", "chuoi"), 
        new Word("apple", "tao"), 
        new Word("orange", "cam"));
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

  /** Công cụ giúp thao tác trên danh sách với giới hạn số lượng. */
  public BlockingListUtils<Word> getSearchListUtils() {
    return searchListUtils;
  }
}
