package com.gryffindor.frontend.scenes.mainscene.page;

import com.gryffindor.frontend.scenes.mainscene.field.switchmode.SwitchModeField;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SettingPage implements IPage {
  private final VBox settingPane;

  SwitchModeField switchModeField;

  public SettingPage() {
    settingPane = new VBox();
    settingPane.getStyleClass().add("padding-pane");

    initSwitchModeField();
    
    settingPane.getChildren().addAll(switchModeField.getPane());
  }

  void initSwitchModeField() {
    switchModeField = new SwitchModeField();
    switchModeField.getPane().prefWidthProperty().bind(settingPane.widthProperty());
  }

  @Override
  public Pane getPane() {
    return settingPane;
  }
}
