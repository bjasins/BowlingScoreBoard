package pojos;

import interfaces.Frame;
import java.util.Arrays;
import java.util.List;

/**
 * A POJO meant to represent the row of frames.
 */
public class FrameRow {
    private final List<Frame> frames;
    private int activeFrameIndex;

    public FrameRow() {
        frames = Arrays.asList(new RegularFrame());
        activeFrameIndex = 0;
    }

    /**
     * @return a List of the frames belonging to the row.
     */
    public List<Frame> getFrames() {
        return frames;
    }

    /**
     * @return an int representing the index of the active frame.
     */
    public int getActiveFrameIndex() {
        return activeFrameIndex;
    }

    /**
     * Increments the active frame index.
     */
    public void incrementActiveIndex() {
        activeFrameIndex++;
    }

    /**
     * @return the {@link Frame} that is currently active.
     */
    public Frame getCurrentFrame() {
        return frames.get(activeFrameIndex);
    }

    /**
     * Add a Frame to the row.
     * 
     * @param frame the {@code Frame} to add.
     */
    public void addFrame(Frame frame) {
        frames.add(frame);
    }
}
