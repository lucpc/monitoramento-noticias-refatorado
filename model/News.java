package model;

import enums.Classification;

public class News {
    private String text;
    private Classification classification;

    public News(String text, Classification classification) {
        this.text = text;
        this.classification = classification;
    }

    public String getText() {
        return text;
    }

    public Classification getClassification() {
        return classification;
    }
}