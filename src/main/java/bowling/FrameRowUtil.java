package bowling;

import interfaces.Frame;
import pojos.FrameRow;
import pojos.RegularFrame;
import pojos.TenthFrame;
import static interfaces.Frame.MAX_VALID_ROLL_SCORE;
import static interfaces.Frame.MINIMUM_VALID_ROLL_SCORE;

/**
 * Provides the functionality for interacting with a {@link FrameRow}.
 */
public class FrameRowUtil {
    private final static int NUMBER_OF_FRAMES_ALLOWED = 10;
    private final static int NUMBER_OF_REGULAR_FRAMES = 9;
    private final static int TENTH_FRAME_INDEX = 9;
    private final static int STRIKE_BONUS_TRIES = 2;
    private final static int SPARE_BONUS_TRIES = 1;
    private final RegularFrameUtil regularFrameUtil;
    private final TenthFrameUtil tenthFrameUtil;
    


    public FrameRowUtil(RegularFrameUtil regularFrameUtil, TenthFrameUtil tenthFrameUtil) {
        this.regularFrameUtil = regularFrameUtil;
        this.tenthFrameUtil = tenthFrameUtil;
    }

    /**
     * @return an int representing the number of frames
     * allowed for a row.
     */
    protected static int getNumberOfFramesAllowed(){
        return NUMBER_OF_FRAMES_ALLOWED;
    }

    /**
     * @param frameRow the {@code FrameRow} to evaluate.
     * @return the {@code Frame} that is currently in progress.
     */
    protected Frame getCurrentFrame(FrameRow framerow) {
        return framerow.getCurrentFrame();
    }

    /**
     * @param frameRow the {@code FrameRow} to evaluate.
     * @return the number of frames present in the row.
     */
    protected int getSize(FrameRow framerow) {
        return framerow.getFrames().size();
    }

    /**
     * @return the {@link RegularFrameUtil} the FrameRowUtil is
     * configured with.
     */
    protected RegularFrameUtil getRegularFrameUtil() {
        return regularFrameUtil;
    }

    /**
     * @return the {@link TenthFrameUtil} the FrameRowUtil is
     * configured with.
     */
    protected TenthFrameUtil getTenthFrameUtil() {
        return tenthFrameUtil;
    }
    /**
     * @param frameRow the {@code FrameRow} to evaluate.
     * @return a boolean signifying if the row of frames is completed.
     */
    protected boolean isRowComplete(FrameRow framerow) {
        if (framerow.getFrames().size() == NUMBER_OF_FRAMES_ALLOWED
                && tenthFrameUtil.isFrameComplete((TenthFrame) framerow.getFrames().get(TENTH_FRAME_INDEX))) {
            return true;
        }
        return false;
    }

    /**
     * Calculates the total score of the row, taking into consideration the rules
     * regarding spares and strikes. If a spare is scored, the next roll is also
     * added to the current frame's score. If a strike is scored, the next two rolls
     * are also added to the current frame's score.
     * 
     * @param frameRow the {@code FrameRow} to evaluate.
     * @return an int representing the score of the frame
     */
    public int getTotalScore(FrameRow framerow) {
        int sum = 0;
        for (int frameIndex = 0; frameIndex < framerow.getFrames().size(); frameIndex++) {
            Frame frame = framerow.getFrames().get(frameIndex);
            if (frame instanceof TenthFrame) {
                sum += tenthFrameUtil.getFinalFrameScore((TenthFrame) frame);
                if (tenthFrameUtil.isFrameStrike((TenthFrame) framerow.getFrames().get(frameIndex))) {
                    sum += peakNextFrame(framerow, frameIndex, STRIKE_BONUS_TRIES);
                } else if (tenthFrameUtil.isFrameSpare((TenthFrame) framerow.getFrames().get(frameIndex))) {
                    sum += peakNextFrame(framerow, frameIndex, SPARE_BONUS_TRIES);
                }
            } else {
                sum += regularFrameUtil.getFinalFrameScore((RegularFrame) frame);
                if (regularFrameUtil.isFrameStrike((RegularFrame) framerow.getFrames().get(frameIndex))) {
                    sum += peakNextFrame(framerow, frameIndex, STRIKE_BONUS_TRIES);
                } else if (regularFrameUtil.isFrameSpare((RegularFrame) framerow.getFrames().get(frameIndex))) {
                    sum += peakNextFrame(framerow, frameIndex, SPARE_BONUS_TRIES);
                }
            }
        }
        return sum;
    }

    /**
     * @param frameRow the {@code FrameRow} to evaluate. Creates a new frame within
     *                 the row.
     * @throws {@code IllegalStateException} if the frameRow is already completed.
     */
    protected void startNewFrame(FrameRow framerow) throws IllegalStateException {
        if (isRowComplete(framerow)) {
            throw new IllegalStateException("Frame row has alrady completed");
        }
        if (framerow.getFrames().size() < NUMBER_OF_REGULAR_FRAMES) {
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
     * @param FrameRow   the {@code FrameRow} to evaluate.
     * @param rollResult an int representing the score of the roll.
     * @throws {@code IllegalStateException} if the frame has already been
     * completed.
     * @throws {@code IllegalArgumentException} if the roll score is not
     * within a valid range.
     */
    protected void playerRoll(FrameRow framerow, int rollResult) throws IllegalStateException, IllegalArgumentException {
        if (rollResult < MINIMUM_VALID_ROLL_SCORE || rollResult > MAX_VALID_ROLL_SCORE) {
            throw new IllegalArgumentException("Invalid score for the roll");
        }
        Frame frame = framerow.getFrames().get(framerow.getActiveFrameIndex());
        if (frame instanceof TenthFrame) {
            if (tenthFrameUtil.isFrameComplete((TenthFrame) frame)) {
                throw new IllegalStateException("Frame has already been completed");
            }
            tenthFrameUtil.enterRollResult((TenthFrame) frame, rollResult);
        } else {
            if (regularFrameUtil.isFrameComplete((RegularFrame) frame)) {
                throw new IllegalStateException("Frame has already been completed");
            }
            regularFrameUtil.enterRollResult((RegularFrame) frame, rollResult);
        }
    }

    /**
     * Retrieves the score successive rolls following a frame that resulted in a
     * spare or strike.
     * 
     * @param frameRow            the {@code FrameRow} to evaluate.
     * @param startIndex          an int indicating which frame the spare or strike
     *                            happened.
     * @param numberToLookForward an int indicating how many rolls to peak ahead.
     *                            Must be a value of either 1 or 2.
     * @return the sum of the rolls that qualify.
     * @throws {@code IllegalArgumentException} if an invalid startIndex or
     * numberToLookForward is given.
     */
    private int peakNextFrame(FrameRow framerow, int startIndex, int numberToLookForward)
            throws IllegalArgumentException {
        if (startIndex < 0 || startIndex >= framerow.getFrames().size()) { // invalid index
            throw new IllegalArgumentException("startIndex must be within valid range");
        }
        if (numberToLookForward != SPARE_BONUS_TRIES && numberToLookForward != STRIKE_BONUS_TRIES) {
            throw new IllegalArgumentException("numberToLookForward must be either " + SPARE_BONUS_TRIES + " or " + STRIKE_BONUS_TRIES);
        }
        if (startIndex == framerow.getFrames().size() - 1) {
            return 0;
        }
        Frame frame = framerow.getFrames().get(startIndex + 1);
        if (frame instanceof TenthFrame) {
            return numberToLookForward == STRIKE_BONUS_TRIES ? frame.getRollResultList().get(0) + frame.getRollResultList().get(1) : frame.getRollResultList().get(0);
        } else {
            if (numberToLookForward == STRIKE_BONUS_TRIES && regularFrameUtil.isFrameStrike((RegularFrame) frame)) {
                return 10 + peakNextFrame(framerow, startIndex + 1, STRIKE_BONUS_TRIES - 1);
            }
            return numberToLookForward == STRIKE_BONUS_TRIES ? regularFrameUtil.getFinalFrameScore((RegularFrame) frame)
                    : frame.getRollResultList().get(0);
        }

    }
}
