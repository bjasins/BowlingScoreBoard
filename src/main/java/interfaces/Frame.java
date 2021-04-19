package interfaces;

import java.util.List;

/**
 * An interface defining the core functionality for a bowling frame.
 */
public interface Frame {

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