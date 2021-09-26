package com.gryffindor.frontend.scenes.mainscene.layout.navigationbar;

import com.gryffindor.frontend.scenes.mainscene.layout.IPage;
import com.gryffindor.frontend.scenes.mainscene.layout.IPageController;
import com.gryffindor.frontend.scenes.mainscene.layout.homepage.HomePage;
import com.gryffindor.frontend.scenes.mainscene.layout.toolspage.ToolsPage;

import javafx.scene.control.Button;

public class NavigationBarController implements IPageController {
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

  /**
   * Link home page to navigation bar.
   * 
   * @param homePage home page to link
   */
  public void setHomePage(HomePage homePage) {
    homePage.getPane().managedProperty().bind(homePage.getPane().visibleProperty());

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
    toolsPage.getPane().managedProperty().bind(toolsPage.getPane().visibleProperty());

    setPresentToolsPageonClick(toolsPage);

    toolsPage.getPane().setVisible(false); // auto hide tools page
  }

  void setShowPage(IPage page) {
    showingPage.getPane().setVisible(false); // hide old page

    showingPage = page;

    showingPage.getPane().setVisible(true); // show new page
  }
}
