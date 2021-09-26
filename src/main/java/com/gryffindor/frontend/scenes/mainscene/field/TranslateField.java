package com.gryffindor.frontend.scenes.mainscene.field;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.utils.ImageUtils;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TranslateField implements IField {
  private final VBox parentPane;
  private final VBox translatePane;
  private Text wordTarget;
  private Text pronouncedText;
  private Button pronouncedButton;

  /** Khởi tạo TranslateField. */
  public TranslateField() {
    translatePane = new VBox();
    translatePane.getStyleClass().add("text-pane");

    initWordTarget();
    initPronouncedText();
    initPronouncedButton();

    parentPane = new VBox();
    parentPane.getStyleClass().add("padding-pane");
    parentPane.getChildren().add(translatePane);

    translatePane.getChildren().addAll(pronouncedButton, wordTarget, pronouncedText);
  }

  void initWordTarget() {
    wordTarget = new Text("flower");
    wordTarget.getStyleClass().add("word-header");
  }

  void initPronouncedText() {
    pronouncedText = new Text("/ˈflauə/");
  }

  void initPronouncedButton() {
    pronouncedButton = new Button("Tap to listen");
    pronouncedButton.getStyleClass().add("pron-button");

    ImageView imageView = ImageUtils.getFitSquareImage(
        DictionaryApplication.INSTANCE.config.getImagesPath() + "/sound.png", 12);

    pronouncedButton.setGraphic(imageView);
  }

  @Override
  public Pane getPane() {
    return parentPane;
  }

  public Text getWordTarget() {
    return wordTarget;
  }

  public Text getPronouncedText() {
    return pronouncedText;
  }
}
