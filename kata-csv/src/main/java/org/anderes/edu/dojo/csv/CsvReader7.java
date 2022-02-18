package org.anderes.edu.dojo.csv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CSV-Reader mittels den Boardmitteln von Java ab Version 1.7.
 * 
 * @author René Anderes
 * @see CsvReader8
 */
public class CsvReader7 {

    private final Path csvFile;
    private final Charset encoding = Charset.forName("UTF-8");
    private final String seperator = ";";

    public CsvReader7(final Path csvFile) {
        this.csvFile = csvFile;
    }

    public List<String> readHeader() throws IOException {
        final List<String> header = new ArrayList<>();
        try(BufferedReader bufferReader = new BufferedReader(createReader())) {
            final String line = bufferReader.readLine();
            header.addAll(Arrays.asList(line.split(seperator)));
        } 
        return header;
    }

    public List<List<String>> readRecords() throws IOException {
        final List<List<String>> records = new ArrayList<List<String>>();
        try(BufferedReader bufferReader = new BufferedReader(createReader())) {
            String line = bufferReader.readLine();    // skip first line
            while((line = bufferReader.readLine()) != null){
                final List<String> record = new ArrayList<>();
                record.addAll(Arrays.asList(line.split(seperator)));
                records.add(record);
            }
        } 
        return records;
    }

    private InputStreamReader createReader() throws FileNotFoundException {
        return new InputStreamReader(new FileInputStream(csvFile.toFile()), encoding);
    }

}
