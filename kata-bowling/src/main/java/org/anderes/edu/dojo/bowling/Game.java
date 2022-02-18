package org.anderes.edu.dojo.bowling;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {
    
    private Map<Integer, Frame> frames = new HashMap<Integer, Frame>();
    private Integer frameCounter = 1;
    
    public void addRoll(final int pins) {
        if (pins < 0) {
            throw new IllegalArgumentException("Ein Wurf kleiner 0 ist nicht möglich");
        }
        if (isOver()) {
            throw new IllegalStateException("Das Game ist fertig gespielt.");
        }
        frames.putIfAbsent(frameCounter, new Frame(frameCounter == 10));
        Frame frame = frames.compute(frameCounter, (Integer k, Frame f) -> { f.addRoll(pins); return f; });
        addSpikeOrSparePointsToLastFrame();
        if (frame.isComplete()) {
            frameCounter++;
        }
    }

    private void addSpikeOrSparePointsToLastFrame() {
        if (frameCounter < 2) {
            return;
        }
        final Integer lastFrameIndex = frameCounter - 1;
        final Frame lastFrame = frames.get(lastFrameIndex);
        final Frame activeFrame = frames.get(frameCounter);
        if (lastFrame.isSpare()) {
            lastFrame.addSpareSpikePoints(activeFrame.getPinsRolled()[0]);
        } else if (lastFrame.isStrike()) {
            lastFrame.addSpareSpikePoints(activeFrame.getPinsSum());
        }
    }

    public int getTotalScore() { 
        return frames.values().stream().mapToInt(f -> f.getScore()).sum();
    }

    public boolean isOver() { 
        return frameCounter > 10;
    }

    public Map<Integer, Frame> getFrames() { 
        return Collections.unmodifiableMap(frames);
    }

    @Override
    public String toString() {
        return frames.values().stream().map(frame -> frame.toString()).collect(Collectors.joining(", "));
    }
    
    
}
