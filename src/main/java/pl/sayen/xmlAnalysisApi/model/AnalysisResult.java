package pl.sayen.xmlAnalysisApi.model;

import java.time.LocalDateTime;

/**
 * @author Mariusz Szymanski
 */
public class AnalysisResult {

    private LocalDateTime analyseDate;
    private AnalysisDetails details;

    public AnalysisResult() {
        this.details = new AnalysisDetails();
    }

    public LocalDateTime getAnalyseDate() {
        return analyseDate;
    }

    public void setAnalyseDate(LocalDateTime analyseDate) {
        this.analyseDate = analyseDate;
    }

    public AnalysisDetails getDetails() {
        return details;
    }
}
