package org.anderes.edu.dojo.java8.news;

import static org.hamcrest.Matchers.arrayContaining;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class ChallangeComparator {

    private final List<String> words = Arrays.asList("Jack", "Bill", "John", "Adam");
    
    @Test
    public void comparatorOldStyle() {
        words.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        
        final List<String> expecteds = Arrays.asList("Adam", "Bill", "Jack", "John");
        assertThat(words.toArray(), arrayContaining(expecteds.toArray()));
    }
    
    @Ignore("Aufgabe: Lösung mittels Lambda implementieren")
    @Test
    public void comparatorWithFunctionalInterface() {

        // to be done ...
        
        final List<String> expecteds = Arrays.asList("Adam", "Bill", "Jack", "John");
        assertThat(words.toArray(), arrayContaining(expecteds.toArray()));
    }
}
