package org.anderes.edu.xml.validate;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DtdEntityResolver implements EntityResolver {

    private String dtd;
    
    public DtdEntityResolver(String dtd) {
        super();
        this.dtd = dtd;
    }
    
    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        if (systemId.endsWith("something.dtd")) {
            final InputStream is = ValidateXmlFiles.class.getResourceAsStream(dtd);
            return new InputSource(is);
        }
        return null;
    }

}
