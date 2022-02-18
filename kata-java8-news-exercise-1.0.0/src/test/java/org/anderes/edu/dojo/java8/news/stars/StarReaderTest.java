package org.anderes.edu.dojo.java8.news.stars;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;

public class StarReaderTest {
    
    final Path csvFile = Paths.get("target/classes", "Sternen.csv");
    
    @Ignore("Aufgabe: Dieser Test soll grün werden")
    @Test 
    public void shouldBeReadTheCsvFile() {
        
        // when
        Collection<Star> starCollection = StarReader.build(csvFile).readStars();
        
        // then
        assertThat(starCollection, is(notNullValue()));
        assertThat(starCollection, hasSize(65));
        assertThat(starCollection, hasItem(Star.create("61 Cygni", "61 Cygni A (BD+38°4343)", "11.4000000000")));
    }

}
