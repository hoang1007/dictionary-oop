package com.gryffindor.frontend.entities;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Button chuyển đổi giữa hai chế độ. Tham khảo code của meiswjn
 *
 * @link https://gist.github.com/meiswjn/dd64f0706085cab336e30ac7e2ef59b1
 */
public class SwitchButton extends StackPane {
  private final Rectangle back = new Rectangle(30, 10, Color.WHITE);
  private final Button button = new Button();

  private final String buttonStyleOff =
      "-fx-effect: dropshadow(three-pass-box,"
          + "rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: WHITE;";

  private final String buttonStyleOn =
      "-fx-effect: dropshadow(three-pass-box,"
          + "rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: #3fa8d9";

  private String colorOn = "#6bc4ed";
  private String colorOff = "#ced5da";

  private boolean state;

  /**
   * Hàm khởi tạo.
   *
   * @param defaultBoolean
   */
  private void init(boolean defaultBoolean) {
    this.state = defaultBoolean;

    this.getChildren().addAll(back, button);

    this.setMinSize(30, 15);

    back.maxWidth(30);
    back.minWidth(30);

    back.maxHeight(10);
    back.minHeight(10);

    back.setArcHeight(back.getHeight());
    back.setArcWidth(back.getHeight());
    back.setFill(state ? Color.valueOf(colorOn) : Color.valueOf(colorOff));

    Double r = 2.0;
    button.setShape(new Circle(r));

    setAlignment(button, state ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
    button.setMaxSize(15, 15);
    button.setMinSize(15, 15);
    button.setStyle(state ? buttonStyleOn : buttonStyleOff);
  }

  /**
   * Khởi tạo switch button.
   *
   * @param defaultBoolean trạng thái on off của button
   */
  public SwitchButton(boolean defaultBoolean) {
    init(defaultBoolean);

    EventHandler<Event> click =
        new EventHandler<Event>() {
          @Override
          public void handle(Event event) {
            if (state) {
              setOff();
            } else {
              setOn();
            }
          }
        };

    button.setFocusTraversable(false);

    this.setOnMouseClicked(click);
    button.setOnMouseClicked(click);
  }

  /** Thêm event handler cho button. */
  public void addOnMouseClicked(EventHandler<Event> value) {
    EventHandler<Event> click =
        new EventHandler<Event>() {
          @Override
          public void handle(Event event) {
            if (state) {
              setOff();
            } else {
              setOn();
            }

            value.handle(event);
          }
        };

    this.setOnMouseClicked(click);
    button.setOnMouseClicked(click);
  }

  /** @return boolean */
  public boolean getState() {
    return state;
  }

  /** Trạng thái on của button. */
  public void setOn() {
    button.setStyle(buttonStyleOn);
    back.setFill(Color.valueOf(colorOn));
    setAlignment(button, Pos.CENTER_RIGHT);
    state = true;
  }

  /** Trạng thái off của button. */
  public void setOff() {
    button.setStyle(buttonStyleOff);
    back.setFill(Color.valueOf(colorOff));
    setAlignment(button, Pos.CENTER_LEFT);
    state = false;
  }
}
