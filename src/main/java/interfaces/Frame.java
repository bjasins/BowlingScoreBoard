package interfaces;

import java.util.List;

/**
 * An interface that defines the common functionality for a bowling frame.
 */
public interface Frame {

    /**
     * @return the List of Integers describing the roll results of the frame.
     */
    public List<Integer> getRollResultList();

    /**
     * @return a boolean signifying if the frame has been completed.
     */
    public boolean isFrameComplete();

    /**
     * @return a boolean signifying if the result of the frame was a strike.
     */
    public boolean isFrameStrike();

    /**
     * @return a boolean signifying if the result of the frame was a spare.
     */
    public boolean isFrameSpare();

    /**
     * Input the result of a singular roll into the frame.
     * 
     * @param rollResult an int representing the score of a roll.
     */
    public void enterRollResult(int rollResult);

    /**
     * Sums the total score of the frame.
     * 
     * @return an int representing the frame score.
     */
    default int getFinalFrameScore() {
        return getRollResultList().stream().mapToInt(Integer::intValue).sum();
    }
}
