package com.gryffindor.frontend.scenes.mainscene.field.search;

import java.util.concurrent.ExecutionException;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.backend.api.FireStore;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.frontend.event.WordEvent;
import com.gryffindor.frontend.scenes.mainscene.field.IController;
import com.gryffindor.frontend.utils.BlockingListUtils;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class SearchController implements IController {
  private SearchField searchField;
  private BlockingListUtils<Word> searchListUtils;

  /**
   * Khởi tạo controller cho seach field.
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
      if (event.getCode().equals(KeyCode.ENTER)) {
        onSearchRequest(searchField.getSearchBox(), searchField.getSearchBox().getText());
      }

      searchField.getSearchList().setVisible(false);
    });

    searchField.getSearchList().setOnMouseClicked(event -> {
      onSearchRequest(searchField.getSearchList(), searchField.getSearchList()
          .getSelectionModel().getSelectedItem().getWordTarget());

      searchField.getSearchList().setVisible(false);
    });
  }

  void historyMode() {
    searchField.getSearchList().setVisible(true);
  }

  void onSearchRequest(Node node, String wordTarget) {
    Word word = DictionaryApplication.INSTANCE.dictionaryManagement.dictionary.searchWord(wordTarget);

    node.fireEvent(new WordEvent(word));
  }
}
