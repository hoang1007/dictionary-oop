package com.gryffindor.frontend.scenes.mainscene;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.scenes.mainscene.entities.SearchField;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/** Scene chính của ứng dụng. */
public class MainScene {
  private final Scene mainScene;
  private final VBox rootPane;

  /** Khởi tạo scene chính. */
  public MainScene() {
    rootPane = new VBox();
    mainScene = new Scene(rootPane);
    mainScene.getStylesheets().add(DictionaryApplication.INSTANCE.config.getStyle());
  
    rootPane.getChildren().add(new SearchField().getPane());
  }

  public Scene getMainScene() {
    return mainScene;
  }
}
