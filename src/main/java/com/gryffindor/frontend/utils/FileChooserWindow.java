package com.gryffindor.frontend.utils;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/** Cửa sổ chọn file. */
public class FileChooserWindow {
  FileChooser fileChooser;

  /** Khởi tạo. */
  public FileChooserWindow(String title, String fileName) {
    fileChooser = new FileChooser();
    fileChooser.setInitialFileName(fileName);
    fileChooser.setTitle(title);
  }

  public File getSaveFile() {
    return fileChooser.showSaveDialog(new Stage());
  }

  public File getOpenFile() {
    return fileChooser.showOpenDialog(new Stage());
  }

  /**
   * Lọc các file muốn chọn.
   */
  public FileChooserWindow setExtensionFilter(ExtensionFilter... filters) {
    fileChooser.getExtensionFilters().addAll(filters);

    return this;
  }
}
