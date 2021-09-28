package com.gryffindor.frontend.scenes.mainscene.field.explains;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.scenes.mainscene.field.IField;
import com.gryffindor.frontend.utils.ImageUtils;
import com.gryffindor.frontend.utils.ManagedUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/** Lớp vùng giải thích của từ. 
 * Vì một từ có thể có nhiều nghĩa nên vùng giải thích 
 * có thể có nhiều phần giải thích
 */
public class ExplainsField implements IField {
  private final VBox parentPane;
  private final ExplainsController controller;

  private ObservableList<Element> elements;

  /** Khởi tạo vùng giải thích. */
  public ExplainsField() {
    parentPane = new VBox();
    parentPane.managedProperty().bind(parentPane.visibleProperty());
    parentPane.getStyleClass().add("padding-pane");
    parentPane.setSpacing(10); // đặt khoảng cách giữa các phần giải thích
    elements = FXCollections.observableArrayList();

    controller = new ExplainsController(this);
  }

  public ObservableList<Element> getElements() {
    return elements;
  }

  @Override
  public Pane getPane() {
    return parentPane;
  }

  /** Lớp chứa phần giải thích. */
  public class Element implements IField {
    private final VBox explainPane;
    private final ElementController controller;

    private TextField wordMeaning;
    private Text wordClass;
    private Label wordDefinition;
    private Label exampleSentence;
    private ObservableList<Button> synonymsButtons;
    private Button editExplainButton;
    private Button deleteExplainButton;

    /** Khởi tạo ExplainField. */
    public Element() {
      explainPane = new VBox();
      explainPane.getStyleClass().add("explain-pane");

      initWordMeaning();
      initWordClass();
      initWordDefinition();
      initDeleteExplainButton();
      initExampleSentence();
      initEditExplainButton();

      explainPane.getChildren().addAll(
          wordClass, 
          wordMeaning, 
          wordDefinition,
          exampleSentence,
          deleteExplainButton,
          editExplainButton);

      controller = new ElementController(this);
    }

    void initWordMeaning() {
      wordMeaning = new TextField();
      wordMeaning.setEditable(false); // mặc định không được sửa
      wordMeaning.getStyleClass().add("word-meaning");
    }

    void initWordClass() {
      wordClass = new Text();
      ManagedUtils.bindVisible(wordClass);
      wordClass.getStyleClass().add("word-class");
    }

    void initWordDefinition() {
      wordDefinition = new Label();
      ManagedUtils.bindVisible(wordDefinition);
      wordDefinition.getStyleClass().add("normal-text");

      wordDefinition.setWrapText(true);
      wordDefinition.setTextAlignment(TextAlignment.LEFT);
    }

    void initDeleteExplainButton() {
      deleteExplainButton = new Button();
      ManagedUtils.bindVisible(deleteExplainButton);
      deleteExplainButton.getStyleClass().add("transparent-button");

      deleteExplainButton.setTooltip(new Tooltip("Xoá bản dịch này"));

      ImageView imageView = ImageUtils.getFitSquareImage(
          DictionaryApplication.INSTANCE.config.getImagesPath() + "/trash.png", 10);

      deleteExplainButton.setGraphic(imageView);
    }

    void initExampleSentence() {
      exampleSentence = new Label();
      ManagedUtils.bindVisible(exampleSentence);
      exampleSentence.setWrapText(true);
      exampleSentence.getStyleClass().add("normal-text");
    }

    void initEditExplainButton() {
      editExplainButton = new Button();
      ManagedUtils.bindVisible(editExplainButton);
      editExplainButton.getStyleClass().add("transparent-button");

      editExplainButton.setTooltip(new Tooltip("Sửa bản dịch này"));

      ImageView imageView = ImageUtils.getFitSquareImage(
          DictionaryApplication.INSTANCE.config.getImagesPath() + "/pencil.png", 10);

      editExplainButton.setGraphic(imageView);
    }

    public TextField getWordMeaning() {
      return wordMeaning;
    }

    public Text getWordClass() {
      return wordClass;
    }

    public Label getWordDefinition() {
      return wordDefinition;
    }

    public Label getExampleSentence() {
      return exampleSentence;
    }

    public ObservableList<Button> getSynonymsButton() {
      return synonymsButtons;
    }

    public Button getEditExplainButton() {
      return editExplainButton;
    }

    public Button getDeleteExplainButton() {
      return deleteExplainButton;
    }

    @Override
    public Pane getPane() {
      return explainPane;
    }

    @Override
    public ElementController getController() {
      return controller;
    }
  }

  @Override
  public ExplainsController getController() {
    return controller;
  }
}
