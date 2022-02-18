package org.anderes.edu.xml.validate;

import java.util.logging.Logger;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SaxErrorHandler implements ErrorHandler {

    private final Logger logger = Logger.getLogger(ValidateXmlFiles.class.getName());
    private String xmlFile;
    private int failure = 0;
    
    public SaxErrorHandler(String xmlFile) {
        super();
        this.xmlFile = xmlFile;
    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        String log = String.format("XML-File '%s' is not valid, because %s",  xmlFile, exception.getMessage());
        logger.warning(log);
        failure++;
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        String log = String.format("XML-File '%s' is not valid, because %s",  xmlFile, exception.getMessage());
        logger.warning(log);
        failure++;
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        String log = String.format("XML-File '%s' is not valid, because %s",  xmlFile, exception.getMessage());
        logger.warning(log);
        failure++;
    }

    public boolean hasError() {
        return failure > 0;
    }

}
