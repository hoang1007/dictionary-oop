package com.gryffindor.frontend;

import com.gryffindor.frontend.scenes.mainscene.MainScene;

import javafx.application.Application;
import javafx.stage.Stage;


public class ApplicationUI extends Application {
  final String title;
  final int width;
  final int height;

  /** Khởi tạo UI. */
  public ApplicationUI() {
    this.title = "Gryffindor Dictionary";
    width = 800;
    height = 600;
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle(title);
    primaryStage.setWidth(width);
    primaryStage.setHeight(height);
    
    primaryStage.setScene(new MainScene().getMainScene());
    primaryStage.show();
  }
}