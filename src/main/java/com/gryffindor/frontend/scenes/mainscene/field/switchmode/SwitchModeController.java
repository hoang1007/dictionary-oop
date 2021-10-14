package com.gryffindor.frontend.scenes.mainscene.field.switchmode;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.Status;
import com.gryffindor.backend.utils.NetworkUtils;
import com.gryffindor.frontend.entities.SwitchButton;
import com.gryffindor.frontend.scenes.mainscene.field.IController;

public class SwitchModeController implements IController {
  SwitchModeField switchMode;

  // đánh dấu lần đầu click để load dữ liệu
  boolean firstClick;

  public SwitchModeController(SwitchModeField switchMode) {
    this.switchMode = switchMode;
    this.firstClick = true;
    onSwitchMode();
  }

  void onSwitchMode() {
    SwitchButton button = switchMode.getSwitchButton();

    button.addOnMouseClicked(event -> {
      if (button.getState() == true) {
        // bật search online
        // kiểm tra xem có internet không
        // nếu không thì không cho bật
        Status status = NetworkUtils.networkStatus();
        if (status.equals(Status.OFFLINE)) {
          button.setOff();
        }

        DictionaryApplication.INSTANCE.setStatus(status);
      } else {
        if (firstClick) {
          new Thread(() -> {
            DictionaryApplication.INSTANCE.dictionaryManagement.addDataFromFile();
            firstClick = false;
            DictionaryApplication.INSTANCE.setStatus(Status.OFFLINE);
          }).start();
        } else {
          DictionaryApplication.INSTANCE.setStatus(Status.OFFLINE);
        }
      }
    });
  }
}
