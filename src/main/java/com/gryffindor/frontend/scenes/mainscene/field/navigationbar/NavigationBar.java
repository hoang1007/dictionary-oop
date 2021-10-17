package com.gryffindor.frontend.scenes.mainscene.field.navigationbar;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.scenes.mainscene.field.IField;
import com.gryffindor.frontend.utils.ImageUtils;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/** Thanh công cụ. */
public class NavigationBar implements IField {
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

  /** Khởi tạo nút home. */
  private void initHomePage() {
    homeButton = new Button();
    homeButton.setTooltip(new Tooltip("Home"));
    homeButton.getStyleClass().add("nav-button");

    ImageView homeImageView = ImageUtils
        .getFitSquareImage(DictionaryApplication.INSTANCE.config.getImagesPath() + "/home.png", iconSize);

    homeButton.setGraphic(homeImageView);
  }

  /** Khởi tạo nút tools. */

  private void initToolsPage() {
    toolsButton = new Button();
    toolsButton.setTooltip(new Tooltip("Tools"));
    toolsButton.getStyleClass().add("nav-button");

    ImageView toolsImageView = ImageUtils
        .getFitSquareImage(DictionaryApplication.INSTANCE.config.getImagesPath() + "/tools.png", iconSize);

    toolsButton.setGraphic(toolsImageView);
  }

  /** Khởi tạo nut setting. */
  private void initSettingsPage() {
    settingsButton = new Button();
    settingsButton.setTooltip(new Tooltip("Settings"));
    settingsButton.getStyleClass().add("nav-button");

    ImageView settingsImageView = ImageUtils
        .getFitSquareImage(DictionaryApplication.INSTANCE.config.getImagesPath() + "/settings.png", iconSize);

    settingsButton.setGraphic(settingsImageView);
  }

  /**
   * @return Button
   */
  public Button getHomeButton() {
    return homeButton;
  }

  /**
   * @return Button
   */
  public Button getToolsButton() {
    return toolsButton;
  }

  /**
   * @return Button
   */
  public Button getSettingsButton() {
    return settingsButton;
  }

  /**
   * @return NavigationBarController
   */
  @Override
  public NavigationBarController getController() {
    return controller;
  }

  /**
   * @return Pane
   */
  @Override
  public Pane getPane() {
    return navigationBarPane;
  }
}
