package com.gryffindor.frontend.scenes.mainscene.layout.navigationbar;

import com.gryffindor.frontend.scenes.mainscene.layout.IPageController;
import com.gryffindor.frontend.scenes.mainscene.layout.homepage.HomePage;

import javafx.scene.control.Button;

public class NavigationBarController implements IPageController {
  private Button homeButton;
  private Button toolsButton;
  private Button settingsButton;

  /**
   * Quản lí các hiệu ứng và hành động của navigation bar.
   * @param navigationBar navigation bar
   */
  public NavigationBarController(NavigationBar navigationBar) {
    homeButton = navigationBar.getHomeButton();
    toolsButton = navigationBar.getToolsButton();
    settingsButton = navigationBar.getSettingsButton();
  }

  void setPresentHomePageonClick(HomePage homePage) {
    homeButton.setOnAction(event -> {
      homePage.getPane().setVisible(false);
    });
  }

  public void setHomePage(HomePage homePage) {
    setPresentHomePageonClick(homePage);
  }
}
