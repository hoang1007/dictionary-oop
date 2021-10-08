package com.gryffindor.frontend.scenes.mainscene.field.navigationbar;

import com.gryffindor.frontend.scenes.mainscene.field.IController;
import com.gryffindor.frontend.scenes.mainscene.page.HomePage;
import com.gryffindor.frontend.scenes.mainscene.page.IPage;
import com.gryffindor.frontend.scenes.mainscene.page.SettingPage;
import com.gryffindor.frontend.scenes.mainscene.page.ToolsPage;
import com.gryffindor.frontend.utils.ManagedUtils;

import javafx.scene.control.Button;

public class NavigationBarController implements IController {
  private Button homeButton;
  private Button toolsButton;
  private Button settingsButton;

  private IPage showingPage;

  /**
   * Quản lí các hiệu ứng và hành động của navigation bar.
   * 
   * @param navigationBar navigation bar
   */
  public NavigationBarController(NavigationBar navigationBar) {
    homeButton = navigationBar.getHomeButton();
    toolsButton = navigationBar.getToolsButton();
    settingsButton = navigationBar.getSettingsButton();
  }

  void setPresentHomePageonClick(HomePage homePage) {
    homeButton.setOnAction(event -> {
      System.out.println("Home button clicked");

      setShowPage(homePage);
    });
  }

  void setPresentToolsPageonClick(ToolsPage toolsPage) {
    toolsButton.setOnAction(event -> {
      System.out.println("Tools button clicked");

      setShowPage(toolsPage);
    });
  }

  void setPresentSettingPageonClick(SettingPage settingPage) {
    settingsButton.setOnAction(event -> setShowPage(settingPage));
  }

  /**
   * Link home page to navigation bar.
   * 
   * @param homePage home page to link
   */
  public void setHomePage(HomePage homePage) {
    ManagedUtils.bindVisible(homePage.getPane());

    setPresentHomePageonClick(homePage);

    if (showingPage == null) {
      showingPage = homePage; // make home page is default
    }
  }

  /**
   * Link tools page to navigation bar.
   * 
   * @param toolsPage tools page to link
   */
  public void setToolsPage(ToolsPage toolsPage) {
    ManagedUtils.bindVisible(toolsPage.getPane());

    setPresentToolsPageonClick(toolsPage);

    toolsPage.getPane().setVisible(false); // auto hide tools page
  }

  public void setSettingsPage(SettingPage settingPage) {
    ManagedUtils.bindVisible(settingPage.getPane());

    setPresentSettingPageonClick(settingPage);

    settingPage.getPane().setVisible(false); // auto hide setting page
  }

  void setShowPage(IPage page) {
    showingPage.getPane().setVisible(false); // hide old page

    showingPage = page;

    showingPage.getPane().setVisible(true); // show new page
  }
}
