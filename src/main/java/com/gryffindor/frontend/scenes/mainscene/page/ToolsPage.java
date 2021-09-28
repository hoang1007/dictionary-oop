package com.gryffindor.frontend.scenes.mainscene.page;

import com.gryffindor.frontend.scenes.mainscene.field.export.ExportField;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ToolsPage implements IPage {
  private final GridPane pane;

  private ExportField exportField;

  /** Khởi tạo tools page. */
  public ToolsPage() {
    pane = new GridPane();
    pane.getStyleClass().add("tools-pane");

    exportField = new ExportField();

    pane.getChildren().add(exportField.getPane());
    GridPane.setConstraints(exportField.getPane(), 0, 0);
  }

  @Override
  public Pane getPane() {
    return pane;
  }

  public ExportField getExportField() {
    return exportField;
  }
}
