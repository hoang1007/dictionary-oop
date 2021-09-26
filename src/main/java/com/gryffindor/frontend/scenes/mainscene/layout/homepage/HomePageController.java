package com.gryffindor.frontend.scenes.mainscene.layout.homepage;

import com.gryffindor.backend.entities.Word;
import com.gryffindor.frontend.scenes.mainscene.field.ExplainField;
import com.gryffindor.frontend.scenes.mainscene.field.SearchField;
import com.gryffindor.frontend.scenes.mainscene.field.TranslateField;
import com.gryffindor.frontend.scenes.mainscene.layout.IPageController;

import javafx.scene.input.KeyCode;

public class HomePageController implements IPageController {
  private SearchField searchField;
  private ExplainField explainField;
  private TranslateField translateField;

  /** Khởi tạo các hiệu ứng và hành động trên home page. */
  public HomePageController(HomePage homePage) {
    searchField = homePage.getSearchField();
    explainField = homePage.getExplainField();
    translateField = homePage.getTranslateField();

    initHideProperty();

    setOnBegin();

    setActionOnSearchBegin();
    setActionOnSearching();
    setActionOnSearchFinished();
  }
  
  void initHideProperty() {
    translateField.getPane().managedProperty().bind(translateField.getPane().visibleProperty());
    explainField.getPane().managedProperty().bind(explainField.getPane().visibleProperty());
    searchField.getSearchList().managedProperty()
      .bind(searchField.getSearchList().visibleProperty());

    searchField.getSearchList().setVisible(false); // auto hide
  }

  void setOnBegin() {
    explainField.getPane().setVisible(false);
    searchField.getSearchList().setVisible(false);
    translateField.getPane().setVisible(false);
  }
  
  void setActionOnSearchBegin() {
    searchField.getSearchBox().setOnMouseClicked(event -> {
      if (searchField.getSearchBox().getText().length() == 0) {
        setHistoryList();
      }
    });
  }

  void setActionOnSearching() {
    searchField.getSearchBox().textProperty().addListener((observable, oldValue, newValue) -> {
      // nếu search box trống
      // hiện lịch sử tìm kiếm
      if (searchField.getSearchBox().getText().length() == 0) {
        setHistoryList();
      // nếu không
      // hiện từ gợi ý
      } else {
        searchField.getSearchListUtils().set(0, new Word(newValue, ""));
      }
    
      enableSearchList(true);
    });
  }

  void setActionOnSearchFinished() {
    searchField.getPane().setOnKeyPressed(event -> {
      // khi nhấn enter
      // lấy từ đã nhập trong search box
      if (event.getCode().equals(KeyCode.ENTER)) {
        onSearchRequest(searchField.getSearchBox().getText());
      }
    });

    searchField.getSearchList().setOnMouseClicked(event -> 
        onSearchRequest(searchField.getSearchList()
        .getSelectionModel().getSelectedItem().getWordTarget()));
  }

  void enableSearchList(boolean enable) {
    explainField.getPane().setVisible(!enable);
    translateField.getPane().setVisible(!enable);
    searchField.getSearchList().setVisible(enable);
  }

  void onSearchRequest(String wordTarget) {
    enableSearchList(false);

    System.out.println(wordTarget);
  }

  void setHistoryList() {
    searchField.getSearchList().setVisible(true);

    searchField.getSearchListUtils().set(0, new Word("Your history", ""));
  }
}
