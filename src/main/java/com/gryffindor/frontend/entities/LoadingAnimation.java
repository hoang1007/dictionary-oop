package com.gryffindor.frontend.entities;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class LoadingAnimation extends HBox {
  Circle[] circles;
  FadeTransition[] animations;

  /** Khởi tạo animation. */
  public LoadingAnimation(Color color) {
    this.setSpacing(10);

    final int No_Circle = 3;
    circles = new Circle[No_Circle];

    for (int i = 0; i < No_Circle; i++) {
      circles[i] = new Circle(10f);
      circles[i].setFill(color);
    }

    this.setAlignment(Pos.CENTER);

    this.getChildren().addAll(circles);
    initAnimation();
  }

  void initAnimation() {
    animations = new FadeTransition[circles.length];
    for (int i = 0; i < circles.length; i++) {
      animations[i] = new FadeTransition(Duration.millis(400), circles[i]);
      animations[i].setFromValue(1.0);
      animations[i].setToValue(0.3);
      animations[i].setAutoReverse(true);

      int next = (i + 1) % circles.length;
      animations[i].setOnFinished(event -> animations[next].play());
    }
  }

  public void play() {
    animations[0].play();
  }

  /** Dừng phát animation. */
  public void stop() {
    for (FadeTransition ft : animations) {
      ft.stop();
    }
  }
}
