package com.gryffindor.frontend.scenes.mainscene.field.search;

import java.util.ArrayList;
import java.util.List;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.backend.libraries.BinarySearch;
import com.gryffindor.frontend.event.WordEvent;
import com.gryffindor.frontend.scenes.mainscene.field.IController;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class SearchController implements IController {
  private SearchField searchField;
  private List<Word> history;

  /**
   * Khởi tạo controller cho seach field.
   * 
   * @param searchField search field muốn control
   */
  public SearchController(SearchField searchField) {
    this.searchField = searchField;
    history = new ArrayList<>();

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
      searchField.getSearchList().setVisible(true); // enable search list
      // bat dau nhap
      if (oldValue.length() == 0 && newValue.length() == 1) {
        BinarySearch.setWordList(
            DictionaryApplication.INSTANCE.getDictionaryManagement().getDictionary().getWordList(newValue));
      }

      // nếu search box trống
      // hiện lịch sử tìm kiếm
      if (newValue.length() == 0) {
        historyMode();
        // nếu không
        // hiện từ gợi ý
      } else {
        List<Word> wordsSuggest = BinarySearch.searchAdvanced(newValue, newValue.length() - oldValue.length());

        searchField.getSearchList().getItems().setAll(wordsSuggest);
      }
    });
  }

  void actionOnSearchFinished() {
    searchField.getSearchBox().setOnKeyPressed(event -> {
      // khi nhấn enter
      // lấy từ đã nhập trong search box
      if (event.getCode().equals(KeyCode.ENTER)) {
        onSearchRequest(searchField.getSearchBox(), searchField.getSearchBox().getText());
        searchField.getSearchList().setVisible(false);
      }
    });

    searchField.getSearchList().setOnMouseClicked(event -> {
      onSearchRequest(searchField.getSearchList(),
          searchField.getSearchList().getSelectionModel().getSelectedItem().getWordTarget());

      searchField.getSearchList().setVisible(false);
    });
  }

  void historyMode() {
    final int MAX_WORDS_COUNT = 5;
    System.out.println("History");
    searchField.getSearchList().setVisible(true);

    searchField.getSearchList().getItems()
        .setAll(history.size() < MAX_WORDS_COUNT ? history : history.subList(0, MAX_WORDS_COUNT));
  }

  void onSearchRequest(Node node, String wordTarget) {
    try {
      Word word = DictionaryApplication.INSTANCE.dictionaryManagement.dictionary.searchWord(wordTarget);
      history.add(word);

      node.fireEvent(new WordEvent(word));
    } catch (Exception e) {
      System.out.println("Not found");
    }
  }
}
