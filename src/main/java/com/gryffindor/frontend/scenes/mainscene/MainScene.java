package com.gryffindor.frontend.scenes.mainscene;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.scenes.mainscene.field.navigationbar.NavigationBar;
import com.gryffindor.frontend.scenes.mainscene.page.HomePage;
import com.gryffindor.frontend.scenes.mainscene.page.LoadingPage;
import com.gryffindor.frontend.scenes.mainscene.page.SettingPage;
import com.gryffindor.frontend.scenes.mainscene.page.ToolsPage;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

/** Scene chính của ứng dụng. */
public class MainScene {
  private final Scene mainScene;
  private final StackPane rootPane;

  private HBox mainPane; // pane of all page except loading page

  private final NavigationBar navigationBarField;
  private final HomePage homePage;
  private final ToolsPage toolsPage;
  private final SettingPage settingPage;
  private final LoadingPage loadingPage;

  /** Khởi tạo scene chính. */
  public MainScene() {
    rootPane = new StackPane();
    rootPane.getStyleClass().add("root-pane");

    homePage = new HomePage();
    toolsPage = new ToolsPage();
    settingPage = new SettingPage();
    loadingPage = new LoadingPage();
    navigationBarField = new NavigationBar();
    
    // Đặt các page cho page manager quản lý
    PageManager.INSTANCE.setHomePage(homePage).setSettingsPage(settingPage)
        .setToolsPage(toolsPage).setLoadingPage(loadingPage);

    setupLayout();


    mainScene = new Scene(rootPane);
    mainScene.getStylesheets().add(DictionaryApplication.INSTANCE.config.getStyle());
  }

  void setupLayout() {
    mainPane = new HBox();
    mainPane.setFillHeight(true);

    mainPane.getChildren().addAll(
        navigationBarField.getPane(), 
        homePage.getPane(), 
        toolsPage.getPane(),
        settingPage.getPane());
        
    HBox.setHgrow(homePage.getPane(), Priority.ALWAYS);
    HBox.setHgrow(toolsPage.getPane(), Priority.ALWAYS);
    HBox.setHgrow(settingPage.getPane(), Priority.ALWAYS);

    ScrollPane scroll = new ScrollPane(mainPane);
    scroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    scroll.setFitToHeight(true);
    scroll.setFitToWidth(true);
    
    rootPane.getChildren().addAll(scroll, loadingPage.getPane());
  }

  public Scene getMainScene() {
    return mainScene;
  }
}
