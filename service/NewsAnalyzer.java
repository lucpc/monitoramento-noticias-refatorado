package service;

import enums.Classification;

public class NewsAnalyzer {
    private static final int CONFIAVEL = 0;
    private static final int DUVIDOSA = 1;

    public Classification analyze(String text) {
        int score = 0;

        if (!text.contains("FONTE")) score++;
        if (text.contains("!!!")) score++;
        if (text.contains("URGENTE")) score++;
        if (text.length() < 10) score++;

        if (score == CONFIAVEL) {
            return Classification.CONFIAVEL;
        } else if (score == DUVIDOSA) {
            return Classification.DUVIDOSA;
        }
        return Classification.FALSA;
    }
}
