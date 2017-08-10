package pl.sayen.xmlAnalysisApi.service;

import pl.sayen.xmlAnalysisApi.model.AnalysisResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariusz Szymanski
 */
public class AnalysisService {

    private String url;
    private List<Integer> scores = new ArrayList<>();
    private AnalysisResult result;

    public AnalysisService() {
        result = new AnalysisResult();
    }

    public AnalysisResult getAnalysisResult() {
        result.setAnalyseDate(LocalDateTime.now());
        calculateScoreStats();
        return result;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void incrementQuestions() {
        int questions = result.getDetails().getQuestions();
        result.getDetails().setQuestions(++questions);
    }

    public void incrementAnswers() {
        int answers = result.getDetails().getAnswers();
        result.getDetails().setAnswers(++answers);
    }

    public void incrementAcceptedAnswers() {
        int acceptedAnswers = result.getDetails().getTotalAcceptedPosts();
        result.getDetails().setTotalAcceptedPosts(++acceptedAnswers);
    }

    public void setFirstPostCreationTime(LocalDateTime firstPostCreationTime) {
        result.getDetails().setFirstPost(firstPostCreationTime);
    }

    public void setLastPostCreationTime(LocalDateTime lastPostCreationTime) {
        result.getDetails().setLastPost(lastPostCreationTime);
    }

    public void addScore(int value) {
        scores.add(value);
    }

    private void calculateScoreStats() {
        result.getDetails().setAvgScore(this.calculateAvgScore());
        result.getDetails().setMinScore(this.calculateMinScore());
        result.getDetails().setMaxScore(this.calculateMaxScore());
    }

    private int calculateAvgScore() {
        double average = scores.stream()
                .mapToInt(i -> i)
                .average()
                .orElse(0);
        return (int) average;
    }

    private int calculateMinScore() {
        double minScore = scores.stream()
                .mapToInt(i -> i)
                .min()
                .orElse(0);
        return (int) minScore;
    }

    private int calculateMaxScore() {
        double maxScore = scores.stream()
                .mapToInt(i -> i)
                .max()
                .orElse(0);
        return (int) maxScore;
    }
}
