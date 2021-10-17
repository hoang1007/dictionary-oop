package com.gryffindor.frontend.scenes.mainscene.field.switchmode;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.Status;
import com.gryffindor.backend.utils.NetworkUtils;
import com.gryffindor.frontend.entities.AlertDialog;
import com.gryffindor.frontend.entities.SwitchButton;
import com.gryffindor.frontend.scenes.mainscene.field.IController;

import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;

public class SwitchModeController implements IController {
  SwitchModeField switchMode;

  // đánh dấu lần đầu offline để load dữ liệu
  boolean firstOffline;

  /** Khởi tạo controller. */
  public SwitchModeController(SwitchModeField switchMode) {
    this.switchMode = switchMode;
    this.firstOffline = true;
    if (DictionaryApplication.INSTANCE.getStatus().equals(Status.OFFLINE)) {
      firstOffline = false;
      // mặc định off
      switchMode.getSwitchButton().setOff();
    }

    onSwitchMode();
  }

  private void onSwitchMode() {
    SwitchButton button = switchMode.getSwitchButton();

    button.addOnMouseClicked(event -> {
      if (button.getState() == true) {
        // bật search online
        // kiểm tra xem có internet không
        // nếu không thì không cho bật
        Status status = NetworkUtils.networkStatus();
        if (status.equals(Status.OFFLINE)) {
          button.setOff();
          new AlertDialog(AlertType.WARNING).setContent("You are offline").show();
        }

        DictionaryApplication.INSTANCE.setStatus(status);
      } else {
        if (firstOffline) {
          // nếu lần đầu offline tải dữ liệu
          new Thread(() -> {
            DictionaryApplication.INSTANCE.dictionaryManagement.insertFromJson();
            firstOffline = false;
            DictionaryApplication.INSTANCE.setStatus(Status.OFFLINE);
            Platform.runLater(() -> new AlertDialog(
                  AlertType.INFORMATION).setContent("Downloaded offline data").show());
          }).start();
        } else {
          // nếu không chỉ cần dặt status
          // mà không cần tải dữ liệu
          DictionaryApplication.INSTANCE.setStatus(Status.OFFLINE);
        }
      }
    });
  }
}
