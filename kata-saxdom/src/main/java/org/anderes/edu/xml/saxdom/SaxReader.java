package org.anderes.edu.xml.saxdom;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.lang3.Validate;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SaxReader {
    private final static Logger logger = Logger.getLogger(SaxReader.class.getName());

    public static void parseFile(final String xmlFile, final String xsdFile, final ContentHandler contentHandler) throws Exception {
        Validate.notBlank(xmlFile);
        Validate.notBlank(xsdFile);
        Validate.notNull(contentHandler);
        
        validateFile(xmlFile, xsdFile);

        parseFile(xmlFile, contentHandler);
        
    }
        
    private static void parseFile(final String xmlFile, final ContentHandler contentHandler) throws Exception {
        final SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        final SAXParser parser = factory.newSAXParser();
        final XMLReader xmlReader = parser.getXMLReader();
        xmlReader.setContentHandler(contentHandler);
        xmlReader.setErrorHandler(new SaxErrorHandler());
        try (final InputStream is = SaxReader.class.getResourceAsStream(xmlFile)) {
            final InputSource inputSource = new InputSource(is);
            xmlReader.parse(inputSource);
        } 
    }
    
    private static void validateFile(final String xmlFile, final String xsdFile) throws Exception {
        final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try (final InputStream is = SaxReader.class.getResourceAsStream(xsdFile)) {
            final InputSource inputSource = new InputSource(is);
            final Schema schema = factory.newSchema(new SAXSource(inputSource));
            final Validator validator = schema.newValidator();
            final Source source = new StreamSource(SaxReader.class.getResourceAsStream(xmlFile));
            validator.validate(source);
            logger.info(xmlFile + " is valid.");
        } catch (SAXException | IOException e) {
            String log = String.format("XML-File '%s' is not valid, because %s",  xmlFile, e.getMessage());
            logger.warning(log);
            throw e;
        }
    }

}
