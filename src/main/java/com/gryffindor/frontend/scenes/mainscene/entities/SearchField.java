package com.gryffindor.frontend.scenes.mainscene.entities;

import com.gryffindor.backend.entities.Word;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SearchField implements IEntity {
  private final VBox searchPane;
  private TextField searchBox;
  private ListView<Word> autocompleteList;

  /** Khởi tạo SearchField. */
  public SearchField() {
    searchPane = new VBox();
    searchPane.getStyleClass().add("padding-pane");
  
    initSearchBox();
    initFloatingAutocomplete();

    searchPane.getChildren().add(searchBox);
  }

  void initSearchBox() {
    searchBox = new TextField("Tap to enter text");
    searchBox.getStyleClass().add("search-box");
  }

  void initFloatingAutocomplete() {
    autocompleteList = new ListView<>();
    autocompleteList.getStyleClass().add("autocomplete-list");
  
    autocompleteList.setOnMouseClicked(event -> {
      Word chosenWord = autocompleteList.getSelectionModel().getSelectedItem();
      System.out.println(chosenWord);
    });

    autocompleteList.getItems().addAll(new Word("banana", "chuoi"), new Word("apple", "tao"));
  }

  public void disableAutocomplete() {
    searchPane.getChildren().remove(autocompleteList);
  }

  public void enableAutocomplete() {
    searchPane.getChildren().add(autocompleteList);
  }

  @Override
  public Pane getPane() {
    return searchPane;
  }

  public TextField getSearchBox() {
    return searchBox;
  }

  public ListView<Word> getAutocompleteList() {
    return autocompleteList;
  }
}
