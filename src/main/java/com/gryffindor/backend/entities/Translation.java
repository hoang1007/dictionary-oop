package com.gryffindor.backend.entities;

import java.util.ArrayList;
import java.util.List;

import com.gryffindor.backend.utils.TextUtils;

public class Translation {
    private String wordExplain = TextUtils.empty();
    private List<ExampleSentence> exampleSentences = new ArrayList<>();

    public Translation() {
    }

    public Translation(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public Translation addExampleSentences(ExampleSentence... exampleSentences) {
        for (ExampleSentence exampleSentence : exampleSentences) {
            this.exampleSentences.add(exampleSentence);
        }

        return this;
    }

    public List<ExampleSentence> getExampleSentences() {
        return this.exampleSentences;
    }

    public Translation setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
        return this;
    }

    public String getWordExplain() {
        return this.wordExplain;
    }
}
