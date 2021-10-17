package com.gryffindor.frontend.scenes.mainscene.field.explains;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.scenes.mainscene.field.IField;
import com.gryffindor.frontend.utils.ImageUtils;
import com.gryffindor.frontend.utils.ManagedUtils;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TranslationField implements IField {
  private GridPane pane;

  private TextArea wordExplain;
  private Button editExplainButton;
  private Button deleteExplainButton;
  private ExampleSentences exampleSentences;

  private TranslationController controller;

  /** Khởi tạo vùng dịch. */
  public TranslationField() {
    pane = new GridPane();
    pane.getStyleClass().add("hover-pane");
    
    initExampleSentences();
    initWordExplain();
    initButtons();

    controller = new TranslationController(this);
  }

  void initWordExplain() {
    wordExplain = new TextArea();
    wordExplain.setEditable(false); // mặc định không được sửa
    wordExplain.setWrapText(true);
    wordExplain.getStyleClass().add("word-meaning");

    // set height fit to content
    SimpleIntegerProperty count = new SimpleIntegerProperty(20);
    int rowHeight = 10;

    wordExplain.prefHeightProperty().bindBidirectional(count);
    wordExplain.minHeightProperty().bindBidirectional(count);

    wordExplain.scrollTopProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.intValue() > rowHeight) {
        count.setValue(count.get() + newValue.intValue());
      }
    });

    pane.getChildren().add(wordExplain);
    GridPane.setConstraints(wordExplain, 0, 0);
  }

  private void initExampleSentences() {
    exampleSentences = new ExampleSentences();

    pane.getChildren().add(exampleSentences.getPane());
    GridPane.setConstraints(exampleSentences.getPane(), 0, 1);
    GridPane.setHgrow(exampleSentences.getPane(), Priority.ALWAYS);
  }

  private void initDeleteExplainButton() {
    deleteExplainButton = new Button();
    ManagedUtils.bindVisible(deleteExplainButton);
    deleteExplainButton.getStyleClass().add("transparent-button");

    deleteExplainButton.setTooltip(new Tooltip("Xoá bản dịch này"));

    ImageView imageView = ImageUtils.getFitSquareImage(
        DictionaryApplication.INSTANCE.config.getImagesPath() + "/trash.png", 10);

    deleteExplainButton.setGraphic(imageView);
  }

  private void initEditExplainButton() {
    editExplainButton = new Button();
    ManagedUtils.bindVisible(editExplainButton);
    editExplainButton.getStyleClass().add("transparent-button");

    editExplainButton.setTooltip(new Tooltip("Sửa bản dịch này"));

    ImageView imageView = ImageUtils.getFitSquareImage(
        DictionaryApplication.INSTANCE.config.getImagesPath() + "/pencil.png", 10);

    editExplainButton.setGraphic(imageView);
  }

  private void initButtons() {
    VBox vbox = new VBox();
    initDeleteExplainButton();
    initEditExplainButton();

    vbox.getChildren().addAll(editExplainButton, deleteExplainButton);

    pane.getChildren().add(vbox);
    GridPane.setConstraints(vbox, 1, 0);
  }

  @Override
  public Pane getPane() {
    return pane;
  }

  @Override
  public TranslationController getController() {
    return controller;
  }

  public Button getEditExplainButton() {
    return editExplainButton;
  }

  public Button getDeleteExplainButton() {
    return deleteExplainButton;
  }

  public TextArea getWordExplain() {
    return wordExplain;
  }

  public ExampleSentences getExamples() {
    return exampleSentences;
  }

  class ExampleSentences {
    private VBox pane;

    private ObservableList<Label> sentences;

    public ExampleSentences() {
      pane = new VBox();

      initExampleSentences();
    }

    private void initExampleSentences() {
      sentences = FXCollections.observableArrayList();

      // format label when add to list
      sentences.addListener(new ListChangeListener<Label>() {
        @Override
        public void onChanged(Change<? extends Label> c) {
          while (c.next()) {
            if (c.wasAdded()) {
              for (Label label : c.getAddedSubList()) {
                label.setWrapText(true);
                pane.getChildren().add(label);
              }
            } else if (c.wasRemoved()) {
              pane.getChildren().removeAll(c.getRemoved());
            }
          }
        }
      }); 
    }

    public ObservableList<Label> getList() {
      return sentences;
    }

    public Pane getPane() {
      return pane;
    }
  }
}
