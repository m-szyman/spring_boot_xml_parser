package pl.sayen.xmlAnalysisApi.service;

import org.springframework.stereotype.Component;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Mariusz Szymanski
 */
@Component
public class StaxParser implements XmlParser {

    @Override
    public void run(AnalysisService service) throws Exception {

        elementAnalyzer.alreadyExecuted = false;

        Map<String, String> attributes = new LinkedHashMap<>();

        InputStream inputStream = service.getUrl().openStream();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(inputStream);

        int eventType;

        while (reader.hasNext()) {
            eventType = reader.next();

            if (eventType == XMLEvent.START_ELEMENT) {
                String elementName = reader.getName().toString();

                if (Objects.equals(elementName, "row")) {
                    attributes.put("PostTypeId", reader.getAttributeValue("", "PostTypeId"));
                    attributes.put("AcceptedAnswerId", reader.getAttributeValue("", "AcceptedAnswerId"));
                    attributes.put("CreationDate", reader.getAttributeValue("", "CreationDate"));
                    attributes.put("Score", reader.getAttributeValue("", "Score"));

                    elementAnalyzer.analyzeElement(service, attributes);
                    attributes.clear();
                }
            }
        }
        inputStream.close();
    }
}
