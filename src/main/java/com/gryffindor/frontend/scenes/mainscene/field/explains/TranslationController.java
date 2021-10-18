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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

public class TranslationController implements IController {
  TranslationField translationField;
  Translation translation;
  Word word;

  /** Khởi tạo controller. */
  public TranslationController(TranslationField translationField) {
    this.translationField = translationField;

    onClickEditButton();
    onClickDeleteButton();
    onTranslationEdited();
  }

  /** Sửa bản dịch khi click vào nút. */
  private void onClickEditButton() {
    // chỉnh sửa bản dịch
    translationField
        .getEditExplainButton()
        .setOnAction(
            event -> {
              translationField.getWordExplain().setEditable(true);
            });
  }

  /** Xóa bản dịch khi click xóa. */
  private void onClickDeleteButton() {
    // xóa bản dịch
    translationField
        .getDeleteExplainButton()
        .setOnAction(
            event -> {
              translationField.getPane().setVisible(false);
              PageManager.INSTANCE.showPage(LoadingPage.class);

              new Thread(
                      () -> {
                        if (DictionaryApplication.INSTANCE.dictionaryManagement.deleteTranslation(
                            this.word, this.translation)) {

                          Platform.runLater(
                              () ->
                                  new AlertDialog(AlertType.INFORMATION)
                                      .setContent(
                                          DictionaryApplication.INSTANCE.config
                                              .getContributeThanks())
                                      .show());
                        }

                        Platform.runLater(() -> PageManager.INSTANCE.restorePage());
                      })
                  .start();
            });
  }

  /** Đặt các thuộc tính liên quan đến word. */
  public void setTranslation(Word word, Translation translation) {
    this.translation = translation;
    this.word = word;
    translationField.getWordExplain().setText(translation.getWordExplain());

    // nếu nguồn tìm kiếm từ google
    // không cho phép xóa hoặc sửa bản dịch
    if (word.getSource().equals(Word.Source.GOOGLE)) {
      translationField.getDeleteExplainButton().setVisible(false);
      translationField.getEditExplainButton().setVisible(false);
    } else {
      for (ExampleSentence example : translation.getExampleSentences()) {
        addExampleSentences(example);
      }
    }
  }

  /** @param exampleSentence */
  private void addExampleSentences(ExampleSentence exampleSentence) {
    Label label = new Label(exampleSentence.toString());
    translationField.getExamples().getList().add(label);
  }

  private void onTranslationEdited() {
    translationField
        .getWordExplain()
        .setOnKeyPressed(
            event -> {
              if (event.getCode().equals(KeyCode.ENTER)) {
                event.consume();
                translationField.getWordExplain().setEditable(false);

                // bật loading page
                PageManager.INSTANCE.showPage(LoadingPage.class);

                // Sau khi kết thúc chỉnh sửa
                // tạo một luồng để gửi request chỉnh sửa tới nguồn
                new Thread(
                        () -> {
                          // Lấy vị trí của dấu nháy
                          int caretPos = translationField.getWordExplain().getCaretPosition();
                          System.out.println("caret pos: " + caretPos);
                          StringBuilder newExStrb =
                              new StringBuilder(translationField.getWordExplain().getText());

                          // Xóa kí tự enter vừa nhấn
                          String newExplain = newExStrb.deleteCharAt(caretPos - 1).toString();

                          System.out.println("Changed: " + newExplain);
                          // Vì textarea tự xuống dòng khi nhấn enter nên set lại text
                          Platform.runLater(
                              () -> translationField.getWordExplain().setText(newExplain));

                          // tạo bản dịch mới và cập nhật
                          Translation newTrans = this.translation.clone();
                          newTrans.setWordExplain(newExplain);

                          if (DictionaryApplication.INSTANCE.dictionaryManagement.updateTranslation(
                              word, this.translation, newTrans)) {

                            // đặt bản dịch hiện tại thành bản dịch mới
                            this.translation = newTrans;
                            Platform.runLater(
                                () ->
                                    new AlertDialog(AlertType.INFORMATION)
                                        .setContent(
                                            DictionaryApplication.INSTANCE.config
                                                .getContributeThanks())
                                        .show());
                          }

                          Platform.runLater(() -> PageManager.INSTANCE.restorePage());
                        })
                    .start();
              }
            });
  }
}
