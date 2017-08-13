package pl.sayen.xmlAnalysisApi.service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Mariusz Szymanski
 */
public class ElementAnalyzer {

    public boolean alreadyExecuted = false;
    private int postTypeId;

    /**
     * This method checks every attribute of xml element and use analysisService to update necessary data
     *
     * @param service    service for xml parser to store and process the analyses results
     * @param attributes map of xml element attributes to analyse
     */
    public void analyzeElement(AnalysisService service, Map<String, String> attributes) {

        attributes.forEach((key, value) -> {
            switch (key) {
                case "PostTypeId": {
                    handlePostTypeId(service, value);
                    break;
                }
                case "AcceptedAnswerId":
                    handleAcceptedAnswerId(service, value);
                    break;
                case "CreationDate": {
                    handleCreationDate(service, value);
                    break;
                }
                case "Score": {
                    handleScore(service, value);
                    break;
                }
                default: {
                    break;
                }
            }
        });
    }

    private void handlePostTypeId(AnalysisService service, String value) {
        postTypeId = Integer.parseInt(value);
        if (postTypeId == 1) service.incrementQuestions();
        if (postTypeId == 2) service.incrementAnswers();
    }

    private void handleAcceptedAnswerId(AnalysisService service, String value) {
        if (!(value == null)) service.incrementAcceptedAnswers();
    }

    private void handleCreationDate(AnalysisService service, String value) {
        LocalDateTime creationDateTime = LocalDateTime.parse(value);
        if (!alreadyExecuted) {
            service.setFirstPostCreationTime(creationDateTime);
            alreadyExecuted = true;
        }
        service.setLastPostCreationTime(LocalDateTime.parse(value));
    }

    private void handleScore(AnalysisService service, String value) {
        if (postTypeId == 1 || postTypeId == 2) {
            service.addScore(Integer.valueOf(value));
        }
    }
}
