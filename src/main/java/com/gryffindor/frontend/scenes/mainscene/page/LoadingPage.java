package com.gryffindor.frontend.scenes.mainscene.page;

import com.gryffindor.frontend.entities.LoadingAnimation;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/** Trang để hiển thị loading animation. */
public class LoadingPage implements IPage {
  StackPane loadingPane;
  private LoadingAnimation animation;

  /** Khởi tạo trang loading. */
  public LoadingPage() {
    loadingPane = new StackPane();
    loadingPane.setStyle("-fx-background-color: rgba(172, 172, 172, 0.3)");
    // loadingPane.setOpacity(20);

    initAnimation();
    loadingPane.setAlignment(Pos.CENTER);
  }

  /** Tạo animation. */
  void initAnimation() {
    animation = new LoadingAnimation(Color.valueOf("#12CBC4"));
    loadingPane.getChildren().add(animation);
    animation.play();
  }

  public LoadingAnimation getAnimation() {
    return animation;
  }

  @Override
  public Pane getPane() {
    return loadingPane;
  }
}
