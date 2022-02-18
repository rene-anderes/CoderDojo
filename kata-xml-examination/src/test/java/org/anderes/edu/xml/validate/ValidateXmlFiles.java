package org.anderes.edu.xml.validate;

import static org.anderes.edu.xml.validate.ValidateXmlFiles.ValidateType.DTD;
import static org.anderes.edu.xml.validate.ValidateXmlFiles.ValidateType.XSD;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import static java.lang.Boolean.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@RunWith(value = Parameterized.class)
public class ValidateXmlFiles {

    private final static Logger logger = Logger.getLogger(ValidateXmlFiles.class.getName());
    
    private String xmlFile;
    private String xsdOrdtd;
    private ValidateType validateType;
    private Boolean expectedResult;

    @Test
    public void checkFile() {
        switch (validateType) {
        case DTD:
            assertThat(validateFileDtd(xmlFile, xsdOrdtd), is(expectedResult));
            break;
        case XSD:
            assertThat(validateFileXsd(xmlFile, xsdOrdtd), is(expectedResult));
            break;
        default:
            break;
        }
    }
    
    private static boolean validateFileXsd(final String xmlFile, final String xsd) {
        final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try (final InputStream is = ValidateXmlFiles.class.getResourceAsStream(xsd)) {
            final InputSource inputSource = new InputSource(is);
            final Schema schema = factory.newSchema(new SAXSource(inputSource));
            final Validator validator = schema.newValidator();
            final Source source = new StreamSource(ValidateXmlFiles.class.getResourceAsStream(xmlFile));
            validator.validate(source);
            logger.info("-----------------------------> " + xmlFile + " is valid.");
            return true;
        } catch (SAXException | IOException e) {
            String log = String.format("XML-File '%s' is not valid, because %s",  xmlFile, e.getMessage());
            logger.warning(log);
            return false;
        }
    }
    
    public static boolean validateFileDtd(final String xmlFile, final String dtd) {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setValidating(true);
        DocumentBuilder builder;
        try {
            builder = domFactory.newDocumentBuilder();
            final SaxErrorHandler errorHandler = new SaxErrorHandler(xmlFile);
            builder.setErrorHandler(errorHandler);
            builder.setEntityResolver(new DtdEntityResolver(dtd));
            builder.parse("target/classes" + xmlFile);
            if (!errorHandler.hasError()) {
                logger.info("-----------------------------> " + xmlFile + " is valid.");
            }
            return !errorHandler.hasError();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            String log = String.format("XML-File '%s' is not valid, because %s",  xmlFile, e.getMessage());
            logger.warning(log);
            return false;
        }
        
    }
    
    public ValidateXmlFiles(final String xmlFile, final String xsdOrdtd, final ValidateType type, final Boolean result) {
        this.xmlFile = xmlFile;
        this.xsdOrdtd = xsdOrdtd;
        this.validateType = type;
        this.expectedResult = result;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Parameter.getParameter();
    }

    public enum ValidateType { XSD, DTD, NONE }
    
    
    private enum Parameter {
        A_NOTE("/note_dtd/A_note.xml", "/note_dtd/note.dtd", DTD, TRUE),
        B_NOTE("/note_dtd/B_note.xml", "/note_dtd/note.dtd", DTD, TRUE),
        C_NOTE("/note_dtd/C_note.xml", "/note_dtd/note.dtd", DTD, TRUE),
        D_NOTE("/note_dtd/D_note.xml", "/note_dtd/note.dtd", DTD, FALSE),
        A_NOTE2("/note_dtd/A_note2.xml", "/note_dtd/note2.dtd", DTD, TRUE),
        B_NOTE2("/note_dtd/B_note2.xml", "/note_dtd/note2.dtd", DTD, TRUE),
        C_NOTE2("/note_dtd/C_note2.xml", "/note_dtd/note2.dtd", DTD, FALSE),
        D_NOTE2("/note_dtd/D_note2.xml", "/note_dtd/note2.dtd", DTD, TRUE),
        A_BOOK("/book_schema/A_Book.xml", "/book_schema/book.xsd", XSD, TRUE),
        B_BOOK("/book_schema/B_Book.xml", "/book_schema/book.xsd", XSD, TRUE),
        C_BOOK("/book_schema/C_Book.xml", "/book_schema/book.xsd", XSD, FALSE),
        D_BOOK("/book_schema/D_Book.xml", "/book_schema/book.xsd", XSD, TRUE);
    
        private String xmlFile;
        private String xsdOrdtd;
        private ValidateType type;
        private Boolean result;
        
        private Parameter(String xmlFile, String xsdOrdtd, ValidateType type, Boolean result) {
            this.xmlFile = xmlFile;
            this.xsdOrdtd = xsdOrdtd;
            this.type = type;
            this.result = result;
        }
        
        public static Collection<Object[]> getParameter() {
            Collection<Object[]> collection = new ArrayList<Object[]>(); 
            for (Parameter parameter : Parameter.values()) {
                collection.add(new Object[] { parameter.xmlFile, parameter.xsdOrdtd, parameter.type, parameter.result });
            }
            return collection;
        }
    }
}
