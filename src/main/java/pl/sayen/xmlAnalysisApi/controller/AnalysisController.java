package pl.sayen.xmlAnalysisApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    private final XmlParser xmlParser;

    @Autowired
    public AnalysisController(XmlParser xmlParser) {
        this.xmlParser = xmlParser;
    }

    @RequestMapping(method = RequestMethod.POST)
    public AnalysisResult doAnalysis(@RequestBody AnalysisService service) throws Exception {

        xmlParser.run(service);

        return service.getAnalysisResult();
    }
}
