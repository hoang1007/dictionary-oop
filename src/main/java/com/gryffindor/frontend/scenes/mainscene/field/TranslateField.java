package com.gryffindor.frontend.scenes.mainscene.field;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.backend.entities.Word;
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

  private Word word;

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
    parentPane.getStyleClass().add("padding-pane");
    parentPane.getChildren().add(translatePane);

    translatePane.getChildren().addAll(pronouncedButton, wordTarget, pronouncedText);
  }

  void initWordTarget() {
    wordTarget = new Label();
    wordTarget.setWrapText(true);
    
    wordTarget.getStyleClass().add("word-header");
  }

  void initPronouncedText() {
    pronouncedText = new Text();
  }

  void initPronouncedButton() {
    pronouncedButton = new Button("Tap to listen");
    pronouncedButton.getStyleClass().add("pron-button");

    ImageView imageView = ImageUtils.getFitSquareImage(
        DictionaryApplication.INSTANCE.config.getImagesPath() + "/sound.png", 12);

    pronouncedButton.setGraphic(imageView);
  }

  /**
   * Tự động đặt các thuộc tính liên quan đến {@link Word}.
   * @param word từ muốn đặt
   */
  public TranslateField setWord(Word word) {
    this.word = word;

    pronouncedText.setText("/ˈflauə/");

    wordTarget.setText(word.getWordTarget());

    return this;
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

  public Word getWord() {
    return word;
  }
}
