package pl.sayen.xmlanalysisapi.model;

import java.time.LocalDateTime;

/**
 * @author Mariusz Szymanski
 */
public class AnalysisResult {

    private LocalDateTime analyseDate;
    private AnalysisDetails details;

    public LocalDateTime getAnalyseDate() {
        return analyseDate;
    }

    public void setAnalyseDate(LocalDateTime analyseDate) {
        this.analyseDate = analyseDate;
    }

    public AnalysisDetails getDetails() {
        return details;
    }

    public void setDetails(AnalysisDetails details) {
        this.details = details;
    }
}
