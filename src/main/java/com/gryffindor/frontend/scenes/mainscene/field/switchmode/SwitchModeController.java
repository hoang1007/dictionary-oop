package com.gryffindor.frontend.scenes.mainscene.field.switchmode;

import com.gryffindor.frontend.entities.SwitchButton;
import com.gryffindor.frontend.scenes.mainscene.field.IController;

public class SwitchModeController implements IController {
  SwitchModeField switchMode;

  public SwitchModeController(SwitchModeField switchMode) {
    this.switchMode = switchMode;
    onSwitchMode();
  }

  void onSwitchMode() {
    SwitchButton button = switchMode.getSwitchButton();

    button.addOnMouseClicked(event -> {
      if (button.getState() == true) {
        System.out.println("enable");
      } else {
        System.out.println("disable");
      }
    });
  }
}
