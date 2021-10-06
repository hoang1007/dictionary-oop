package com.gryffindor.backend.entities;

public class ExampleSentence {
    private String langTo;
    private String langFrom;

    public ExampleSentence(String langFrom, String langTo) {
        this.langTo = langTo;
        this.langFrom = langFrom;
    }

    public ExampleSentence() {
        langTo = "";
        langFrom = "";
    }

    public String getLangTo() {
        return langTo;
    }

    public String getLangFrom() {
        return langFrom;
    }

    @Override
    public String toString() {
        return langFrom + ":\t" + langTo;
    }
}