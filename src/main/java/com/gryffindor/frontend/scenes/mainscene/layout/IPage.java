package com.gryffindor.frontend.scenes.mainscene.layout;

import javafx.scene.layout.Pane;

public interface IPage {
  Pane getPane();
  
  IPageController getController();
}
