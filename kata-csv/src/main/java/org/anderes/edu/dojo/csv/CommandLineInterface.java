package org.anderes.edu.dojo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class CommandLineInterface {
    
    public enum Command { 
        NEXT("N"), PREV("P"), FIRST("F"), LAST("L"), EXIT("X");
        
        private String key;
        
        private Command(final String key) {
            this.key = key;
        }
        
        public static Optional<Command> eval(final String command) {
            return Stream.of(Command.values()).filter(k -> k.getKey().equalsIgnoreCase(command)).findAny();
        }

        private String getKey() {
            return key;
        }
    };
    
    public CommandLineInterface() {
        super();
    }
    
    public Command showAndWait(final OutputStream outputStream, final InputStream inputStream) {
        writeCommandsToOutputStream(outputStream);
        Optional<Command> command = Optional.empty();
        do {
            command = Command.eval(readFromInputStream(inputStream));
        } while (!command.isPresent());
        return command.get();
    }
    
    private void writeCommandsToOutputStream(final OutputStream outputStream) {
        final OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        try {
            writer.append("N(ext page, P(revious page, F(irst page, L(ast page, eX(it").append('\n').flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @SuppressWarnings("resource")
    private String readFromInputStream(final InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        return scanner.nextLine();
    }
}
