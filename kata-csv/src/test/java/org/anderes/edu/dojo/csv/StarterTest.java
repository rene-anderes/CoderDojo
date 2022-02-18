package org.anderes.edu.dojo.csv;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.anderes.edu.dojo.csv.CommandLineInterface.Command.*;
import org.junit.Before;
import org.junit.Test;

public class StarterTest {

    private List<List<String>> records;
    private Paging paging;

    @Before
    public void setUp() throws Exception {
        records = new ArrayList<List<String>>(7);
        records.add(Arrays.asList("Peter", "42", "New York"));
        records.add(Arrays.asList("Paul", "57", "London"));
        records.add(Arrays.asList("Mary", "35", "Munich"));
        records.add(Arrays.asList("Jaques", "66", "Paris"));
        records.add(Arrays.asList("Yuri", "23", "Moscow"));
        records.add(Arrays.asList("Stephanie", "47", "Stockholm"));
        records.add(Arrays.asList("Nadia", "29", "Madrid"));
        paging = new Paging(records);
        paging.firstPage();
    }

    
    @Test
    @SuppressWarnings("unchecked")
    public void shouldBeFirstPage() {
        List<List<String>> result = Starter.getRecords(FIRST, paging);
        assertThat(result.size(), is(3));
        assertThat(result, contains(
                        Arrays.asList("Peter", "42", "New York"), 
                        Arrays.asList("Paul", "57", "London"),
                        Arrays.asList("Mary", "35", "Munich")));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void shouldBeLastPage() {
        List<List<String>> result = Starter.getRecords(LAST, paging);
        assertThat(result.size(), is(3));
        assertThat(result, contains(
                        Arrays.asList("Yuri", "23", "Moscow"), 
                        Arrays.asList("Stephanie", "47", "Stockholm"),
                        Arrays.asList("Nadia", "29", "Madrid")));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void shouldBeNextPage() {
        List<List<String>> result = Starter.getRecords(NEXT, paging);
        assertThat(result.size(), is(3));
        assertThat(result, contains(
                        Arrays.asList("Jaques", "66", "Paris"), 
                        Arrays.asList("Yuri", "23", "Moscow"),
                        Arrays.asList("Stephanie", "47", "Stockholm")));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void shouldBeNextNextPage() {
        paging.nextPage();
        List<List<String>> result = Starter.getRecords(NEXT, paging);
        assertThat(result.size(), is(3));
        assertThat(result, contains(
                        Arrays.asList("Yuri", "23", "Moscow"), 
                        Arrays.asList("Stephanie", "47", "Stockholm"),
                        Arrays.asList("Nadia", "29", "Madrid")));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void shouldBePreviousPage() {
        paging.lastPage();
        List<List<String>> result = Starter.getRecords(PREV, paging);
        assertThat(result.size(), is(3));
        assertThat(result, contains(
                        Arrays.asList("Paul", "57", "London"),
                        Arrays.asList("Mary", "35", "Munich"),
                        Arrays.asList("Jaques", "66", "Paris")));
    }
}
