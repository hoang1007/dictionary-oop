package com.gryffindor.frontend.scenes.mainscene;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.scenes.mainscene.layout.homepage.HomePage;
import com.gryffindor.frontend.scenes.mainscene.layout.navigationbar.NavigationBar;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/** Scene chính của ứng dụng. */
public class MainScene {
  private final Scene mainScene;
  private final HBox rootPane;

  private final NavigationBar navigationBarField;
  private final HomePage homePage;

  /** Khởi tạo scene chính. */
  public MainScene() {
    rootPane = new HBox();
    rootPane.setFillHeight(true);
    rootPane.getStyleClass().add("root-pane");

    homePage = new HomePage();

    navigationBarField = new NavigationBar();
    navigationBarField.getController().setHomePage(homePage);
    
    setupLayout();

    mainScene = new Scene(rootPane);
    mainScene.getStylesheets().add(DictionaryApplication.INSTANCE.config.getStyle());
  }

  void setupLayout() {
    rootPane.getChildren().addAll(navigationBarField.getPane(), homePage.getPane());
    HBox.setHgrow(homePage.getPane(), Priority.ALWAYS);
  }

  public Scene getMainScene() {
    return mainScene;
  }
}
