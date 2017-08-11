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
                    postTypeId = Integer.parseInt(value);
                    incrementPostsNumber(service, Integer.parseInt(value));
                    break;
                }
                case "AcceptedAnswerId":
                    if (!(value == null))service.incrementAcceptedAnswers();
                    break;
                case "CreationDate": {
                    if (!alreadyExecuted) {
                        service.setFirstPostCreationTime(LocalDateTime.parse(value));
                        alreadyExecuted = true;
                    }
                    service.setLastPostCreationTime(LocalDateTime.parse(value));
                    break;
                }
                case "Score": {
                    if (postTypeId == 1 || postTypeId == 2) {
                        service.addScore(Integer.valueOf(value));
                    }
                    break;
                }
                default: {
                    break;
                }
            }
        });
    }

    private static void incrementPostsNumber(AnalysisService service, int postTypeId) {
        if (postTypeId == 1) service.incrementQuestions();
        if (postTypeId == 2) service.incrementAnswers();
    }
}
