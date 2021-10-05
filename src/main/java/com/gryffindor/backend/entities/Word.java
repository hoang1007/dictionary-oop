package com.gryffindor.backend.entities;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private String wordTarget;
    private String wordExplain;
    private String wordSpelling; // phiên âm.
    private String wordType; // loai tu
    private List<ExampleSentence> exampleSentences;

    public Word() {
        this.wordType = "";
        this.wordExplain = "";
        this.wordSpelling = "";
        this.wordTarget = "";
        this.exampleSentences = new ArrayList<>();
    }

    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.wordSpelling = "";
        this.wordType = "";
        exampleSentences = new ArrayList<>();
    }

    /** Constructor have spelling */
    public Word(String wordTarget, String wordSpelling, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordSpelling = wordSpelling;
        this.wordExplain = wordExplain;
        this.wordType = "";
        exampleSentences = new ArrayList<>();
    }

    public Word(String wordTarget, String wordSpelling, String wordExplain, String wordType) {
        this.wordTarget = wordTarget;
        this.wordSpelling = wordSpelling;
        this.wordExplain = wordExplain;
        this.wordType = wordType;
        exampleSentences = new ArrayList<>();
    }

    public Word setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
        return this;
    }

    public String getWordTarget() {
        return this.wordTarget;
    }

    public Word setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
        return this;
    }

    public String getWordExplain() {
        return this.wordExplain;
    }

    public Word setWordSpelling(String wordSpelling) {
        this.wordSpelling = wordSpelling;
        return this;
    }

    public String getWordSpelling() {
        return this.wordSpelling;
    }

    public Word setWordType(String wordType) {
        this.wordType = wordType;
        return this;
    }

    public String getWordType() {
        return this.wordType;
    }

    public Word setExampleSentences(List<ExampleSentence> exampleSentences) {
        this.exampleSentences = exampleSentences;
        return this;
    }

    public List<ExampleSentence> getExampleSentences() {
        return this.exampleSentences;
    }

    @Override
    public String toString() {
        return wordTarget + "\n" + wordExplain;
    }
}
