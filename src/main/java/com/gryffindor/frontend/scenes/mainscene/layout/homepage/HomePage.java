package com.gryffindor.frontend.scenes.mainscene.layout.homepage;

import com.gryffindor.frontend.scenes.mainscene.field.ExplainField;
import com.gryffindor.frontend.scenes.mainscene.field.SearchField;
import com.gryffindor.frontend.scenes.mainscene.field.TranslateField;
import com.gryffindor.frontend.scenes.mainscene.layout.IPage;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HomePage implements IPage {
  private final VBox homePanel;

  private SearchField searchField;
  private TranslateField translateField;
  private ExplainField explainField;

  private HomePageController controller;

  /** Construct. */
  public HomePage() {
    homePanel = new VBox();
    homePanel.setFillWidth(true);
  
    searchField = new SearchField();
    translateField = new TranslateField();
    explainField = new ExplainField();

    homePanel.getChildren().addAll(
        searchField.getPane(),
        translateField.getPane(), 
        explainField.getPane()
    );

    controller = new HomePageController(this);
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
  
  public HomePageController getController() {
    return controller;
  }

  @Override
  public Pane getPane() {
    return homePanel;
  }
}
