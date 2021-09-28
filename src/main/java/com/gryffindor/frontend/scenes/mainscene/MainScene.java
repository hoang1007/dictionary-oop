package com.gryffindor.frontend.scenes.mainscene;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.scenes.mainscene.field.navigationbar.NavigationBar;
import com.gryffindor.frontend.scenes.mainscene.page.HomePage;
import com.gryffindor.frontend.scenes.mainscene.page.ToolsPage;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/** Scene chính của ứng dụng. */
public class MainScene {
  private final Scene mainScene;
  private final HBox rootPane;

  private final NavigationBar navigationBarField;
  private final HomePage homePage;
  private final ToolsPage toolsPage;

  /** Khởi tạo scene chính. */
  public MainScene() {
    rootPane = new HBox();
    rootPane.setFillHeight(true);
    rootPane.getStyleClass().add("root-pane");

    homePage = new HomePage();
    toolsPage = new ToolsPage();

    navigationBarField = new NavigationBar();
    navigationBarField.getController().setHomePage(homePage);
    navigationBarField.getController().setToolsPage(toolsPage);
    
    setupLayout();

    mainScene = new Scene(rootPane);
    mainScene.getStylesheets().add(DictionaryApplication.INSTANCE.config.getStyle());
  }

  void setupLayout() {
    rootPane.getChildren().addAll(
        navigationBarField.getPane(), 
        homePage.getPane(), 
        toolsPage.getPane());
        
    HBox.setHgrow(homePage.getPane(), Priority.ALWAYS);
  }

  public Scene getMainScene() {
    return mainScene;
  }
}
