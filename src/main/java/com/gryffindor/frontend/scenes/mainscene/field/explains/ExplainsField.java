package com.gryffindor.frontend.scenes.mainscene.field.explains;

import com.gryffindor.frontend.scenes.mainscene.field.IField;
import com.gryffindor.frontend.utils.ManagedUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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

    private Text wordClass;
    private ObservableList<TranslationField> translationFields;
    private ObservableList<Button> synonymsButtons;

    /** Khởi tạo ExplainField. */
    public Element() {
      explainPane = new VBox();
      explainPane.getStyleClass().add("explain-pane");

      initWordClass();
      initSynonymButtons();
      initTranslationFields();

      controller = new ElementController(this);
    }

    void initWordClass() {
      wordClass = new Text();
      ManagedUtils.bindVisible(wordClass);
      wordClass.getStyleClass().add("word-class");

      explainPane.getChildren().add(wordClass);
    }

    void initSynonymButtons() {
      synonymsButtons = FXCollections.observableArrayList();
    }

    void initTranslationFields() {
      translationFields = FXCollections.observableArrayList();
    }

    public Text getWordClass() {
      return wordClass;
    }

    public ObservableList<Button> getSynonymsButton() {
      return synonymsButtons;
    }

    public ObservableList<TranslationField> getTranslationFields() {
      return translationFields;
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
