package com.gryffindor.frontend.scenes.mainscene.page;

import com.gryffindor.frontend.scenes.mainscene.field.IField;
import javafx.scene.layout.Pane;

/** Các trang của một scene chứa các {@link IField} có chức năng liên kết các field lại với nhau. */
public interface IPage {
  Pane getPane();
}
