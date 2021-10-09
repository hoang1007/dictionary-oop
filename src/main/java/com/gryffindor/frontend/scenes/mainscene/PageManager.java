package com.gryffindor.frontend.scenes.mainscene;

import com.gryffindor.frontend.scenes.mainscene.page.HomePage;
import com.gryffindor.frontend.scenes.mainscene.page.IPage;
import com.gryffindor.frontend.scenes.mainscene.page.LoadingPage;
import com.gryffindor.frontend.scenes.mainscene.page.SettingPage;
import com.gryffindor.frontend.scenes.mainscene.page.ToolsPage;
import com.gryffindor.frontend.utils.ManagedUtils;

public class PageManager {
  public static final PageManager INSTANCE = new PageManager();

  private IPage showingPage;
  private IPage oldPage;

  private HomePage homePage;
  private LoadingPage loadingPage;
  private SettingPage settingPage;
  private ToolsPage toolsPage;

  public PageManager setHomePage(HomePage homePage) {
    ManagedUtils.bindVisible(homePage.getPane());
    this.homePage = homePage;
    if (showingPage == null) {
      showingPage = homePage; // make home page is default
    }
    return this;
  }

  public PageManager setToolsPage(ToolsPage toolsPage) {
    ManagedUtils.bindVisible(toolsPage.getPane());
    this.toolsPage = toolsPage;
    toolsPage.getPane().setVisible(false); // auto hide tools page
    return this;
  }

  public PageManager setSettingsPage(SettingPage settingPage) {
    ManagedUtils.bindVisible(settingPage.getPane());
    this.settingPage = settingPage;
    settingPage.getPane().setVisible(false); // auto hide setting page
    return this;
  }

  public PageManager setLoadingPage(LoadingPage loadingPage) {
    ManagedUtils.bindVisible(loadingPage.getPane());
    this.loadingPage = loadingPage;
    loadingPage.getPane().setVisible(false); // auto hide setting page
    return this;
  }

  public void showPage(Class<? extends IPage> page) {
    System.out.println("showing " + page.getName());
    
    if (page.isAssignableFrom(HomePage.class)) {
      showPage(homePage);
    } else if (page.isAssignableFrom(LoadingPage.class)) {
      showPage(loadingPage);
    } else if (page.isAssignableFrom(SettingPage.class)) {
      showPage(settingPage);
    } else if (page.isAssignableFrom(ToolsPage.class)) {
      showPage(toolsPage);
    }
  }

  public void restorePage() {
    if (oldPage != null) {
      showingPage.getPane().setVisible(false); // hide showing page
      showingPage = oldPage; // restore page
      showingPage.getPane().setVisible(true); // show restore page
      oldPage = null;
    }
  }

  private void showPage(IPage newPage) {
    oldPage = showingPage; // backup old page
    
    if (!(newPage instanceof LoadingPage)) {
      showingPage.getPane().setVisible(false); // hide old page
    }

    showingPage = newPage;
    showingPage.getPane().setVisible(true); // show new page
  }
}
