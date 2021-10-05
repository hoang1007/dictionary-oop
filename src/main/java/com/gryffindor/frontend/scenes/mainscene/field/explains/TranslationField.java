package com.gryffindor.frontend.scenes.mainscene.field.explains;

import com.gryffindor.DictionaryApplication;
import com.gryffindor.frontend.scenes.mainscene.field.IController;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TranslationField implements IField {
    private GridPane pane;

    private ObservableList<Label> exampleSentences;
    private TextField wordExplain;
    private Button editExplainButton;
    private Button deleteExplainButton;

    private TranslationController controller;

    public TranslationField() {
        pane = new GridPane();

        initExampleSentences();
        initWordExplain();
        initEditExplainButton();
        initDeleteExplainButton();

        controller = new TranslationController(this);
    }

    void initWordExplain() {
        wordExplain = new TextField();
        wordExplain.setEditable(false); // mặc định không được sửa
        wordExplain.getStyleClass().add("word-meaning");

        pane.getChildren().add(wordExplain);
        GridPane.setConstraints(wordExplain, 0, 0);
    }

    void initExampleSentences() {
        exampleSentences = FXCollections.observableArrayList();

        pane.getChildren().addAll(exampleSentences);
        for (int i = 0; i < exampleSentences.size(); i++) {
            GridPane.setConstraints(exampleSentences.get(i), i + 1, 0);
        }
    }

    void initDeleteExplainButton() {
        deleteExplainButton = new Button();
        ManagedUtils.bindVisible(deleteExplainButton);
        deleteExplainButton.getStyleClass().add("transparent-button");

        deleteExplainButton.setTooltip(new Tooltip("Xoá bản dịch này"));

        ImageView imageView = ImageUtils
                .getFitSquareImage(DictionaryApplication.INSTANCE.config.getImagesPath() + "/trash.png", 10);

        deleteExplainButton.setGraphic(imageView);

        pane.getChildren().add(deleteExplainButton);
        GridPane.setConstraints(deleteExplainButton, 0, 1);
    }

    void initEditExplainButton() {
        editExplainButton = new Button();
        ManagedUtils.bindVisible(editExplainButton);
        editExplainButton.getStyleClass().add("transparent-button");

        editExplainButton.setTooltip(new Tooltip("Sửa bản dịch này"));

        ImageView imageView = ImageUtils
                .getFitSquareImage(DictionaryApplication.INSTANCE.config.getImagesPath() + "/pencil.png", 10);

        editExplainButton.setGraphic(imageView);

        pane.getChildren().add(editExplainButton);
        GridPane.setConstraints(editExplainButton, 1, 1);
    }

    @Override
    public Pane getPane() {
        return null;
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

    public ObservableList<Label> getExampleSentences() {
        return exampleSentences;
    }

    public TextField getWordExplain() {
        return wordExplain;
    }
}
