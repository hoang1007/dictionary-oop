package com.gryffindor.frontend.scenes.mainscene.field;

import com.gryffindor.backend.entities.Word;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
  private ObservableList<Element> elements;

  /** Khởi tạo vùng giải thích. */
  public ExplainsField() {
    parentPane = new VBox();
    parentPane.getStyleClass().add("padding-pane");
    parentPane.setSpacing(10); // đặt khoảng cách giữa các phần giải thích
    elements = FXCollections.observableArrayList();
  }

  public ObservableList<Element> getElements() {
    return elements;
  }

  @Override
  public Pane getPane() {
    return parentPane;
  }

  /**
   * Thêm phần giải thích.
   * @param word từ muốn thêm
   */
  public void add(Word word) {
    elements.add(new Element().setWord(word));
  }

  /**
   * Xóa các phần giải thích hiện tại 
   * và thêm vào các phần giải thích mới.
   * @param words danh sách từ muốn thêm
   */
  public void setAll(Word...words) {
    parentPane.getChildren().clear();
    elements.clear();
    
    for (Word word : words) {
      this.add(word);
    }
  }

  /** Lớp chứa phần giải thích. */
  public class Element implements IField {
    private final VBox explainPane;
    private Word word;

    private Label wordMeaning;
    private Text wordClass;
    private Label wordDefinition;
    private Label exampleSentence;
    private ObservableList<Button> synonymsButtons;

    /** Khởi tạo ExplainField. */
    public Element() {
      explainPane = new VBox();
      explainPane.getStyleClass().add("explain-pane");

      initWordMeaning();
      initWordClass();
      initWordDefinition();

      explainPane.getChildren().addAll(wordClass, wordMeaning, wordDefinition);
    }

    void initWordMeaning() {
      wordMeaning = new Label();
      wordMeaning.setWrapText(true);
      wordMeaning.getStyleClass().add("word-meaning");
    }

    void initWordClass() {
      wordClass = new Text();
      wordClass.getStyleClass().add("word-class");
    }

    void initWordDefinition() {
      wordDefinition = new Label();

      wordDefinition.setWrapText(true);
      wordDefinition.setTextAlignment(TextAlignment.LEFT);
    }

    public Label getWordMeaning() {
      return wordMeaning;
    }

    public Text getWordClass() {
      return wordClass;
    }

    public Label getWordDefinition() {
      return wordDefinition;
    }

    public Word getWord() {
      return word;
    }

    /**
     * Tự động đặt loại từ, định nghĩa, giải thích... theo từ.
     * 
     * @param word từ muốn đặt
     */
    public Element setWord(Word word) {
      this.word = word;

      wordClass.setText("danh từ");
      wordMeaning.setText(word.getWordExplain());
      wordDefinition
        .setText("cơ quan sinh sản hữu tính của cây hạt kín, thường có màu sắc và hương thơm");

      parentPane.getChildren().add(explainPane);
      return this;
    }

    @Override
    public Pane getPane() {
      return explainPane;
    }
  }
}
