package com.gryffindor.backend.entities;

public class ExampleSentence {
    private String langTo;
    private String langFrom;

    public ExampleSentence(String langTo, String langFrom) {
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
        return "Langto:" + langTo + ", langFrom:" + langFrom;
    }
}
