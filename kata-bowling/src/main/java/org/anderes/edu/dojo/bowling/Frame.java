package org.anderes.edu.dojo.bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frame {
    
    private final static int NO_SPAR_OR_SPIKE_POINT = -1;
    private final static int SPAR_OR_SPIKE_POINT = 10;
    private final static int MAX_ROLL = 2;
    private List<Integer> pins = new ArrayList<>(2);
    private int spareOrStrikePoints = NO_SPAR_OR_SPIKE_POINT;
    private final boolean isLastFrameInGame;
    
    public Frame(boolean isLastFrameInGame) {
        this.isLastFrameInGame = isLastFrameInGame;
    }

    /**
     * Punktzahl nur dieses Frame
     */
    public int getScore() {
        return getPinsSum() + getSpareOrStrikePoints();
    }
    
    private int getSpareOrStrikePoints() {
        if (spareOrStrikePoints == NO_SPAR_OR_SPIKE_POINT) {
            return 0;
        }
        return spareOrStrikePoints;
    }

    public int getPinsSum() {
        return pins.stream().reduce(0, Integer::sum).intValue();
    }
    
    public int[] getPinsRolled() { 
        return pins.stream().mapToInt(Integer::intValue).toArray();
    }
    
    @Override
    public String toString() { 
        if (isLastFrameInGame && isComplete()) {
            return String.format("([%s,%s],%s)", pins.stream().map(v -> v.toString()).collect(Collectors.joining(",")), getSpareOrStrikePoints(), getScore());
        } 
        return String.format("([%s],%s)", pins.stream().map(v -> v.toString()).collect(Collectors.joining(",")), getScore());
    }
    
    public void addRoll(int pins) {
        if (this.pins.size() == 2) {
            if (isLastFrameInGame) {
                // im letzten Frame ist ein dritter Wurf erlaubt, dieser wird als Zusatzpunkte gespeichert
                spareOrStrikePoints = pins;
                return;
            }
            throw new IllegalArgumentException("Mehr als zwei Würfe pro Frame sind nicht erlaubt");
        }
        this.pins.add(pins);
    }
    
    public boolean isComplete() {
        if (isLastFrameInGame) {
            return (isSpare() && hasSparOrSpikePoint()) 
                            || (isStrike() && hasSparOrSpikePoint()) 
                            || ((!isSpare() && !isStrike()) && pins.size() == MAX_ROLL);
        }
        return isStrike() || pins.size() == MAX_ROLL;
    }
    
    private boolean hasSparOrSpikePoint() {
        return spareOrStrikePoints != NO_SPAR_OR_SPIKE_POINT;
    }

    public void addSpareSpikePoints(final int points) {
        if(getPinsSum() != SPAR_OR_SPIKE_POINT) {
            throw new IllegalStateException("Das Frame ist kein Spare oder Spike");
        }
        spareOrStrikePoints = points;
    }
    
    public boolean isStrike() {
        if (pins.size() == 1) {
            return pins.get(0) == SPAR_OR_SPIKE_POINT;
        }
        return false;
    }

    public boolean isSpare() {
        if (pins.size() == 2) {
            return getPinsSum() == SPAR_OR_SPIKE_POINT;
        }
        return false;
    }

}
