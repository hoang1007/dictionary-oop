package com.gryffindor.frontend.scenes.mainscene.layout.homepage;

import com.gryffindor.frontend.scenes.mainscene.field.ExplainsField;
import com.gryffindor.frontend.scenes.mainscene.field.SearchField;
import com.gryffindor.frontend.scenes.mainscene.field.TranslateField;
import com.gryffindor.frontend.scenes.mainscene.layout.IPage;
import com.gryffindor.frontend.scenes.mainscene.layout.IPageController;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HomePage implements IPage {
  private final VBox homePanel;

  private SearchField searchField;
  private TranslateField translateField;
  private ExplainsField explainsField;

  private HomePageController controller;

  /** Construct. */
  public HomePage() {
    homePanel = new VBox();
    homePanel.setFillWidth(true);
  
    searchField = new SearchField();
    translateField = new TranslateField();
    explainsField = new ExplainsField();

    homePanel.getChildren().addAll(
        searchField.getPane(),
        translateField.getPane(), 
        explainsField.getPane()
    );

    controller = new HomePageController(this);
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
  public IPageController getController() {
    return controller;
  }

  @Override
  public Pane getPane() {
    return homePanel;
  }
}
