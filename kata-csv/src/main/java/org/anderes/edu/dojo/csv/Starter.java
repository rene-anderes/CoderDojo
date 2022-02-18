package org.anderes.edu.dojo.csv;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.anderes.edu.dojo.csv.CommandLineInterface.Command;

public class Starter {

    public static void main(String[] args) {
        final Path csvFile = Paths.get("target/classes", "persons.csv");
        final CsvReader7 csvReader = new CsvReader7(csvFile);
        List<String> header = null;
        Paging paging = null;
        try {
            header = csvReader.readHeader();
            paging = new Paging(csvReader.readRecords());
        } catch (IOException e) {
            System.exit(1);
        }
        final CommandLineInterface cli = new CommandLineInterface();
        Command command = Command.FIRST;
        do {
            final List<List<String>> records = getRecords(command, paging);
            final Viewer view = new Viewer(header, records);
            view.show(System.out);
            command = cli.showAndWait(System.out, System.in);
        } while(true);
    }

    /*package*/ static List<List<String>> getRecords(final Command command, final Paging paging) {
        List<List<String>> records = new ArrayList<List<String>>(0);
        switch (command) {
            case NEXT:
                records = paging.nextPage();
                break;
            case PREV:
                records = paging.previousPage();
                break;
            case FIRST:
                records = paging.firstPage();
                break;
            case LAST:
                records = paging.lastPage();
                break;
            case EXIT:
                System.exit(0);
                break;
            default:
                break;
        }
        return records;
    }
}
