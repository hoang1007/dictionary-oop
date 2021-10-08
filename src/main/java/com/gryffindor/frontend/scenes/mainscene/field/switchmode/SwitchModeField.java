package com.gryffindor.frontend.scenes.mainscene.field.switchmode;

import com.gryffindor.frontend.entities.SwitchButton;
import com.gryffindor.frontend.scenes.mainscene.field.IField;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * Chuyển đổi giữa hai chế độ offline và online search
 */
public class SwitchModeField implements IField {
  private HBox pane;

  private Label description;
  private SwitchButton switchButton;

  private SwitchModeController controller;

  public SwitchModeField() {
    pane = new HBox();
    pane.setSpacing(100);
    pane.getStyleClass().add("setting-e-pane");

    initDescription();
    initSwitchButton();

    controller = new SwitchModeController(this);
  }

  void initDescription() {
    description = new Label("Enable online search mode");
    description.getStyleClass().add("setting-label");
    description.setAlignment(Pos.TOP_CENTER);
    pane.getChildren().add(description);

    HBox.setHgrow(description, Priority.ALWAYS);
  }

  void initSwitchButton() {
    switchButton = new SwitchButton(true);
    pane.getChildren().add(switchButton);
  }

  @Override
  public Pane getPane() {
    return pane;
  }

  @Override
  public SwitchModeController getController() {
    return controller;
  }

  public Label getDescription() {
    return description;
  }

  public SwitchButton getSwitchButton() {
    return switchButton;
  }
}
