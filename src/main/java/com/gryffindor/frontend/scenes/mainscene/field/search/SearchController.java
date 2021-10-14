package com.gryffindor.frontend.scenes.mainscene.field.search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.Language;
import com.gryffindor.Status;
import com.gryffindor.backend.api.GoogleTranslator;
import com.gryffindor.backend.entities.Translation;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.backend.libraries.BinarySearch;
import com.gryffindor.backend.utils.TextUtils;
import com.gryffindor.frontend.event.WordEvent;
import com.gryffindor.frontend.scenes.mainscene.PageManager;
import com.gryffindor.frontend.scenes.mainscene.field.IController;
import com.gryffindor.frontend.scenes.mainscene.page.LoadingPage;
import com.gryffindor.frontend.utils.FileChooserWindow;

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

  // ấn vào icon ảnh
  void actionOnClickImageSearch() {
    searchField.getImageSearchButton().setOnAction(event -> {

      File img = new FileChooserWindow("Choose image", "image")
          .setExtensionFilter(new ExtensionFilter("PNG", "*.png"), new ExtensionFilter("JPEG", "*.jpg")).getOpenFile();

      PageManager.INSTANCE.showPage(LoadingPage.class);

      new Thread(() -> {
        try {
          String content = TextUtils.fromImage(img);

          String trans = GoogleTranslator.translate(content, Language.DETECT, Language.VIETNAMESE);

          Word word = new Word(content);
          word.addTranslation(new Translation(trans));

          Platform.runLater(() -> searchField.getImageSearchButton().fireEvent(new WordEvent(word)));
        } catch (Exception e) {
          DictionaryApplication.INSTANCE.exceptionHandler.add(e);
          e.printStackTrace();
        } finally {
          Platform.runLater(() -> PageManager.INSTANCE.restorePage());
        }
      }).start();
    });
  }

  void historyMode() {
    final int MAX_WORDS_COUNT = 5;
    System.out.println("History");
    searchField.getSearchList().setVisible(true);

    searchField.getSearchList().getItems()
        .setAll(history.size() < MAX_WORDS_COUNT ? history : history.subList(0, MAX_WORDS_COUNT));
  }

  public static void onSearchRequest(Node node, String wordTarget) {
    PageManager.INSTANCE.showPage(LoadingPage.class);

    new Thread(() -> {
      try {
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
        } else {
          DictionaryApplication.INSTANCE.exceptionHandler.add(
            new NullPointerException("Không tìm thấy " + wordTarget + " trong từ điển"));
        }
      } catch (NullPointerException e) {
        DictionaryApplication.INSTANCE.exceptionHandler.add(e);
        e.printStackTrace();
      } finally {
        Platform.runLater(() -> PageManager.INSTANCE.restorePage());
      }
    }).start();
  }
}