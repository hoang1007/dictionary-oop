package com.gryffindor.frontend.scenes.mainscene.field.navigationbar;

import com.gryffindor.frontend.scenes.mainscene.PageManager;
import com.gryffindor.frontend.scenes.mainscene.field.IController;
import com.gryffindor.frontend.scenes.mainscene.page.HomePage;
import com.gryffindor.frontend.scenes.mainscene.page.SettingPage;
import com.gryffindor.frontend.scenes.mainscene.page.ToolsPage;

import javafx.scene.control.Button;

public class NavigationBarController implements IController {
  private Button homeButton;
  private Button toolsButton;
  private Button settingsButton;

  /**
   * Quản lí các hiệu ứng và hành động của navigation bar.
   * 
   * @param navigationBar navigation bar
   */
  public NavigationBarController(NavigationBar navigationBar) {
    homeButton = navigationBar.getHomeButton();
    toolsButton = navigationBar.getToolsButton();
    settingsButton = navigationBar.getSettingsButton();

    setPresentHomePageonClick();
    setPresentToolsPageonClick();
    setPresentSettingPageonClick();
  }

  void setPresentHomePageonClick() {
    homeButton.setOnAction(event -> PageManager.INSTANCE.showPage(HomePage.class));
  }
 
  void setPresentToolsPageonClick() {
    toolsButton.setOnAction(event -> PageManager.INSTANCE.showPage(ToolsPage.class));
  }

  void setPresentSettingPageonClick() {
    settingsButton.setOnAction(event -> PageManager.INSTANCE.showPage(SettingPage.class));
  }
}
