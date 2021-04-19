package bowling;

import java.util.Arrays;
import java.util.List;

import interfaces.Frame;

/**
 * Holds the information for a row of {@link Frame}s and provides the
 * functionality for interacting with said frames.
 */
public class FrameRow {
    private final List<Frame> frames;
    private int activeFrameIndex;

    protected FrameRow() {
        frames = Arrays.asList(new RegularFrame());
        activeFrameIndex = 0;
    }

    /**
     * @return a boolean signifying if the row of frames is completed.
     */
    protected boolean isRowComplete() {
        if (frames.size() == 10 && frames.get(9).isFrameComplete()) {
            return true;
        }
        return false;
    }

    /**
     * @return the {@code Frame} that is currently in progress.
     */
    protected Frame getCurrentFrame() {
        return frames.get(activeFrameIndex);
    }

    /**
     * @return the number of frames present in the row.
     */
    protected int getSize() {
        return frames.size();
    }

    /**
     * Calculates the total score of the row, taking into consideration the rules
     * regarding spares and strikes. If a spare is scored, the next roll is also
     * added to the current frame's score. If a strike is scored, the next two rolls
     * are also added to the current frame's score.
     * 
     * @return an int representing the score of the frame
     */
    protected int getTotalScore() {
        int sum = 0;
        for (int i = 0; i < frames.size(); i++) {
            sum += frames.get(i).getFinalFrameScore();
            if (frames.get(i).isFrameStrike()) {
                sum += peakNextFrame(i, 2);
            } else if (frames.get(i).isFrameSpare()) {
                sum += peakNextFrame(i, 1);
            }
        }
        return sum;
    }

    /**
     * Creates a new frame within the row.
     */
    protected void startNewFrame() throws IllegalStateException {
        if (isRowComplete()) {
            throw new IllegalStateException("Frame row has alrady completed");
        }
        if (frames.size() < 9) {
            RegularFrame regularFrame = new RegularFrame();
            frames.add(regularFrame);
        } else {
            TenthFrame tenthFrame = new TenthFrame();
            frames.add(tenthFrame);
        }
        activeFrameIndex++;
    }

    /**
     * Input the result of a singular roll of the current frame.
     * 
     * @param rollResult an int representing the score of the roll.
     */
    protected void playerRoll(int rollResult) throws IllegalStateException {
        if (frames.get(activeFrameIndex).isFrameComplete()) {
            throw new IllegalStateException("Frame has already been completed");
        }
        frames.get(activeFrameIndex).enterRollResult(rollResult);
    }

    /**
     * Retrieves the score successive rolls following a frame that resulted in a
     * spare or strike.
     * 
     * @param startIndex          an int indicating which frame the spare or strike
     *                            happened.
     * @param numberToLookForward an int indicating how many rolls to peak ahead.
     *                            Must be a value of either 1 or 2.
     * @return the sum of the rolls that qualify.
     */
    private int peakNextFrame(int startIndex, int numberToLookForward) throws IllegalArgumentException {
        if (startIndex < 0 || startIndex >= frames.size()) { // invalid index
            throw new IllegalArgumentException("startIndex must be within valid range");
        }
        if (numberToLookForward < 1 || numberToLookForward > 2) {
            throw new IllegalArgumentException("numberToLookForward must be either 1 or 2");
        }
        if (startIndex == frames.size() - 1) { // current frame, no additional rolls to evaluate yet. Also includes
                                               // tenth frame
            return 0;
        }
        Frame frame = frames.get(startIndex + 1);
        if (numberToLookForward == 2 && frame.isFrameStrike()) {
            return 10 + peakNextFrame(startIndex + 1, 1);
        }
        return numberToLookForward == 2 ? frame.getFinalFrameScore() : frame.getRollResultList().get(0);
    }
}
