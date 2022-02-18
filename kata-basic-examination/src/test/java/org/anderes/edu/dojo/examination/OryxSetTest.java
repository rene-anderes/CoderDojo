package org.anderes.edu.dojo.examination;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.TreeSet;

import org.junit.Test;

public class OryxSetTest {
    
    @Test
    public void shouldBeTreeSet() {
        
        // given
        final TreeSet<Oryx> collection = new TreeSet<>();
        final Oryx oryx1 = new Oryx("Otavi");
        final Oryx oryx2 = new Oryx("Outjo");
        
        // when
        collection.add(oryx1);
        collection.add(oryx2);
        
        // then
        assertThat(collection.size(), is(2));
        assertThat(collection, contains(oryx2, oryx1));
        
        collection.forEach(o -> System.out.println(o.getDescription()));
        /*
         * Outjo
         * Otavi
         */
    }

    @Test
    public void shouldBeTreeSetModify() {
        
        // given
        final TreeSet<Oryx> collection = new TreeSet<>();
        final Oryx oryx1 = new Oryx("Otavi");
        oryx1.setAge(6);
        final Oryx oryx2 = new Oryx("Outjo");
        final Oryx oryx3 = new Oryx("Otavi");
        oryx3.setAge(5);
        
        // when
        assertThat(collection.add(oryx1), is(true));
        assertThat(collection.add(oryx2), is(true));
        assertThat(collection.add(oryx3), is(false));
        
        // then
        assertThat(collection.size(), is(2));
        assertThat(collection, contains(oryx2, oryx1));
        
        collection.forEach(o -> System.out.println(o.getDescription()));
        /*
         * Outjo
         * Otavi
         */
    }
}
