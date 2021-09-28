package com.gryffindor.frontend.scenes.mainscene.field.translate;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.scenes.mainscene.field.IField;
import com.gryffindor.frontend.utils.ImageUtils;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TranslateField implements IField {
  private final VBox parentPane;
  private final VBox translatePane;
  private final TranslateController controller;

  private Label wordTarget;
  private Text pronouncedText;
  private Button pronouncedButton;

  /** Khởi tạo TranslateField. */
  public TranslateField() {
    translatePane = new VBox();
    translatePane.getStyleClass().add("trans-pane");

    initWordTarget();
    initPronouncedText();
    initPronouncedButton();

    parentPane = new VBox();
    parentPane.managedProperty().bind(parentPane.visibleProperty());
    parentPane.getStyleClass().add("padding-pane");
    parentPane.getChildren().add(translatePane);

    translatePane.getChildren().addAll(pronouncedButton, wordTarget, pronouncedText);

    controller = new TranslateController(this);
  }

  private void initWordTarget() {
    wordTarget = new Label();
    wordTarget.setWrapText(true);
    
    wordTarget.getStyleClass().add("word-header");
  }

  private void initPronouncedText() {
    pronouncedText = new Text();
    pronouncedText.getStyleClass().add("normal-text");
  }

  private void initPronouncedButton() {
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

  public Label getWordTarget() {
    return wordTarget;
  }

  public Text getPronouncedText() {
    return pronouncedText;
  }

  public Button getPronouncedButton() {
    return pronouncedButton;
  }

  @Override
  public TranslateController getController() {
    return controller;
  }
}
