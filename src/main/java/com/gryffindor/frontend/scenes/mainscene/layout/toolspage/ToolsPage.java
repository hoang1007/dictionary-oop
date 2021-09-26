package com.gryffindor.frontend.scenes.mainscene.layout.toolspage;

import com.gryffindor.frontend.scenes.mainscene.field.ExportField;
import com.gryffindor.frontend.scenes.mainscene.layout.IPage;
import com.gryffindor.frontend.scenes.mainscene.layout.IPageController;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ToolsPage implements IPage {
  private final GridPane pane;

  private ExportField exportField;

  private ToolsPageController controller;

  /** Khởi tạo tools page. */
  public ToolsPage() {
    pane = new GridPane();
    pane.getStyleClass().add("tools-pane");

    exportField = new ExportField();

    pane.getChildren().add(exportField.getPane());
    GridPane.setConstraints(exportField.getPane(), 0, 0);
    
    controller = new ToolsPageController(this);
  }

  @Override
  public Pane getPane() {
    return pane;
  }

  @Override
  public IPageController getController() {
    return controller;
  }

  public ExportField getExportField() {
    return exportField;
  }
}
