package pl.sayen.xmlanalysisapi.model;

import java.time.LocalDateTime;

/**
 * @author Mariusz Szymanski
 */
public class AnalysisDetails {

    private LocalDateTime firstPost;
    private LocalDateTime lastPost;
    private int totalPosts;
    private int questions;
    private int answers;
    private int totalAcceptedPosts;
    private int avgScore;
    private int minScore;
    private int maxScore;

    public LocalDateTime getFirstPost() {
        return firstPost;
    }

    public void setFirstPost(LocalDateTime firstPost) {
        this.firstPost = firstPost;
    }

    public LocalDateTime getLastPost() {
        return lastPost;
    }

    public void setLastPost(LocalDateTime lastPost) {
        this.lastPost = lastPost;
    }

    public int getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(int totalPosts) {
        this.totalPosts = totalPosts;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public int getAnswers() {
        return answers;
    }

    public void setAnswers(int answers) {
        this.answers = answers;
    }

    public int getTotalAcceptedPosts() {
        return totalAcceptedPosts;
    }

    public void setTotalAcceptedPosts(int totalAcceptedPosts) {
        this.totalAcceptedPosts = totalAcceptedPosts;
    }

    public int getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(int avgScore) {
        this.avgScore = avgScore;
    }
    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
}
