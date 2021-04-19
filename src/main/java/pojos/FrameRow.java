package pojos;

import interfaces.Frame;
import java.util.Arrays;
import java.util.List;

public class FrameRow {
    private final List<Frame> frames;
    private int activeFrameIndex;
    
    public FrameRow() {
        frames = Arrays.asList(new RegularFrame());
        activeFrameIndex = 0;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int getActiveFrameIndex() {
        return activeFrameIndex;
    }

    public void incrementActiveIndex() {
        activeFrameIndex++;
    }

    public Frame getCurrentFrame() {
        return frames.get(activeFrameIndex);
    }

    public void addFrame(Frame frame) {
        frames.add(frame);
    }
}
