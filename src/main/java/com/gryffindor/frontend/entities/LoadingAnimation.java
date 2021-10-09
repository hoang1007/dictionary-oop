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

  public LoadingAnimation(Color color) {
    this.setSpacing(10);

    final int NO_CIRCLE = 3;
    circles = new Circle[NO_CIRCLE];

    for (int i = 0; i < NO_CIRCLE; i++) {
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

  public void stop() {
    for (FadeTransition ft : animations) {
      ft.stop();
    }
  }
}
