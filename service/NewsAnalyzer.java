package service;

import enums.Classification;

public class NewsAnalyzer {
    public Classification analyze(String text) {
        int score = 0;

        if (!text.contains("FONTE")) score++;
        if (text.contains("!!!")) score++;
        if (text.contains("URGENTE")) score++;
        if (text.length() < 10) score++;

        if (score == 0) {
            return Classification.CONFIAVEL;
        } else if (score == 1) {
            return Classification.DUVIDOSA;
        }
        return Classification.FALSA;
    }
}
