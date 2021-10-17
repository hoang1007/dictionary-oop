package com.gryffindor.frontend.utils;

import javafx.scene.Node;

public class ManagedUtils {
  /** Tiện ích ràng buộc nhanh quản lí node với visible. */
  public static void bindVisible(Node node) {
    node.managedProperty().bind(node.visibleProperty());
  }
}
