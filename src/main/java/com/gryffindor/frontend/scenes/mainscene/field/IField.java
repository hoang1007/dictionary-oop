package com.gryffindor.frontend.scenes.mainscene.field;

import javafx.scene.layout.Pane;

/** Interface field để khởi tạo đồ họa của các vùng chức năng. */
public interface IField {
  Pane getPane();

  IController getController();
}
