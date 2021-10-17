package com.gryffindor.frontend.scenes.mainscene.field.search;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.frontend.scenes.mainscene.field.IField;
import com.gryffindor.frontend.utils.ImageUtils;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class SearchField implements IField {
  private final GridPane searchPane;
  private final SearchController controller;

  private TextField searchBox;
  private Button imageSearch;
  /** Lịch sử tìm kiếm và gợi ý khi nhập. */
  private ListView<Word> searchList;
  
  /** Khởi tạo SearchField. */
  public SearchField() {
    searchPane = new GridPane();
    searchPane.setHgap(5);

    initSearchBox();
    initSearchList();
    initImageSearchButton();

    controller = new SearchController(this); // should be last since need to init other components
  }

  private void initSearchBox() {
    searchBox = new TextField();
    searchBox.setPromptText("Tap to search...");
    searchBox.getStyleClass().add("search-box");

    searchPane.getChildren().add(searchBox);
    GridPane.setConstraints(searchBox, 0, 0);
    GridPane.setHgrow(searchBox, Priority.ALWAYS);
  }

  private void initImageSearchButton() {
    imageSearch = new Button();
    imageSearch.getStyleClass().add("search-button");
    ImageView view = ImageUtils.getFitSquareImage(
          DictionaryApplication.INSTANCE.config.getImagesPath() + "/copy.png", 30);
    imageSearch.setGraphic(view);
    imageSearch.setTooltip(new Tooltip("Dịch bằng hình ảnh"));

    searchPane.getChildren().add(imageSearch);
    GridPane.setConstraints(imageSearch, 1, 0);
  }

  private void initSearchList() {
    searchList = new ListView<>();
    searchList.getStyleClass().add("search-list");

    searchList.managedProperty().bind(searchList.visibleProperty());

    searchPane.getChildren().add(searchList);
    GridPane.setConstraints(searchList, 0, 1);
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

  public Button getImageSearchButton() {
    return imageSearch;
  }

  @Override
  public SearchController getController() {
    return controller;
  }
}
