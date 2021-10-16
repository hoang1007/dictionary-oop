package com.gryffindor.frontend.scenes.mainscene.field.explains;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.backend.entities.ExampleSentence;
import com.gryffindor.backend.entities.Translation;
import com.gryffindor.backend.entities.Word;
import com.gryffindor.frontend.entities.AlertDialog;
import com.gryffindor.frontend.scenes.mainscene.PageManager;
import com.gryffindor.frontend.scenes.mainscene.field.IController;
import com.gryffindor.frontend.scenes.mainscene.page.LoadingPage;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;

public class TranslationController implements IController {
  TranslationField translationField;
  Translation translation;
  Word word;

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
      PageManager.INSTANCE.showPage(LoadingPage.class);

      new Thread(() -> {
        if (DictionaryApplication.INSTANCE.dictionaryManagement
          .deleteTranslation(this.word, this.translation)) {

          Platform.runLater(() -> new AlertDialog(AlertType.INFORMATION)
              .setContent(DictionaryApplication.INSTANCE.config.getContributeThanks()).show());
        }

        Platform.runLater(() -> PageManager.INSTANCE.restorePage());
      }).start();
    });
  }

  public void setTranslation(Word word, Translation translation) {
    this.translation = translation;
    this.word = word;
    translationField.getWordExplain().setText(translation.getWordExplain());

    if (word.getSource().equals(Word.Source.GOOGLE)) {
      translationField.getDeleteExplainButton().setVisible(false);
      translationField.getEditExplainButton().setVisible(false);
    } else {
      for (ExampleSentence example : translation.getExampleSentences()) {
        addExampleSentences(example);
      }
    }
  }

  void addExampleSentences(ExampleSentence exampleSentence) {
    Label label = new Label(exampleSentence.toString());
    translationField.getExamples().getList().add(label);
  }

  void onTranslationEdited() {
    translationField.getWordExplain().setOnKeyPressed(event -> {
      if (event.getCode().equals(KeyCode.ENTER)) {
        translationField.getWordExplain().setEditable(false);

        PageManager.INSTANCE.showPage(LoadingPage.class);

        new Thread(() -> {
          Translation newTrans = this.translation.clone();
          newTrans.setWordExplain(translationField.getWordExplain().getText());

          if (DictionaryApplication.INSTANCE.dictionaryManagement
            .updateTranslation(word, this.translation, newTrans)) {
            
            this.translation = newTrans;
            Platform.runLater(() -> new AlertDialog(AlertType.INFORMATION)
              .setContent(DictionaryApplication.INSTANCE.config.getContributeThanks()).show());
          }

          Platform.runLater(() -> PageManager.INSTANCE.restorePage());
        }).start();
      }
    });
  }
}
