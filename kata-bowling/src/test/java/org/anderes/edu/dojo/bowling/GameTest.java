package org.anderes.edu.dojo.bowling;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayDeque;
import java.util.Collection;

import org.apache.commons.lang3.tuple.Triple;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

    private Game game;
    
    @Before
    public void setup() {
        game = new Game();
    }

    @Test
    public void shouldBeRealLiveExample() {
        int counter = 1;
        for (Triple<Integer, Integer, String> expected : getGameExample()) {
            game.addRoll(expected.getLeft());
            assertThat("Wurf " + counter + " falsch", game.getTotalScore(), is(expected.getMiddle()));
            assertThat(game.toString(), is(expected.getRight()));
            System.out.println("Wurf: " + counter + " | " + game.toString());
            counter++;
        }
        assertThat(game.isOver(), is(true));
    }
    
    private Collection<Triple<Integer, Integer, String>> getGameExample() {
        final ArrayDeque<Triple<Integer, Integer, String>> gameExample = new ArrayDeque<>(19);
        gameExample.add(Triple.of(1, 1, "([1],1)"));
        gameExample.add(Triple.of(4, 5, "([1,4],5)"));
        gameExample.add(Triple.of(4, 9, "([1,4],5), ([4],4)"));
        gameExample.add(Triple.of(5, 14, "([1,4],5), ([4,5],9)"));
        gameExample.add(Triple.of(6, 20, "([1,4],5), ([4,5],9), ([6],6)"));
        gameExample.add(Triple.of(4, 24, "([1,4],5), ([4,5],9), ([6,4],10)"));
        gameExample.add(Triple.of(5, 34, "([1,4],5), ([4,5],9), ([6,4],15), ([5],5)"));
        gameExample.add(Triple.of(5, 39, "([1,4],5), ([4,5],9), ([6,4],15), ([5,5],10)"));
        gameExample.add(Triple.of(10, 59, "([1,4],5), ([4,5],9), ([6,4],15), ([5,5],20), ([10],10)"));
        gameExample.add(Triple.of(0, 59, "([1,4],5), ([4,5],9), ([6,4],15), ([5,5],20), ([10],10), ([0],0)"));
        gameExample.add(Triple.of(1, 61, "([1,4],5), ([4,5],9), ([6,4],15), ([5,5],20), ([10],11), ([0,1],1)"));
        gameExample.add(Triple.of(7, 68, "([1,4],5), ([4,5],9), ([6,4],15), ([5,5],20), ([10],11), ([0,1],1), ([7],7)"));
        gameExample.add(Triple.of(3, 71, "([1,4],5), ([4,5],9), ([6,4],15), ([5,5],20), ([10],11), ([0,1],1), ([7,3],10)"));
        gameExample.add(Triple.of(6, 83, "([1,4],5), ([4,5],9), ([6,4],15), ([5,5],20), ([10],11), ([0,1],1), ([7,3],16), ([6],6)"));
        gameExample.add(Triple.of(4, 87, "([1,4],5), ([4,5],9), ([6,4],15), ([5,5],20), ([10],11), ([0,1],1), ([7,3],16), ([6,4],10)"));
        gameExample.add(Triple.of(10, 107, "([1,4],5), ([4,5],9), ([6,4],15), ([5,5],20), ([10],11), ([0,1],1), ([7,3],16), ([6,4],20), ([10],10)"));
        gameExample.add(Triple.of(2, 111, "([1,4],5), ([4,5],9), ([6,4],15), ([5,5],20), ([10],11), ([0,1],1), ([7,3],16), ([6,4],20), ([10],12), ([2],2)"));
        gameExample.add(Triple.of(8, 127, "([1,4],5), ([4,5],9), ([6,4],15), ([5,5],20), ([10],11), ([0,1],1), ([7,3],16), ([6,4],20), ([10],20), ([2,8],10)"));
        gameExample.add(Triple.of(6, 133, "([1,4],5), ([4,5],9), ([6,4],15), ([5,5],20), ([10],11), ([0,1],1), ([7,3],16), ([6,4],20), ([10],20), ([2,8,6],16)"));
        return gameExample;
    }

    @Test
    public void oneRoll() {
        game.addRoll(1);

        assertThat(game.getTotalScore(), is(1));
        assertThat(game.getFrames(), is(notNullValue()));
        assertThat(game.getFrames().size(), is(1));
        assertThat(game.getFrames().get(1), is(notNullValue()));
        assertThat(game.getFrames().get(1).getScore(), is(1));
        assertThat(game.getFrames().get(1).getPinsRolled().length, is(1));
        assertThat(game.getFrames().get(1).getPinsRolled()[0], is(1));
    }
    
    @Test
    public void twoRolls() {
        game.addRoll(1);
        game.addRoll(4);
        
        assertThat(game.getTotalScore(), is(5));
        assertThat(game.getFrames().size(), is(1));
        assertThat(game.getFrames().get(1).getPinsRolled().length, is(2));
        assertThat(game.getFrames().get(1).getPinsRolled()[0], is(1));
        assertThat(game.getFrames().get(1).getPinsRolled()[1], is(4));
    }
    
    @Test
    public void shouldBeSpare() {
        game.addRoll(6);
        game.addRoll(4);
        game.addRoll(5);
        
        assertThat(game.getTotalScore(), is(20));
        assertThat(game.getFrames().get(1).getScore(), is(15));
        assertThat(game.getFrames().get(1).getPinsRolled()[0], is(6));
        assertThat(game.getFrames().get(1).getPinsRolled()[1], is(4));
    }
    
    @Test
    public void shouldBeSpareTwo() {
        game.addRoll(6);
        game.addRoll(4);
        game.addRoll(5);
        game.addRoll(5);
        
        assertThat(game.getTotalScore(), is(25));
        assertThat(game.getFrames().get(1).getScore(), is(15));
        assertThat(game.getFrames().get(1).getPinsRolled()[0], is(6));
        assertThat(game.getFrames().get(1).getPinsRolled()[1], is(4));
        assertThat(game.getFrames().get(2).getScore(), is(10));
        assertThat(game.getFrames().get(2).getPinsRolled()[0], is(5));
        assertThat(game.getFrames().get(2).getPinsRolled()[1], is(5));
    }
    
    @Test
    public void shouldBeCorrectToString() {
        String expectedToString = "([6,4],15), ([5,5],10)";
        game.addRoll(6);
        game.addRoll(4);
        game.addRoll(5);
        game.addRoll(5);
        
        assertThat(game.toString(), is(expectedToString));
    }
    
    @Test
    public void shouldBeSpike() {
        game.addRoll(10);
        game.addRoll(1);
        
        assertThat(game.getTotalScore(), is(12));
        assertThat(game.getFrames().get(1).getScore(), is(11));
        assertThat(game.getFrames().get(1).getPinsRolled()[0], is(10));
    }
    
    @Test
    public void shouldBeSpikeTwo() {
        game.addRoll(10);
        game.addRoll(1);
        game.addRoll(6);
        
        assertThat(game.getTotalScore(), is(24));
        assertThat(game.getFrames().get(1).getScore(), is(17));
        assertThat(game.getFrames().get(2).getScore(), is(7));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldBeWrongRollValue() {
        game.addRoll(-2);
    }
    
    @Test(expected = IllegalStateException.class)
    public void bowlingGameTest() {
        final Game game = new Game();
        for (int i=0; i < 20; i++) {
            game.addRoll(0);
        }
        assertThat(game.getTotalScore(), is(0));
        assertThat(game.isOver(), is(true));
        game.addRoll(1);
    }
}
