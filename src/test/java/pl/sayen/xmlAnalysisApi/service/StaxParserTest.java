package pl.sayen.xmlAnalysisApi.service;

import org.junit.Before;
import org.junit.Test;
import pl.sayen.xmlAnalysisApi.model.AnalysisResult;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * @author Mariusz Szymanski
 */
public class StaxParserTest {

    private XmlParser staxParser;
    private AnalysisResult analysisResult;

    @Before
    public void setUp() throws Exception {
        staxParser = new StaxParser();
    }

    @Test
    public void shouldReturnProperResultValuesForGivenXmlTestUrl() throws Exception {
//        Given
        AnalysisService service = new AnalysisService();
        String relativePathToTestFile = "src/test/resources/test-posts.xml";
        String absolutePathToTestFile = new File(relativePathToTestFile).getAbsolutePath();
        URL testFileUrl = new File(absolutePathToTestFile).toURI().toURL();
        service.setUrl(testFileUrl);

//        When
        staxParser.run(service);
        AnalysisResult result = service.getAnalysisResult();

//        Then
        LocalDateTime expectedFirstPost = LocalDateTime.parse("2016-01-12T18:45:19.963");
        LocalDateTime actualFirstPost = result.getDetails().getFirstPost();
        assertEquals(expectedFirstPost, actualFirstPost);

        LocalDateTime expectedLastPost = LocalDateTime.parse("2016-01-12T18:59:34.417");
        LocalDateTime actualLastPost = result.getDetails().getLastPost();
        assertEquals(expectedLastPost, actualLastPost);

        int expectedQuestions = 7;
        int actualQuestions = result.getDetails().getQuestions();
        assertEquals(expectedQuestions, actualQuestions);

        int expectedAnswers = 1;
        int actualAnswers = result.getDetails().getAnswers();
        assertEquals(expectedAnswers, actualAnswers);

        int expectedTotalAcceptedPosts = 5;
        int actualTotalAcceptedPosts = result.getDetails().getTotalAcceptedPosts();
        assertEquals(expectedTotalAcceptedPosts, actualTotalAcceptedPosts);

        int expectedAvgScore = 9;
        int actualAvgScore = result.getDetails().getAvgScore();
        assertEquals(expectedAvgScore, actualAvgScore);

        int expectedMinScore = 4;
        int actualMinScore = result.getDetails().getMinScore();
        assertEquals(expectedMinScore, actualMinScore);

        int expectedMaxScore = 18;
        int actualMaxScore = result.getDetails().getMaxScore();
        assertEquals(expectedMaxScore, actualMaxScore);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenUriIsNull() throws Exception {
//        Given
        AnalysisService service = new AnalysisService();
        service.setUrl(null);
//        When & Then
        staxParser.run(service);
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldThrowExceptionWhenWrongUri() throws Exception {
//        Given
        AnalysisService service = new AnalysisService();
        String relativePathToTestFile = "src/test/resources/notExisting.xml";
        String absolutePathToTestFile = new File(relativePathToTestFile).getAbsolutePath();
        URL testFileUrl = new File(absolutePathToTestFile).toURI().toURL();
        service.setUrl(testFileUrl);
//        When & Then
        staxParser.run(service);
    }

    @Test(expected = XMLStreamException.class)
    public void shouldThrowExceptionWhenXmlFileBroken() throws Exception {
//        Given
        AnalysisService service = new AnalysisService();
        String relativePathToTestFile = "src/test/resources/broken.xml";
        String absolutePathToTestFile = new File(relativePathToTestFile).getAbsolutePath();
        URL testFileUrl = new File(absolutePathToTestFile).toURI().toURL();
        service.setUrl(testFileUrl);
//        When & Then
        staxParser.run(service);
    }
}