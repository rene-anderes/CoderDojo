package org.anderes.edu.dojo.java8.news.stars;

import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;

public class StarReader {

    private final Path csvFile;
    
    private StarReader(final Path csvFile) {
        super();
        this.csvFile = csvFile;
    }

    public static StarReader build(final Path csvFile) {
        return new StarReader(csvFile);
    }

    /**
     * Diese Methode muss implementiert werden
     */
    public Collection<Star> readStars() {
        
        return new HashSet<Star>(); // to be done ...
    }

}
