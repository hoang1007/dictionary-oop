package com.gryffindor.backend.entities;

import java.util.ArrayList;
import java.util.List;

import com.gryffindor.backend.utils.TextUtils;

public class Word {
    private String wordTarget = TextUtils.empty();
    private String wordSpelling = TextUtils.empty(); // phiên âm.
    private String wordClass = TextUtils.empty();
    private List<Translation> translations = new ArrayList<>();

    public Word() { }

    public Word(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    /** Constructor have spelling */
    public Word(String wordTarget, String wordSpelling) {
        this.wordTarget = wordTarget;
        this.wordSpelling = wordSpelling;
    }

    public Word(String wordTarget, String wordSpelling, List<Translation> translations) {
        this.wordTarget = wordTarget;
        this.wordSpelling = wordSpelling;
        this.translations = translations;
    }

    public Word setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
        return this;
    }

    public String getWordTarget() {
        return this.wordTarget;
    }

    public Word setWordSpelling(String wordSpelling) {
        this.wordSpelling = wordSpelling;
        return this;
    }

    public String getWordSpelling() {
        return this.wordSpelling;
    }

    public Word setWordClass(String wordClass) {
        this.wordClass = wordClass;
        return this;
    }

    public String getWordClass() {
        return this.wordClass;
    }

    public Word addTranslation(Translation... translations) {
        for (Translation translation : translations) {
            this.translations.add(translation);
        }

        return this;
    }

    public List<Translation> getTranslations() {
        return this.translations;
    }

    @Override
    public String toString() {
      return wordTarget;
    }
}
