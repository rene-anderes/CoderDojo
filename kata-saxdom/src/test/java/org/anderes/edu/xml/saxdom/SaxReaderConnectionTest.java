package org.anderes.edu.xml.saxdom;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.anderes.edu.xml.saxdom.exercise.connection.Connection;
import org.anderes.edu.xml.saxdom.exercise.connection.SaxConnectionHandler;
import org.junit.Test;
import org.xml.sax.SAXParseException;

public class SaxReaderConnectionTest {

    private final String xsdPath = "/org/anderes/edu/xml/saxdom/connection/Verbindung.xsd";
    private final LocalDate expectedDate = LocalDate.of(2014, Month.DECEMBER, 1);
    private final LocalTime expectedFromTime = LocalTime.of(12, 11, 0);
    private final LocalTime expectedToTime = LocalTime.of(13, 23, 0);
    
    @Test
    public void shouldBeReadTheXMLFile() throws Exception {
 
        SaxConnectionHandler contentHandler = new SaxConnectionHandler();
        SaxReader.parseFile("/org/anderes/edu/xml/saxdom/connection/Verbindung.xml", xsdPath, contentHandler);
        
        final Connection connection = contentHandler.getConnection();
        
        assertThat(connection, is(notNullValue()));
        assertThat(connection.getDate(), is(expectedDate));
        assertThat(connection.getFrom().getStation(), is("St.Gallen"));
        assertThat(connection.getFrom().getTime(), is(expectedFromTime));
        assertThat(connection.getFrom().getPlatform(), is(1));
        assertThat(connection.getTo().getStation(), is("Zürich"));
        assertThat(connection.getTo().getTime(), is(expectedToTime));
        assertThat(connection.getTo().getPlatform(), is(16));
        assertThat(connection.getTravelWith(), is("ICN"));
        assertThat(connection.getAllocation().getFirstClass(), is(1));
        assertThat(connection.getAllocation().getSecondClass(), is(2));
        assertThat(connection.getComment().isPresent(), is(true));
        assertThat(connection.getComment().get(), is("InterCity 724 Richtung: Genève"));
    }
    
    @Test
    public void shouldBeReadTheXMLFileII() throws Exception {

        SaxConnectionHandler contentHandler = new SaxConnectionHandler();
        SaxReader.parseFile("/org/anderes/edu/xml/saxdom/connection/Verbindung_II.xml", xsdPath, contentHandler);
        
        final Connection connection = contentHandler.getConnection();
        
        assertThat(connection, is(notNullValue()));
        assertThat(connection.getDate(), is(expectedDate));
        assertThat(connection.getFrom().getStation(), is("St.Gallen"));
        assertThat(connection.getFrom().getTime(), is(expectedFromTime));
        assertThat(connection.getFrom().getPlatform(), is(1));
        assertThat(connection.getTo().getStation(), is("Zürich"));
        assertThat(connection.getTo().getTime(), is(expectedToTime));
        assertThat(connection.getTo().getPlatform(), is(16));
        assertThat(connection.getTravelWith(), is("ICN"));
        assertThat(connection.getAllocation().getFirstClass(), is(1));
        assertThat(connection.getAllocation().getSecondClass(), is(2));
        assertThat(connection.getComment().isPresent(), is(false));
    }
    
    @Test(expected = SAXParseException.class)
    public void shouldBeAException() throws Exception {

        SaxConnectionHandler contentHandler = new SaxConnectionHandler();
        SaxReader.parseFile("/org/anderes/edu/xml/saxdom/connection/Verbindung_NotValid.xml", xsdPath, contentHandler);
    }
}
