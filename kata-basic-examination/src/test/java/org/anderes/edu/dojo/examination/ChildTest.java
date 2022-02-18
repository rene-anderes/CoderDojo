package org.anderes.edu.dojo.examination;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class ChildTest {

    @Test
    public void shouldBeCorrectTreeSet() {
        
        // given
        Set<Child> sortedList = new TreeSet<>();
        
        // when
        sortedList.add(new Child(9, "Mélina"));
        sortedList.add(new Child(3, "Sonia"));
        sortedList.add(new Child(9, "Jaquelina"));
        sortedList.add(new Child(11, "Alais"));
        
        // then
        assertThat(sortedList.size(), is(3));
        sortedList.forEach(c -> System.out.println(c.getName()));
        /*
         * Sonia
         * Mélina
         * Alais
         */
    }
    
}
