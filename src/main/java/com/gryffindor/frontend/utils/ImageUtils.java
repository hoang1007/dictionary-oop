package com.gryffindor.frontend.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageUtils {
  /**
   * Lấy image view sau khi resize.
   * @param path đường dẫn ảnh
   * @param size kích cỡ ảnh
   * @return image view sau khi resize
   */
  public static ImageView getFitSquareImage(String path, double size) {
    Image img = new Image(path);
    ImageView view = new ImageView(img);
    view.setFitWidth(size);
    view.setPreserveRatio(true);

    return view;
  }
}
