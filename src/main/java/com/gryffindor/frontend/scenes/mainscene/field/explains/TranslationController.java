package com.gryffindor.frontend.scenes.mainscene.field.explains;

import com.gryffindor.backend.entities.ExampleSentence;
import com.gryffindor.backend.entities.Translation;
import com.gryffindor.frontend.scenes.mainscene.field.IController;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

public class TranslationController implements IController {
    TranslationField translationField;

    public TranslationController(TranslationField translationField) {
        this.translationField = translationField;

        onClickEditButton();
        onClickDeleteButton();
        onTranslationEdited();
    }

    void onClickEditButton() {
        // chỉnh sửa bản dịch
        translationField.getEditExplainButton().setOnAction(event -> {
            translationField.getWordExplain().setEditable(true);
        });
    }

    void onClickDeleteButton() {
        // xóa bản dịch
        translationField.getDeleteExplainButton().setOnAction(event -> {
            translationField.getPane().setVisible(false);
        });
    }

    public void setTranslation(Translation translation) {
        translationField.getWordExplain().setText(translation.getWordExplain());

        for (ExampleSentence example : translation.getExampleSentences()) {
            addExampleSentences(example);
        }
    }

    void addExampleSentences(ExampleSentence exampleSentence) {
        Label label = new Label(exampleSentence.toString());
        translationField.getExampleSentences().add(label);
    }

    void onTranslationEdited() {
        translationField.getWordExplain().setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                translationField.getPane().setVisible(false);
            }
        });
    }
}
