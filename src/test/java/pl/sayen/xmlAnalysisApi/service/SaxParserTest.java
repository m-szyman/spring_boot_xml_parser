package pl.sayen.xmlAnalysisApi.service;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXParseException;
import pl.sayen.xmlAnalysisApi.model.AnalysisResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * @author Mariusz Szymanski
 */
public class SaxParserTest {

    private XmlParser saxParser;

    @Before
    public void setUp() throws Exception {
        saxParser = new SaxParser();
    }

    @Test
    public void shouldReturnCorrectResultValuesForGivenXmlTestUrl() throws Exception {
//        Given
        AnalysisService service = new AnalysisService();
        String relativePathToTestFile = "src/test/resources/test-posts.xml";
        String absolutePathToTestFile = new File(relativePathToTestFile).getAbsolutePath();
        URL testFileUrl = new File(absolutePathToTestFile).toURI().toURL();
        service.setUrl(testFileUrl);
        LocalDateTime expectedFirstPost = LocalDateTime.parse("2016-01-12T18:45:19.963");
        LocalDateTime expectedLastPost = LocalDateTime.parse("2016-01-12T18:59:34.417");
        int expectedQuestions = 7;
        int expectedAnswers = 1;
        int expectedTotalPosts = 8;
        int expectedTotalAcceptedPosts = 5;
//        When
        saxParser.run(service);
        AnalysisResult result = service.getAnalysisResult();
        LocalDateTime actualFirstPost = result.getDetails().getFirstPost();
        LocalDateTime actualLastPost = result.getDetails().getLastPost();
        int actualQuestions = result.getDetails().getQuestions();
        int actualAnswers = result.getDetails().getAnswers();
        int actualTotalPosts = result.getDetails().getTotalPosts();
        int actualTotalAcceptedPosts = result.getDetails().getTotalAcceptedPosts();
//        Then
        assertEquals(expectedFirstPost, actualFirstPost);
        assertEquals(expectedLastPost, actualLastPost);
        assertEquals(expectedQuestions, actualQuestions);
        assertEquals(expectedAnswers, actualAnswers);
        assertEquals(expectedTotalPosts, actualTotalPosts);
        assertEquals(expectedTotalAcceptedPosts, actualTotalAcceptedPosts);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenUriIsNull() throws Exception {
//        Given
        AnalysisService service = new AnalysisService();
        service.setUrl(null);
//        When & Then
        saxParser.run(service);
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
        saxParser.run(service);
    }

    @Test(expected = SAXParseException.class)
    public void shouldThrowExceptionWhenXmlFileBroken() throws Exception {
//        Given
        AnalysisService service = new AnalysisService();
        String relativePathToTestFile = "src/test/resources/broken.xml";
        String absolutePathToTestFile = new File(relativePathToTestFile).getAbsolutePath();
        URL testFileUrl = new File(absolutePathToTestFile).toURI().toURL();
        service.setUrl(testFileUrl);
//        When & Then
        saxParser.run(service);
    }
}