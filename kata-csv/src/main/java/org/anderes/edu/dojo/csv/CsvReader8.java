package org.anderes.edu.dojo.csv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Alternatives Beispiel für das Einlesen des CSV-Files.
 * Gelöst mittels in Java eingeführten Funktionalen Programmierung mit Lambdas.
 * 
 * @author René Anderes
 *
 */
public class CsvReader8 {

    private final Path csvFile;
    private final Charset encoding = Charset.forName("UTF-8");
    private final String seperator = ";";

    public CsvReader8(final Path csvFile) {
        this.csvFile = csvFile;
    }
    
    public List<String> readHeader() throws IOException {
        try (Stream<String> lines = Files.lines(csvFile, encoding)) {
            return lines.findFirst()
                    .map(line -> Arrays.asList(line.split(seperator)))
                    .get();
        } 
    }

    public List<List<String>> readRecords() throws IOException {
        try (Stream<String> lines = Files.lines(csvFile, encoding)) {
            return lines.skip(1)
                    .map(line -> Arrays.asList(line.split(seperator)))
                    .collect(Collectors.toList());
        } 
    }

}
