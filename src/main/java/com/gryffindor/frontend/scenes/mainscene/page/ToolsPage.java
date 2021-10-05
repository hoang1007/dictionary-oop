package com.gryffindor.frontend.scenes.mainscene.page;

import com.gryffindor.frontend.scenes.mainscene.field.export.ExportField;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ToolsPage implements IPage {
  private final VBox pane;

  private ExportField exportField;

  /** Khởi tạo tools page. */
  public ToolsPage() {
    pane = new VBox();
    pane.setFillWidth(true);
    pane.getStyleClass().add("tools-pane");

    initExportField();
    
    pane.getChildren().addAll(exportField.getPane());
  }

  void initExportField() {
    exportField = new ExportField();
    exportField.getPane().prefWidthProperty().bind(pane.widthProperty());
  }

  @Override
  public Pane getPane() {
    return pane;
  }

  public ExportField getExportField() {
    return exportField;
  }
}
