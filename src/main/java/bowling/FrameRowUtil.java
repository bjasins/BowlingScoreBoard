package bowling;

import interfaces.Frame;
import pojos.FrameRow;
import pojos.RegularFrame;
import pojos.TenthFrame;

/**
 * Holds the information for a row of {@link Frame}s and provides the
 * functionality for interacting with said frames.
 */
public class FrameRowUtil {
    private static final RegularFrameUtil regularFrameUtil = new RegularFrameUtil(); //make static names
    private static final TenthFrameUtil tenthFrameUtil = new TenthFrameUtil();

    /**
     * @return a boolean signifying if the row of frames is completed.
     */
    protected boolean isRowComplete(FrameRow framerow) {
        if (framerow.getFrames().size() == 10
                && tenthFrameUtil.isFrameComplete((TenthFrame) framerow.getFrames().get(9))) {
            return true;
        }
        return false;
    }

    /**
     * @return the {@code Frame} that is currently in progress.
     */
    protected Frame getCurrentFrame(FrameRow framerow) {
        return framerow.getCurrentFrame();
    }

    /**
     * @return the number of frames present in the row.
     */
    protected int getSize(FrameRow framerow) {
        return framerow.getFrames().size();
    }

    /**
     * Calculates the total score of the row, taking into consideration the rules
     * regarding spares and strikes. If a spare is scored, the next roll is also
     * added to the current frame's score. If a strike is scored, the next two rolls
     * are also added to the current frame's score.
     * 
     * @return an int representing the score of the frame
     */
    public int getTotalScore(FrameRow framerow) {
        int sum = 0;
        for (int i = 0; i < framerow.getFrames().size(); i++) {
            Frame frame = framerow.getFrames().get(i);
            if (frame instanceof TenthFrame) {
                sum += tenthFrameUtil.getFinalFrameScore((TenthFrame)frame);
                if (tenthFrameUtil.isFrameStrike((TenthFrame)framerow.getFrames().get(i))) {
                    sum += peakNextFrame(framerow, i, 2);
                } else if (tenthFrameUtil.isFrameSpare((TenthFrame)framerow.getFrames().get(i))) {
                    sum += peakNextFrame(framerow, i, 1);
                }
            }
            else {
                sum += regularFrameUtil.getFinalFrameScore((RegularFrame)frame);
                if (regularFrameUtil.isFrameStrike((RegularFrame)framerow.getFrames().get(i))) {
                    sum += peakNextFrame(framerow, i, 2);
                } else if (regularFrameUtil.isFrameSpare((RegularFrame)framerow.getFrames().get(i))) {
                    sum += peakNextFrame(framerow, i, 1);
                }
            }
        }
        return sum;
    }

    /**
     * Creates a new frame within the row.
     */
    protected void startNewFrame(FrameRow framerow) throws IllegalStateException {
        if (isRowComplete(framerow)) {
            throw new IllegalStateException("Frame row has alrady completed");
        }
        if (framerow.getFrames().size() < 9) {
            RegularFrame regularFrame = new RegularFrame();
            framerow.addFrame(regularFrame);
        } else {
            TenthFrame tenthFrame = new TenthFrame();
            framerow.addFrame(tenthFrame);
        }
        framerow.incrementActiveIndex();
    }

    /**
     * Input the result of a singular roll of the current frame.
     * 
     * @param rollResult an int representing the score of the roll.
     */
    protected void playerRoll(FrameRow framerow, int rollResult) throws IllegalStateException {
        Frame frame = framerow.getFrames().get(framerow.getActiveFrameIndex());
        if (frame instanceof TenthFrame) {
            if (tenthFrameUtil.isFrameComplete((TenthFrame)frame)) {
                throw new IllegalStateException("Frame has already been completed");
            }
            tenthFrameUtil.enterRollResult((TenthFrame) frame, rollResult);
        }
        else {
            if (regularFrameUtil.isFrameComplete((RegularFrame)frame)) {
                throw new IllegalStateException("Frame has already been completed");
            }
            regularFrameUtil.enterRollResult((RegularFrame) frame, rollResult);
        }
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
    private int peakNextFrame(FrameRow framerow, int startIndex, int numberToLookForward) throws IllegalArgumentException {
        if (startIndex < 0 || startIndex >= framerow.getFrames().size()) { // invalid index
            throw new IllegalArgumentException("startIndex must be within valid range");
        }
        if (numberToLookForward < 1 || numberToLookForward > 2) {
            throw new IllegalArgumentException("numberToLookForward must be either 1 or 2");
        }
        if (startIndex == framerow.getFrames().size() - 1) { // current frame, no additional rolls to evaluate yet. Also includes
                                               // tenth frame
            return 0;
        }
        Frame frame = framerow.getFrames().get(startIndex + 1);
        if (frame instanceof TenthFrame) {
            if (numberToLookForward == 2 && tenthFrameUtil.isFrameStrike((TenthFrame) frame)) {
                return 10 + peakNextFrame(framerow, startIndex + 1, 1);
            }
            return numberToLookForward == 2 ? tenthFrameUtil.getFinalFrameScore((TenthFrame) frame) : frame.getRollResultList().get(0);
        }
        else {
            if (numberToLookForward == 2 && regularFrameUtil.isFrameStrike((RegularFrame) frame)) {
                return 10 + peakNextFrame(framerow, startIndex + 1, 1);
            }
            return numberToLookForward == 2 ? regularFrameUtil.getFinalFrameScore((RegularFrame) frame) : frame.getRollResultList().get(0);
        }

    }
}
