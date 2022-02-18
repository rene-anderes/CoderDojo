package org.anderes.edu.dojo.examination;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.anderes.edu.dojo.examination.Klipspringer.Sex;
import org.junit.Before;
import org.junit.Test;

public class KlipspringerCollectionTest {
    
    private Collection<Klipspringer> collection = new ArrayList<>();
    
    @Before
    public void setup() {
        final Klipspringer animal = new Klipspringer("Aldebaran");
        animal.setSex(Sex.FEMALE);
        collection.add(animal);
    }
    
    @Test
    public void shouldBeContains() {
        
        // given
        final Klipspringer animal = new Klipspringer("Aldebaran");
        animal.setSex(Sex.FEMALE);
        
        // when
        boolean isExists = collection.contains(animal);
        
        // then
        assertThat(isExists, is(true));
        
    }

}
