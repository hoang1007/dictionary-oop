package com.gryffindor.frontend.scenes.mainscene.field.export;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.entities.AlertDialog;
import com.gryffindor.frontend.scenes.mainscene.field.IController;
import com.gryffindor.frontend.utils.FileChooserWindow;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

public class ExportController implements IController {
  ExportField exportField;

  /** Khởi tạo export controller. */
  public ExportController(ExportField exportField) {
    this.exportField = exportField;

    initialize();
    onClickExport();
  }

  // Khởi tạo đường dân mặc định khi click vào button export
  public void initialize() {
    // String dic = "C:/";
    // IController.fileChooser.setInitialDirectory(new File(dic));
  }

  private void export() {
    File file =
        new FileChooserWindow("Save file", "dictionary")
            .setExtensionFilter(
                // Định dạng file save
                new ExtensionFilter("TEXT files", "*.txt"), new ExtensionFilter("All Files", "*.*"))
            .getSaveFile();

    new Thread(
            () -> {
              DictionaryApplication.INSTANCE.getDictionaryManagement().dictionaryExportToFile(file);

              Platform.runLater(
                  () ->
                      new AlertDialog(AlertType.INFORMATION)
                          .setContent(
                              "Xuất file thành công\nFile được lưu ở " + file.getAbsolutePath())
                          .show());
            })
        .start();
  }

  private void onClickExport() {
    // sự kiện nhấn chuật vào Export button
    exportField
        .getExportButton()
        .setOnAction(
            new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                export();
              }
            });
  }
}
