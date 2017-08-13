package pl.sayen.xmlAnalysisApi.service;

import pl.sayen.xmlAnalysisApi.model.AnalysisResult;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariusz Szymanski
 */
public class AnalysisService {

    private URL url;
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

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
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
        result.getDetails().setAvgScore(Calculator.calculateAvgScore(scores));
        result.getDetails().setMinScore(Calculator.calculateMinScore(scores));
        result.getDetails().setMaxScore(Calculator.calculateMaxScore(scores));
    }
}
