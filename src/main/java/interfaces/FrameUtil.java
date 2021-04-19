package interfaces;

/**
 * An interface that defines the common functionality for a bowling frame.
 */
public interface FrameUtil<frameType extends Frame>{

    /**
     * @return a boolean signifying if the frame has been completed.
     */
    public boolean isFrameComplete(frameType frame);

    /**
     * @return a boolean signifying if the result of the frame was a strike.
     */
    public boolean isFrameStrike(frameType frame);

    /**
     * @return a boolean signifying if the result of the frame was a spare.
     */
    public boolean isFrameSpare(frameType frame);

    /**
     * Input the result of a singular roll into the frame.
     * 
     * @param rollResult an int representing the score of a roll.
     */
    public void enterRollResult(frameType frameType, int rollResult);

    /**
     * Sums the total score of the frame.
     * 
     * @return an int representing the frame score.
     */
    default int getFinalFrameScore(frameType frameType) {
        return frameType.getRollResultList().stream().mapToInt(Integer::intValue).sum();
    }
}
