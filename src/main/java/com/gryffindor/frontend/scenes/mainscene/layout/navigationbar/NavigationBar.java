package com.gryffindor.frontend.scenes.mainscene.layout.navigationbar;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.scenes.mainscene.layout.IPage;
import com.gryffindor.frontend.utils.IconUtils;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class NavigationBar implements IPage {
  private final VBox navigationBarPane;

  private Button homeButton;
  private Button toolsButton;
  private Button settingsButton;

  private double iconSize = 30;

  NavigationBarController controller;

  /** Khởi tạo. */
  public NavigationBar() {
    navigationBarPane = new VBox();
    navigationBarPane.setSpacing(20);
    navigationBarPane.getStyleClass().add("nav-pane");

    initHomePage();
    initToolsPage();
    initSettingsPage();

    navigationBarPane.getChildren().addAll(homeButton, toolsButton, settingsButton);
    navigationBarPane.setSpacing(20);

    controller = new NavigationBarController(this);
  }

  void initHomePage() {
    homeButton = new Button();
    homeButton.getStyleClass().add("nav-button");

    ImageView homeImageView = IconUtils.getFitSquareIcon(
            DictionaryApplication.INSTANCE.config.getImagesPath() + "/home.png", iconSize);

    homeButton.setGraphic(homeImageView);
  }

  void initToolsPage() {
    toolsButton = new Button();
    toolsButton.getStyleClass().add("nav-button");

    ImageView toolsImageView = IconUtils.getFitSquareIcon(
            DictionaryApplication.INSTANCE.config.getImagesPath() + "/tools.png", iconSize);

    toolsButton.setGraphic(toolsImageView);
  }

  void initSettingsPage() {
    settingsButton = new Button();
    settingsButton.getStyleClass().add("nav-button");

    ImageView settingsImageView = IconUtils.getFitSquareIcon(
            DictionaryApplication.INSTANCE.config.getImagesPath() + "/settings.png", iconSize);
    
    settingsButton.setGraphic(settingsImageView);
  }

  public Button getHomeButton() {
    return homeButton;
  }

  public Button getToolsButton() {
    return toolsButton;
  }

  public Button getSettingsButton() {
    return settingsButton;
  }

  public NavigationBarController getController() {
    return controller;
  }

  @Override
  public Pane getPane() {
    return navigationBarPane;
  }
}
