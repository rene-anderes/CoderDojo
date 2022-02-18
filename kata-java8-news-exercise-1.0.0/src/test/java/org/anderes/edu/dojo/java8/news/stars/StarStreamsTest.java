package org.anderes.edu.dojo.java8.news.stars;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class StarStreamsTest {

    private Collection<Star> starCollection;
    
    @Before
    public void setup() {
        final Path csvFile = Paths.get("target", "classes", "Sternen.csv");
        starCollection = StarReader.build(csvFile).readStars();
    }
    
    /**
     * Liefert den nächstgelegenen Stern (von der Erde aus gesehen) züruck.
     */
    @Ignore("Aufgabe: Dieser Test soll grün werden")
    @Test
    public void shouldBeNearestStar() {
        
        // when
        Optional<Star> star = Optional.empty(); // to be done ...
        
        // then
        assertThat(star.isPresent(), is(true));
        assertThat(star.get().getStarname(), is("Sonne"));
    }
    
    /**
     * Liefert den nächstgelegenen Stern welcher nicht im Sonnensystem liegt züruck.
     */
    @Ignore("Aufgabe: Dieser Test soll grün werden")
    @Test
    public void shouldBeNearestStarNotInSunsystem() {
        
        // when
        Optional<Star> star = Optional.empty(); // to be done ...
        
        // then
        assertThat(star.isPresent(), is(true));
        assertThat(star.get().getStarname(), is("Proxima Centauri (V645 Centauri)"));
    }
    
    
    /**
     * Liefert den Stern zurück, der am weitesten von der Erde entfernt ist.
     */
    @Ignore("Aufgabe: Dieser Test soll grün werden")
    @Test
    public void shouldBeMostDistanceStar() {
        
        // when
        Optional<Star> star = Optional.empty(); // to be done ...
        
        // then
        assertThat(star.isPresent(), is(true));
        assertThat(star.get().getStarname(), is("LP 944-20"));
    }
    
    /**
     * Liefert eine sortierte Liste aller Entfernungen zurück. Den nächstegelegen Stern zuerst.
     */
    @Ignore("Aufgabe: Dieser Test soll grün werden")
    @Test
    public void shouldBeSortedDistanceList() {
        
        // when
        List<Double> distanceList = Arrays.asList(null); // to be done ...
        
        // then
        assertThat(distanceList, is(notNullValue()));
        assertThat(distanceList.size(), is(65));
        assertThat(distanceList.stream().findFirst().get(), is(1.6E-5));
        assertThat(distanceList.stream().skip(64).findFirst().get(), is(16.19));
    }
    
    /**
     * Liefert die durchschnittliche Entfernung aller Sterne ausserhalb des Sonnensystems
     */
    @Ignore("Aufgabe: Dieser Test soll grün werden")
    @Test
    public void shouldBeAverageStarDistance() {
    
        // when
        double average = 0.0D; // to be done ...
        
        // then
        assertThat(average, is(12.16934375));
    }
    
    /**
     * Liefert ein gruppierte Liste von Sternen zurück. Gruppiert nach Sternensystem.
     * </p>
     * Map: Key = Sternensystem / Value = Liste von Sternen die zu diesem System gehören
     */
    @Ignore("Aufgabe: Dieser Test soll grün werden")
    @Test
    public void shouldBeGroupBySystem() {
        
        // when
        Map<String, List<Star>> map = new HashMap<>(); // to be done ...
        
        // then
        assertThat(map, is(notNullValue()));
        assertThat(map.get("Kruger 60").size(), is(2));
        assertThat(map.get("Kruger 60"), hasItems(
                        Star.create("Kruger 60","Kruger 60 A (BD+56°2783)","13.1400000000"), 
                        Star.create("Kruger 60","Kruger 60 B (DO Cephei)","13.1400000000")));
    }
    
    /**
     * Liefert eine Liste aller Sterne zurück, die den Grossbuchstaben 'A' im Namen tragen, 
     * sortiert nach Entfernung aufsteigend und danach nach Namen (lexicographically), falls wenn zwei Sterne die gleiche Distanz haben.
     */
    @Ignore("Aufgabe: Dieser Test soll grün werden")
    @Test
    public void shouldBeFindStarsWithA() {
             
        // when
        LinkedList<Star> stars = new LinkedList<>(); // to be done ...
        
        // then
        assertThat(stars, is(notNullValue()));
        assertThat(stars.size(), is(17));
        assertThat(stars.peekFirst().getStarname(), is("Alpha Centauri A (Rigil Kentaurus, Toliman)"));
        assertThat(stars.peekLast().getStarname(), is("GJ 412 A"));
    }
    
    /**
     * Liefert einen String der alle Sternennamen beinhaltet (alphabetisch sortiert). Format: [Sonne;GJ 412 A;...]
     */
    @Ignore("Aufgabe: Dieser Test soll grün werden")
    @Test
    public void shouldBeBuildString() {
        
        // when
        String starNames = ""; // to be done ...
        
        // then
        assertThat(starNames, is(notNullValue()));
        assertThat(starNames, startsWith("[61 Cygni A (BD+38°4343);61 Cygni B (BD+38°4344);Alpha Centauri A (Rigil Kentaurus, Toliman);"));
        assertThat(starNames.length(), is(1373));
        
    }
}
