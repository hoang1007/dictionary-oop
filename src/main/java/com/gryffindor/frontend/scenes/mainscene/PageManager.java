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

  /** Đặt trang chủ và đặt các hiển thị mặc định. */
  public PageManager setHomePage(HomePage homePage) {
    ManagedUtils.bindVisible(homePage.getPane());
    this.homePage = homePage;
    if (showingPage == null) {
      showingPage = homePage; // make home page is default
    }
    return this;
  }

  /** Đặt trang công cụ và đặt các hiển thị mặc định. */
  public PageManager setToolsPage(ToolsPage toolsPage) {
    ManagedUtils.bindVisible(toolsPage.getPane());
    this.toolsPage = toolsPage;
    toolsPage.getPane().setVisible(false); // auto hide tools page
    return this;
  }

  /** Đặt trang cài đặt và đặt các hiển thị mặc định. */
  public PageManager setSettingsPage(SettingPage settingPage) {
    ManagedUtils.bindVisible(settingPage.getPane());
    this.settingPage = settingPage;
    settingPage.getPane().setVisible(false); // auto hide setting page
    return this;
  }

  /** Đặt trang loading và đặt các hiển thị mặc định. */
  public PageManager setLoadingPage(LoadingPage loadingPage) {
    ManagedUtils.bindVisible(loadingPage.getPane());
    this.loadingPage = loadingPage;
    loadingPage.getPane().setVisible(false); // auto hide setting page
    return this;
  }

  /** Hiển thị một trang có trong page manager. */
  public void showPage(Class<? extends IPage> page) {
    System.out.println("showing " + page.getName());

    if (page.isAssignableFrom(HomePage.class)) {
      showIPage(homePage);
    } else if (page.isAssignableFrom(LoadingPage.class)) {
      showIPage(loadingPage);
    } else if (page.isAssignableFrom(SettingPage.class)) {
      showIPage(settingPage);
    } else if (page.isAssignableFrom(ToolsPage.class)) {
      showIPage(toolsPage);
    }
  }

  /** Khôi phục lại trang trước đó sau khi dùng 
   * {@link PageManager#showIPage(Class)}. */
  public void restorePage() {
    if (oldPage != null) {
      showingPage.getPane().setVisible(false); // hide showing page
      showingPage = oldPage; // restore page
      showingPage.getPane().setVisible(true); // show restore page
      oldPage = null;
    }
  }

  private void showIPage(IPage newPage) {
    oldPage = showingPage; // backup old page

    if (!(newPage instanceof LoadingPage)) {
      showingPage.getPane().setVisible(false); // hide old page
    }

    showingPage = newPage;
    showingPage.getPane().setVisible(true); // show new page
  }

  public SettingPage getSettingPage() {
    return settingPage;
  }
}
