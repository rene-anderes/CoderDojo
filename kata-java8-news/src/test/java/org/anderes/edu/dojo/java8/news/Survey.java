package org.anderes.edu.dojo.java8.news;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Prüfungsaufgaben
 */
public class Survey {

    private Stream<String> stringStream = Stream.of("Erde", "Mond", "Merkur", "Venus", "Sonne", "Mars", "Jupiter", "Saturn");

    @Test
    public void mission_one() {
        List<String> list = stringStream
                        .filter(element -> element.length() > 4)
                        .sorted()
                        .collect(Collectors.toList());
        assertThat(Arrays.asList("Jupiter", "Merkur", "Saturn", "Sonne", "Venus"), is(list));
    }

    @Test
    public void mission_two() {
        List<String> list = stringStream
                        .filter(element -> element.matches("[a-zA-Z&&[^u]]+"))
//                        .filter(element -> element.indexOf("u") == -1)
                        .sorted()
                        .collect(Collectors.toList());
        assertThat(Arrays.asList("Erde", "Mars", "Mond", "Sonne"), is(list));
    }
}
