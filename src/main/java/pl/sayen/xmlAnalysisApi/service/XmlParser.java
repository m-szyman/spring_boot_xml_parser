package pl.sayen.xmlAnalysisApi.service;

/**
 * @author Mariusz Szymanski
 */
public interface XmlParser {

    ElementAnalyzer elementAnalyzer = new ElementAnalyzer();

    /**
     * This method is used to read all the xml file and parse each startElement
     *
     * @param service service for xml parser to temporary store and process results in memory
     */
    void run(AnalysisService service) throws Exception;
}
