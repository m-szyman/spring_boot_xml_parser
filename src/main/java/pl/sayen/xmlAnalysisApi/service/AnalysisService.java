package pl.sayen.xmlAnalysisApi.service;

import pl.sayen.xmlAnalysisApi.model.AnalysisDetails;
import pl.sayen.xmlAnalysisApi.model.AnalysisResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariusz Szymanski
 */
public class AnalysisService {

    private String url;
    private int questionCounter = 0;
    private int answerCounter = 0;
    private int acceptedAnswersCounter = 0;
    private List<Integer> score = new ArrayList<>();
    private LocalDateTime firstPostCreationTime;
    private LocalDateTime lastPostCreationTime;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void incrementQuestionCounter() {
        questionCounter++;
    }

    public void incrementAnswerCounter() {
        answerCounter++;
    }

    public void incrementAcceptedAnswersCounter() {
        acceptedAnswersCounter++;
    }

    public void setFirstPostCreationTime(String value) {
        firstPostCreationTime = LocalDateTime.parse(value);
    }

    public void setLastPostCreationTime(LocalDateTime lastPostCreationTime) {
        this.lastPostCreationTime = lastPostCreationTime;
    }

    public void addScore(String attributeValue) {
        score.add(Integer.valueOf(attributeValue));
    }

    public AnalysisResult generateAnalysisResult() {

        AnalysisDetails details = new AnalysisDetails();

        details.setFirstPost(firstPostCreationTime);
        details.setLastPost(lastPostCreationTime);
        details.setTotalPosts(questionCounter + answerCounter);
        details.setQuestions(questionCounter);
        details.setAnswers(answerCounter);
        details.setTotalAcceptedPosts(acceptedAnswersCounter);
        details.setAvgScore(this.calculateAvgScore());
        details.setMinScore(this.calculateMinScore());
        details.setMaxScore(this.calculateMaxScore());

        AnalysisResult result = new AnalysisResult();

        result.setAnalyseDate(LocalDateTime.now());
        result.setDetails(details);

        return result;
    }

    private int calculateAvgScore() {
        double average = score.stream()
                .mapToInt(i -> i)
                .average()
                .orElse(0);
        return (int) average;
    }

    private int calculateMinScore() {
        double minScore = score.stream()
                .mapToInt(i -> i)
                .min()
                .orElse(0);
        return (int) minScore;
    }

    private int calculateMaxScore() {
        double maxScore = score.stream()
                .mapToInt(i -> i)
                .max()
                .orElse(0);
        return (int) maxScore;
    }
}
