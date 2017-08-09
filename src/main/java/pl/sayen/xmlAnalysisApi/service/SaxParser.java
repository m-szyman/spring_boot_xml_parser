package pl.sayen.xmlAnalysisApi.service;

import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * @author Mariusz Szymanski
 */
@Component
public class SaxParser extends DefaultHandler implements XmlParser {

    private AnalysisService service;

    /**
     * This method is used to read all xml file and parse each startElement
     *
     * @param service service for xml parser to temporary store and process results in memory
     */
    @Override
    public void run(AnalysisService service) throws ParserConfigurationException, SAXException, IOException {
        this.service = service;
        ElementAnalyzer.alreadyExecuted = false;
        String uri = service.getUrl();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        saxParser.parse(uri, this);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        ElementAnalyzer.analyzeElement(service, attributes);
    }
}
