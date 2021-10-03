package com.gryffindor.frontend.scenes.mainscene.field.search;

import com.gryffindor.backend.entities.Word;
import com.gryffindor.frontend.event.WordEvent;
import com.gryffindor.frontend.scenes.mainscene.field.IController;
import com.gryffindor.frontend.utils.BlockingListUtils;
import com.gryffindor.Language;
import com.gryffindor.backend.api.GoogleTranslator;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class SearchController implements IController {
  private SearchField searchField;
  private BlockingListUtils<Word> searchListUtils;

  /**
   * Khởi tạo controller cho seach field.
   * 
   * @param searchField search field muốn control
   */
  public SearchController(SearchField searchField) {
    this.searchField = searchField;
    searchListUtils = new BlockingListUtils<Word>(4, searchField.getSearchList().getItems());

    onBegin();
    actionOnSearchBegin();
    actionOnSearching();
    actionOnSearchFinished();
  }

  void onBegin() {
    searchField.getSearchList().setVisible(false); // mặc định ẩn
  }

  void actionOnSearchBegin() {
    searchField.getSearchBox().setOnMouseClicked(event -> {
      if (searchField.getSearchBox().getText().length() == 0) {
        historyMode();
      }
    });
  }

  void actionOnSearching() {

    searchField.getSearchBox().textProperty().addListener((observable, oldValue, newValue) -> {
      // nếu search box trống
      // hiện lịch sử tìm kiếm
      if (searchField.getSearchBox().getText().length() == 0) {
        historyMode();
        // nếu không
        // hiện từ gợi ý
      } else {
        searchListUtils.set(0, new Word(newValue, ""));
      }

      searchField.getSearchList().setVisible(true); // enable search list
    });
  }

  void actionOnSearchFinished() {
    searchField.getSearchBox().setOnKeyPressed(event -> {
      // khi nhấn enter
      // lấy từ đã nhập trong search box
      try {
        if (event.getCode().equals(KeyCode.ENTER)) {
          onSearchRequest(searchField.getSearchBox(), searchField.getSearchBox().getText());
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

      searchField.getSearchList().setVisible(false);
    });

    searchField.getSearchList().setOnMouseClicked(event -> {
      try {
        onSearchRequest(searchField.getSearchList(),
            searchField.getSearchList().getSelectionModel().getSelectedItem().getWordTarget());
      } catch (IOException e) {
        e.printStackTrace();
      }
      searchField.getSearchList().setVisible(false);
    });
  }

  void historyMode() {
    searchField.getSearchList().setVisible(true);
  }

  void onSearchRequest(Node node, String wordTarget) throws IOException {
    Word word = new Word(wordTarget, GoogleTranslator.translate(wordTarget, Language.ENGLISH, Language.VIETNAMESE));

    node.fireEvent(new WordEvent(word));
  }
}
