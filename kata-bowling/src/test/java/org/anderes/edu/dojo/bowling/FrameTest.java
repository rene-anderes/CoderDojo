package org.anderes.edu.dojo.bowling;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FrameTest {
    
   
    @Test
    public void shouldBeOneRoll() {
        // given
        final Frame frame = new Frame(false);
        
        // when
        frame.addRoll(3);
        
        // then
        assertThat(frame.getPinsRolled().length, is(1));
        assertThat(frame.getScore(), is(3));
        assertThat(frame.getPinsSum(), is(3));
        assertThat(frame.isComplete(), is(false));
        assertThat(frame.isStrike(), is(false));
        assertThat(frame.isSpare(), is(false));
        assertThat(frame.toString(), is("([3],3)"));
    }
    
    @Test
    public void shouldBeTwoRoll() {
        // given
        final Frame frame = new Frame(false);
        
        // when
        frame.addRoll(1);
        frame.addRoll(4);
        
        // then
        assertThat(frame.getPinsRolled().length, is(2));
        assertThat(frame.getScore(), is(5));
        assertThat(frame.getPinsSum(), is(5));
        assertThat(frame.isComplete(), is(true));
        assertThat(frame.isStrike(), is(false));
        assertThat(frame.isSpare(), is(false));
        assertThat(frame.toString(), is("([1,4],5)"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldBeException() {
        // given
        final Frame frame = new Frame(false);
        
        // when
        frame.addRoll(1);
        frame.addRoll(4);
        frame.addRoll(4);
    }
    
    @Test(expected = IllegalStateException.class)
    public void shouldBeNoSpikeException() {
        // given
        final Frame frame = new Frame(false);
        
        // when
        frame.addRoll(1);
        frame.addRoll(4);
        frame.addSpareSpikePoints(5);
    }
    
    @Test
    public void shouldBeStrike() {
        // given
        final Frame frame = new Frame(false);
        
        // when
        frame.addRoll(10);
        
        // then
        assertThat(frame.getPinsRolled().length, is(1));
        assertThat(frame.getScore(), is(10));
        assertThat(frame.getPinsSum(), is(10));
        assertThat(frame.isComplete(), is(true));
        assertThat(frame.isStrike(), is(true));
        assertThat(frame.isSpare(), is(false));
        assertThat(frame.toString(), is("([10],10)"));
    }
    
    @Test
    public void shouldBeSpare() {
        // given
        final Frame frame = new Frame(false);
        
        // when
        frame.addRoll(6);
        frame.addRoll(4);
        
        // then
        assertThat(frame.getPinsRolled().length, is(2));
        assertThat(frame.getScore(), is(10));
        assertThat(frame.getPinsSum(), is(10));
        assertThat(frame.isStrike(), is(false));
        assertThat(frame.isSpare(), is(true));
        assertThat(frame.isComplete(), is(true));
        assertThat(frame.toString(), is("([6,4],10)"));
    }
    
    @Test
    public void shouldBeStrikeWithStrikePoints() {
        // given
        final Frame frame = new Frame(false);
        
        // when
        frame.addRoll(10);
        frame.addSpareSpikePoints(5);
        
        // then
        assertThat(frame.getPinsRolled().length, is(1));
        assertThat(frame.getScore(), is(15));
        assertThat(frame.getPinsSum(), is(10));
        assertThat(frame.isComplete(), is(true));
        assertThat(frame.isStrike(), is(true));
        assertThat(frame.isSpare(), is(false));
        assertThat(frame.toString(), is("([10],15)"));
    }
    
    @Test
    public void shouldBeLastFrameInGame() {
        // given
        final Frame frame = new Frame(true);
        
        // when
        frame.addRoll(2);
        frame.addRoll(8);
        frame.addRoll(6);
        
        // then
        assertThat(frame.getPinsRolled().length, is(2));
        assertThat(frame.getScore(), is(16));
        assertThat(frame.getPinsSum(), is(10));
        assertThat(frame.isComplete(), is(true));
        assertThat(frame.isStrike(), is(false));
        assertThat(frame.isSpare(), is(true));
        assertThat(frame.toString(), is("([2,8,6],16)"));
    }
}
