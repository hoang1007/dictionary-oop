package com.gryffindor.frontend.utils;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileChooserWindow {
  FileChooser fileChooser;

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

  public FileChooserWindow setExtensionFilter(ExtensionFilter... filters) {
    fileChooser.getExtensionFilters().addAll(filters);

    return this;
  }
}
