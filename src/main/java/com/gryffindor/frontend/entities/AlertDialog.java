package com.gryffindor.frontend.entities;

import com.gryffindor.DictionaryApplication;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertDialog {
  Alert alert;

  /**
   * Khởi tạo hộp thông báo.
   * @param type loại thông báo
   */
  public AlertDialog(AlertType type) {
    alert = new Alert(type);
    alert.getDialogPane().getStylesheets().add(
          DictionaryApplication.INSTANCE.config.getAlertStyle());
    alert.getDialogPane().getStyleClass().add("alert-pane");

    alert.setHeaderText(null);
  }

  /** Đặt tin nhắn. */
  public AlertDialog setContent(String content) {
    alert.setContentText(content);
    return this;
  }

  public void show() {
    alert.showAndWait();
  }

  public void show(Exception exception) {
    alert.setContentText(exception.getMessage());
    alert.showAndWait();
  }
}
