package com.gryffindor.frontend.scenes.mainscene;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.scenes.mainscene.entities.ExplainField;
import com.gryffindor.frontend.scenes.mainscene.entities.NavigationBarField;
import com.gryffindor.frontend.scenes.mainscene.entities.SearchField;
import com.gryffindor.frontend.scenes.mainscene.entities.TranslateField;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/** Scene chính của ứng dụng. */
public class MainScene {
  private final Scene mainScene;
  private final VBox rootPane;

  private final NavigationBarField navigationBarField;
  private final SearchField searchField;
  private final TranslateField translateField;
  private final ExplainField explainField;

  /** Khởi tạo scene chính. */
  public MainScene() {
    rootPane = new VBox();
    rootPane.getStyleClass().add("root-pane");
    rootPane.setSpacing(5); // đặt khoảng cách giữa các child pane

    navigationBarField = new NavigationBarField();
    searchField = new SearchField();
    translateField = new TranslateField();
    explainField = new ExplainField();

    rootPane.getChildren().addAll(
        navigationBarField.getPane(),
        searchField.getPane(), 
        translateField.getPane(), 
        explainField.getPane()
    );

    mainScene = new Scene(rootPane);
    mainScene.getStylesheets().add(DictionaryApplication.INSTANCE.config.getStyle());
  }

  public Scene getMainScene() {
    return mainScene;
  }
}
