package pl.sayen.xmlAnalysisApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.sayen.xmlAnalysisApi.model.AnalysisResult;
import pl.sayen.xmlAnalysisApi.service.AnalysisService;
import pl.sayen.xmlAnalysisApi.service.XmlParser;

/**
 * @author Mariusz Szymanski
 */
@RestController
@RequestMapping("/analyze")
public class AnalysisController {

    private final XmlParser saxParser;
    private final XmlParser staxParser;

    @Autowired
    public AnalysisController(@Qualifier("saxParser") XmlParser saxParser, @Qualifier("staxParser") XmlParser staxParser) {
        this.saxParser = saxParser;
        this.staxParser = staxParser;
    }

    @RequestMapping(path = {"", "/", "StAX"}, method = RequestMethod.POST)
    public AnalysisResult doAnalysisWithStAX(@RequestBody AnalysisService service) throws Exception {

        staxParser.run(service);

        return service.getAnalysisResult();
    }

    @RequestMapping(path = "/SAX", method = RequestMethod.POST)
    public AnalysisResult doAnalysisWithSAX(@RequestBody AnalysisService service) throws Exception {

        saxParser.run(service);

        return service.getAnalysisResult();
    }
}
