package com.gryffindor.frontend.utils;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;

/** Cửa sổ chọn file. */
public class FileChooserWindow {
  FileChooser fileChooser;

  /** Khởi tạo. */
  public FileChooserWindow(String title, String fileName) {
    fileChooser = new FileChooser();
    fileChooser.setInitialFileName(fileName);
    fileChooser.setTitle(title);
  }

  /** @return File */
  public File getSaveFile() {
    return fileChooser.showSaveDialog(new Stage());
  }

  /** @return File */
  public File getOpenFile() {
    return fileChooser.showOpenDialog(new Stage());
  }

  /** Lọc các file muốn chọn. */
  public FileChooserWindow setExtensionFilter(ExtensionFilter... filters) {
    fileChooser.getExtensionFilters().addAll(filters);

    return this;
  }
}
