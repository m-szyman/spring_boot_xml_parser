package pl.sayen.xmlAnalysisApi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sayen.xmlAnalysisApi.model.AnalysisResult;
import pl.sayen.xmlAnalysisApi.service.AnalysisService;
import pl.sayen.xmlAnalysisApi.service.XmlParser;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mariusz Szymanski
 */
@RestController
@RequestMapping("/analyze")
public class AnalysisController {

    private final XmlParser saxParser;
    private final XmlParser staxParser;
    private static final Logger LOG = LoggerFactory.getLogger(AnalysisController.class);

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

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private Map<String, String> handleException(Exception ex) {

        Map<String, String> error = new LinkedHashMap<>();
        error.put("error", ex.getClass().getSimpleName());
        error.put("message", ex.getMessage());
        LOG.warn(ex.toString());

        return error;
    }
}
