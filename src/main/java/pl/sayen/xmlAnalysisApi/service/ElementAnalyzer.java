package pl.sayen.xmlAnalysisApi.service;

import org.xml.sax.Attributes;

import java.time.LocalDateTime;

/**
 * @author Mariusz Szymanski
 */
public class ElementAnalyzer {

    public static boolean alreadyExecuted = false;

    /**
     * This method checks every attribute of xml element and use analysisService to update necessary data
     *
     * @param service    service for xml parser to temporary store and process results in memory
     * @param attributes list of xml element attributes to analyse
     */
    public static void analyzeElement(AnalysisService service, Attributes attributes) {

        // get the number of attributes in the element
        int length = attributes.getLength();

        // initialize postTypeId; postTypeId = 1 for Question, postTypeId = 2 for Answer
        int postTypeId = 0;

        // process each attribute
        for (int i = 0; i < length; i++) {
            String attributeName = attributes.getQName(i);
            String attributeValue = attributes.getValue(i);

            switch (attributeName) {

                case "PostTypeId": {
                    postTypeId = Integer.parseInt(attributeValue);
                    incrementPostsNumber(service, postTypeId);
                    break;
                }

                case "AcceptedAnswerId":
                    service.incrementAcceptedAnswers();
                    break;

                case "CreationDate": {
                    if (!alreadyExecuted) {
                        service.setFirstPostCreationTime(LocalDateTime.parse(attributeValue));
                        alreadyExecuted = true;
                    }
                    service.setLastPostCreationTime(LocalDateTime.parse(attributeValue));
                    break;
                }

                case "Score": {
                    if (postTypeId == 1 || postTypeId == 2) {
                        service.addScore(Integer.valueOf(attributeValue));
                    }
                    break;
                }

                default: {
                    break;
                }
            }
        }
    }

    private static void incrementPostsNumber(AnalysisService service, int postTypeId) {
        if (postTypeId == 1) service.incrementQuestions();
        if (postTypeId == 2) service.incrementAnswers();
    }
}

