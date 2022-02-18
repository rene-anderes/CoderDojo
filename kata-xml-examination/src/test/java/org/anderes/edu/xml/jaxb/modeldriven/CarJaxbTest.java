package org.anderes.edu.xml.jaxb.modeldriven;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CarJaxbTest {

    private Path xmlFile;

    @Before
    public void setUp() throws Exception {
        final File tempFile  = File.createTempFile("customer", ".xml");
        xmlFile = Paths.get(tempFile.toURI());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void marshaller() throws JAXBException {
        final File file = xmlFile.toFile();
        final JAXBContext jaxbContext = JAXBContext.newInstance(Car.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(createSampleCar(), file);
        
        assertThat(file.exists(), is(true));
        assertThat(file.length() > 100L, is(true));
        
        dumpFile(xmlFile);
    }

    private void dumpFile(Path file) {
        try {
            Files.lines(file, UTF_8).forEach(System.out::println);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    private Car createSampleCar() {
        return new Car(4, "black", 988722345);
    }
}
