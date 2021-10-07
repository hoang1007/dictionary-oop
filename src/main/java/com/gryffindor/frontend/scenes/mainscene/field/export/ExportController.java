package com.gryffindor.frontend.scenes.mainscene.field.export;

import com.gryffindor.frontend.scenes.mainscene.field.IController;
import com.gryffindor.frontend.utils.FileChooserWindow;
import com.gryffindor.DictionaryApplication;

import java.io.File;

import javafx.stage.FileChooser.ExtensionFilter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ExportController implements IController {
  ExportField exportField;

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

  // export to file
  public void export() {
    File file = new FileChooserWindow("Save file", "dictionary")
          .setExtensionFilter(
            // Định dạng file save
            new ExtensionFilter("TEXT files", "*.txt"),
            new ExtensionFilter("All Files", "*.*")
          ).getSaveFile();

    DictionaryApplication.INSTANCE.getDictionaryManagement().dictionaryExportToFile(file);
  }

  void onClickExport() {
    // sự kiện nhấn chuật vào Export button
    exportField.getExportButton().setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        export();
      }
    });
  }
}
