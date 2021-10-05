package com.gryffindor.frontend.scenes.mainscene.field.export;

import com.gryffindor.frontend.scenes.mainscene.field.IController;
import com.gryffindor.DictionaryApplication;

import java.io.File;

import javafx.stage.FileChooser.ExtensionFilter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ExportController implements IController {

  ExportField exportField;

  public ExportController(ExportField exportField) {
    this.exportField = exportField;
  }

  // Khởi tạo
  public ExportController() {
    initialize();
    addExtensionFilter();
  }

  // Khởi tạo đường dân mặc định khi click vào button export
  public void initialize() {
    // String dic = "C:/";
    // IController.fileChooser.setInitialDirectory(new File(dic));
  }

  public void setTitleAndFileName(String title, String nameOfFile) {
    IController.fileChooser.setInitialFileName(nameOfFile);
    IController.fileChooser.setTitle(title);
  }

  public void addExtensionFilter() {
    // Định dạng file save
    ExtensionFilter txt = new ExtensionFilter("TEXT files", "*.txt");
    // ExtensionFilter pdf = new ExtensionFilter("PDF", "*.pdf");
    ExtensionFilter allFile = new ExtensionFilter("All Files", "*.*");
    IController.fileChooser.getExtensionFilters().addAll(txt, allFile);
  }

  // export to file
  public void export() {
    setTitleAndFileName("Save file", "file_name");

    File file = IController.fileChooser.showSaveDialog(new Stage());

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
