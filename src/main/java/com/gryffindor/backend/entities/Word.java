package com.gryffindor.backend.entities;

public class Word {
    private String wordTarget;
    private String wordExplain;
    private String wordSpelling = null; // phiên âm.
    private String wordType = null; // loai tu

    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.wordSpelling = null;
        this.wordType = null;
    }

    /** Constructor have spelling */
    public Word(String wordTarget, String wordSpelling, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordSpelling = wordSpelling;
        this.wordExplain = wordExplain;
        this.wordType = null;
    }

    public Word(String wordTarget, String wordSpelling, String wordExplain, String wordType) {
        this.wordTarget = wordTarget;
        this.wordSpelling = wordSpelling;
        this.wordExplain = wordExplain;
        this.wordType = wordType;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public String getWordTarget() {
        return this.wordTarget;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public String getWordExplain() {
        return this.wordExplain;
    }

    public void setWordSpelling(String wordSpelling) { this.wordSpelling = wordSpelling; }

    public String getWordSpelling() { return this.wordSpelling; }

    public void setWordType(String wordType) { this.wordType = wordType; }

    public String getWordType() { return this.wordType; }

    @Override
    public String toString() {
        return wordTarget + "\n" + wordExplain;
    }
}
