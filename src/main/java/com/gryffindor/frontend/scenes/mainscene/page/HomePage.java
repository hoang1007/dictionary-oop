package com.gryffindor.frontend.scenes.mainscene.page;

import com.gryffindor.frontend.event.WordEvent;
import com.gryffindor.frontend.scenes.mainscene.field.explains.ExplainField;
import com.gryffindor.frontend.scenes.mainscene.field.search.SearchField;
import com.gryffindor.frontend.scenes.mainscene.field.translate.TranslateField;

import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/** Trang chính nơi để dịch từ. */
public class HomePage implements IPage {
  private final VBox homePanel;

  private SearchField searchField;
  private TranslateField translateField;
  private ExplainField explainField;

  /** Construct. */
  public HomePage() {
    homePanel = new VBox();
    homePanel.setSpacing(10);
    homePanel.getStyleClass().add("padding-pane");
    homePanel.setFillWidth(true);

    searchField = new SearchField();
    translateField = new TranslateField();
    explainField = new ExplainField();

    homePanel.getChildren().addAll(searchField.getPane(), translateField.getPane(), explainField.getPane());

    handleWordEvent();
  }

  private void handleWordEvent() {
    homePanel.addEventFilter(WordEvent.EVENTTYPE, new EventHandler<WordEvent>() {
      @Override
      public void handle(WordEvent event) {
        translateField.getController().setWord(event.getWord());
        explainField.getController().setWord(event.getWord());
      }
    });
  }

  public SearchField getSearchField() {
    return searchField;
  }

  public TranslateField getTranslateField() {
    return translateField;
  }

  public ExplainField getExplainField() {
    return explainField;
  }

  @Override
  public Pane getPane() {
    return homePanel;
  }
}
