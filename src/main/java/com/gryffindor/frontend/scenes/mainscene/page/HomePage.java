package com.gryffindor.frontend.scenes.mainscene.page;

import com.gryffindor.frontend.event.WordEvent;
import com.gryffindor.frontend.scenes.mainscene.field.explains.ExplainsField;
import com.gryffindor.frontend.scenes.mainscene.field.search.SearchField;
import com.gryffindor.frontend.scenes.mainscene.field.translate.TranslateField;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HomePage implements IPage {
  private final VBox homePanel;

  private SearchField searchField;
  private TranslateField translateField;
  private ExplainsField explainsField;

  /** Construct. */
  public HomePage() {
    homePanel = new VBox();
    homePanel.setFillWidth(true);

    searchField = new SearchField();
    translateField = new TranslateField();
    explainsField = new ExplainsField();

    homePanel.getChildren().addAll(
        searchField.getPane(), translateField.getPane(), explainsField.getPane());

    handleWordEvent();
  }


  void handleWordEvent() {
    homePanel.addEventFilter(WordEvent.EVENTTYPE, new EventHandler<WordEvent>() {
      @Override
      public void handle(WordEvent event) {
        translateField.getController().setWord(event.getWord());
        explainsField.getController().setWords(List.of(event.getWord()));
      }
    });
  }

  public SearchField getSearchField() {
    return searchField;
  }

  public TranslateField getTranslateField() {
    return translateField;
  }

  public ExplainsField getExplainField() {
    return explainsField;
  }

  @Override
  public Pane getPane() {
    return homePanel;
  }
}
