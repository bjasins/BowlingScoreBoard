package interfaces;

/**
 * An interface that defines the core functionality for a utility that interacts
 * with a {@link Frame}.
 * 
 * @param <frameType> the type of the {@code Frame} to utility will process.
 */
public interface FrameUtil<frameType extends Frame> {

    /**
     * @param frame the {@code frameType} to evaluate.
     * @return a boolean signifying if the frame has been completed.
     */
    public boolean isFrameComplete(frameType frame);

    /**
     * @param frame the {@code frameType} to evaluate.
     * @return a boolean signifying if the result of the frame was a strike.
     */
    public boolean isFrameStrike(frameType frame);

    /**
     * @param frame the {@code frameType} to evaluate.
     * @return a boolean signifying if the result of the frame was a spare.
     */
    public boolean isFrameSpare(frameType frame);

    /**
     * Input the result of a singular roll into the frame.
     * 
     * @param frame      the {@code frameType} to evaluate.
     * @param rollResult an int representing the score of a roll.
     */
    public void enterRollResult(frameType frameType, int rollResult);

    /**
     * Sums the total score of the frame.
     * 
     * @param frame the {@code frameType} to evaluate.
     * @return an int representing the frame score.
     */
    default int getFinalFrameScore(frameType frameType) {
        return frameType.getRollResultList().stream().mapToInt(Integer::intValue).sum();
    }
}
