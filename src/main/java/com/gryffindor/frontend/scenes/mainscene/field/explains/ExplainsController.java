package com.gryffindor.frontend.scenes.mainscene.field.explains;

import com.gryffindor.backend.entities.Word;
import com.gryffindor.frontend.scenes.mainscene.field.IController;

import java.util.Collection;

public class ExplainsController implements IController {
  private ExplainsField explainsField;

  public ExplainsController(ExplainsField explainsField) {
    this.explainsField = explainsField;
    explainsField.getPane().setVisible(false); // mặc định ẩn
  }

  /** Khởi tạo thuộc tính 
   * liên quan đến {@link Word}.*/
  public void setWords(Collection<Word> words) {
    explainsField.getPane().setVisible(true);

    explainsField.getPane().getChildren().clear();

    for (Word word : words) {
      ExplainsField.Element element = explainsField.new Element();
      element.getController().setWord(word);

      explainsField.getPane().getChildren().add(element.getPane());
    }
  }
}
