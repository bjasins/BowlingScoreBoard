package interfaces;

import java.util.List;

/**
 * An interface defining the core functionality for a bowling frame.
 */
public interface Frame {

    /**
     * An int representing the minimum possible score 
     * for a single roll attempt.
     */
    public final static int MINIMUM_VALID_ROLL_SCORE = 0;
    /**
     * An int representing the maximum possible score  
     * for a single roll attempt.
     */
    public final static int MAX_VALID_ROLL_SCORE = 10;
    /**
     * An int representing the score that is associated
     * with both a strike and a spare.
     */
    public static final int STRIKE_SPARE_SCORE = 10;

    /**
     * @return the List of Integers describing the roll results of the frame.
     */
    public List<Integer> getRollResultList();

    /**
     * @return an int representing the max rolls allowed in the frame.
     */
    public int getMaxRollsAllowed();

    /**
     * Add a result to the row results.
     * 
     * @param rollResult an int representing the result of a roll.
     */
    public void addRollResult(int rollResult);
}