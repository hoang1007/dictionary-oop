package com.gryffindor.frontend.scenes.mainscene.field;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.utils.ImageUtils;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ExportField implements IField {
  private final Pane pane;

  private Button exportButton;

  /** Khởi tạo vùng export. */
  public ExportField() {
    pane = new Pane();

    initExportButton();

    pane.getChildren().add(exportButton);
  }

  void initExportButton() {
    exportButton = new Button("Export words to file");
    exportButton.getStyleClass().add("tool-button");

    exportButton.maxWidthProperty().set(Integer.MAX_VALUE);

    ImageView toolsImageView = ImageUtils.getFitSquareImage(
        DictionaryApplication.INSTANCE.config.getImagesPath() + "/export.png", 
        50);
    exportButton.setGraphic(toolsImageView);
  }

  @Override
  public Pane getPane() {
    return pane;
  }

  public Button getExportButton() {
    return exportButton;
  }
}
