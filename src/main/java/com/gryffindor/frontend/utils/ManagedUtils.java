package com.gryffindor.frontend.utils;

import javafx.scene.Node;

public class ManagedUtils {
  public static void bindVisible(Node node) {
    node.managedProperty().bind(node.visibleProperty());
  }
}
