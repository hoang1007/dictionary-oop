package com.gryffindor.frontend.scenes.mainscene.entities;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class NavigationBarField implements IEntity {
  private final HBox toolsBarPane;

  private Button homePage;
  private Button toolsPage;

  /** Khởi tạo. */
  public NavigationBarField() {
    toolsBarPane = new HBox();
    toolsBarPane.getStyleClass().add("nav-pane");

    initHomePage();
    initToolsPage();

    toolsBarPane.getChildren().addAll(homePage, toolsPage);
    toolsBarPane.setSpacing(20);
  }

  void initHomePage() {
    homePage = new Button("Home");
    homePage.getStyleClass().add("nav-button");
  }

  void initToolsPage() {
    toolsPage = new Button("Tools");
    toolsPage.getStyleClass().add("nav-button");
  }

  @Override
  public Pane getPane() {
    return toolsBarPane;
  }
}
