package pl.sayen.xmlAnalysisApi.service;

import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mariusz Szymanski
 */
@Component
public class SaxParser extends DefaultHandler implements XmlParser {

    private AnalysisService service;

    @Override
    public void run(AnalysisService service) throws ParserConfigurationException, SAXException, IOException {
        this.service = service;

        elementAnalyzer.alreadyExecuted = false;

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        String uri = service.getUrl().toString();
        saxParser.parse(uri, this);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        int length = attributes.getLength();

        Map<String, String> attributesMap = new LinkedHashMap<>();
        for (int i = 0; i < length; i++) {
            attributesMap.put(attributes.getQName(i), attributes.getValue(i));
        }
        elementAnalyzer.analyzeElement(service, attributesMap);
    }
}
