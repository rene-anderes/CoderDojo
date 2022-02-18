package org.anderes.edu.dojo.csv;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Reader8Test {

    private CsvReader8 csvReader;   
    
    @Before
    public void setUp() throws Exception {
        final Path csvFile = Paths.get("target/test-classes", "test.csv");
        csvReader = new CsvReader8(csvFile);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void readHeader() throws IOException {
        final List<String> header = csvReader.readHeader();
        assertThat(header.size(), is(3));
        assertThat("Falsche Daten im Header", header, contains("Name", "Age", "City"));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void readRecords() throws IOException {
        final List<List<String>> records = csvReader.readRecords();
        assertThat(records.size(), is(2));
        assertThat(records, contains(Arrays.asList("Peter", "42", "New York"), Arrays.asList("Paul", "57", "London")));
    }
    
    @Test(expected = IOException.class)
    public void shouldBeWrongPath() throws IOException {
        csvReader = new CsvReader8(Paths.get("/notExists.csv"));
        csvReader.readHeader();
    }
}
