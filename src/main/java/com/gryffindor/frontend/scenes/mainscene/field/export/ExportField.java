package com.gryffindor.frontend.scenes.mainscene.field.export;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.scenes.mainscene.field.IController;
import com.gryffindor.frontend.scenes.mainscene.field.IField;
import com.gryffindor.frontend.utils.ImageUtils;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ExportField implements IField {
  private final Pane pane;

  private Button exportButton;

  private ExportController controller;

  /** Khởi tạo vùng export. */
  public ExportField() {
    pane = new Pane();

    initExportButton();
    pane.getChildren().add(exportButton);

    controller = new ExportController(this);
  }

  void initExportButton() {
    exportButton = new Button("Export word list to file");
    exportButton.getStyleClass().add("tool-button");

    ImageView toolsImageView = ImageUtils
        .getFitSquareImage(DictionaryApplication.INSTANCE.config.getImagesPath() + "/export.png", 50);
    exportButton.setGraphic(toolsImageView);

    exportButton.prefWidthProperty().bind(pane.widthProperty());
  }

  @Override
  public Pane getPane() {
    return pane;
  }

  public Button getExportButton() {
    return exportButton;
  }

  @Override
  public IController getController() {
    return controller;
  }
}
