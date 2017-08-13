package pl.sayen.xmlAnalysisApi.service;

import java.util.List;

/**
 * @author Mariusz Szymanski
 */
public class Calculator {

    public static int calculateAvgScore(List<Integer> scores) {
        double average = scores.stream()
                .mapToInt(i -> i)
                .average()
                .orElse(0);
        return (int) average;
    }

    public static int calculateMinScore(List<Integer> scores) {
        double minScore = scores.stream()
                .mapToInt(i -> i)
                .min()
                .orElse(0);
        return (int) minScore;
    }

    public static int calculateMaxScore(List<Integer> scores) {
        double maxScore = scores.stream()
                .mapToInt(i -> i)
                .max()
                .orElse(0);
        return (int) maxScore;
    }
}
