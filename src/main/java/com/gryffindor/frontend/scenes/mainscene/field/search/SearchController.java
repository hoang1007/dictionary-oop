package com.gryffindor.frontend.scenes.mainscene.field.search;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.Status;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.backend.libraries.BinarySearch;
import com.gryffindor.backend.utils.TextUtils;
import com.gryffindor.frontend.event.WordEvent;
import com.gryffindor.frontend.scenes.mainscene.PageManager;
import com.gryffindor.frontend.scenes.mainscene.field.IController;
import com.gryffindor.frontend.scenes.mainscene.page.LoadingPage;
import com.gryffindor.frontend.utils.FileChooserWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser.ExtensionFilter;

public class SearchController implements IController {
  private SearchField searchField;
  private static List<Word> history;

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
    actionOnClickImageSearch();
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

      // Bắt đầu nhập
      // Đặt danh sách tìm kiếm cho hàm tìm kiếm nhị phân
      if (oldValue.length() == 0 && newValue.length() == 1) {
        BinarySearch.setWordList(DictionaryApplication.INSTANCE
            .getDictionaryManagement().getDictionary().getWordList(newValue));
      }

      // nếu search box trống
      // hiện lịch sử tìm kiếm
      if (newValue.length() == 0) {
        historyMode();
        // nếu không
        // hiện từ gợi ý
      } else {
        List<Word> wordsSuggest = 
            BinarySearch.searchAdvanced(newValue, newValue.length() - oldValue.length());

        if (wordsSuggest != null) {
          searchField.getSearchList().getItems().setAll(wordsSuggest);
        }
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

  // Lấy đoạn văn từ ảnh và thực hiện tìm kiếm
  void actionOnClickImageSearch() {
    searchField.getImageSearchButton().setOnAction(event -> {

      File img = new FileChooserWindow("Choose image", "image").setExtensionFilter(
          new ExtensionFilter("PNG", "*.png"), new ExtensionFilter("JPEG", "*.jpg")).getOpenFile();

      String content = TextUtils.fromImage(img);

      onSearchRequest(searchField.getImageSearchButton(), content);
    });
  }

  void historyMode() {
    final int Max_Words_Count = 5;
    System.out.println("History");
    searchField.getSearchList().setVisible(true);
    
    // Lấy lịch sử tìm kiếm
    searchField.getSearchList().getItems()
        .setAll(history.size() < Max_Words_Count ? history : history.subList(0, Max_Words_Count));
  }

  /**
   * Hàm gửi yêu cầu tìm kiếm tới nguồn.
   * @param node node muốn fire event
   * @param wordTarget tìm muốn tìm kiếm
   */
  public static void onSearchRequest(Node node, String wordTarget) {
    PageManager.INSTANCE.showPage(LoadingPage.class);

    new Thread(() -> {
      Word word;
      Status status = DictionaryApplication.INSTANCE.getStatus();
      // if ONLINE
      if (status.equals(Status.ONLINE)) {
        System.out.println("Searching online");
        word = DictionaryApplication.INSTANCE.dictionaryManagement.searchWordOnline(wordTarget);

      } else { // if OFFLINE
        System.out.println("Searching offline");
        word = DictionaryApplication.INSTANCE.dictionaryManagement.dictionaryLookup(wordTarget);
      }

      if (word != null) {
        history.add(word);
        Platform.runLater(() -> node.fireEvent(new WordEvent(word)));
        System.out.println("Fired event.");
      } else {
        // nếu không tìm kiếm được từ
        // tạo một từ rỗng để người dùng có thể thêm bản dịch
        Platform.runLater(() -> node.fireEvent(new WordEvent(new Word(wordTarget))));

        // Gửi thông báo không tìm thấy
        DictionaryApplication.INSTANCE.exceptionHandler
            .add(new NullPointerException("Không tìm thấy " + wordTarget + " trong từ điển"));
      }

      Platform.runLater(() -> PageManager.INSTANCE.restorePage());
    }).start();
  }
}