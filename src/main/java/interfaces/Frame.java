package interfaces;

import java.util.List;

/**
 * TODO
 */
public interface Frame {

    /**
     * @return the List of Integers describing the roll results of the frame.
     */
    public List<Integer> getRollResultList();

    public int getMaxRollsAllowed();

    public void addRowResult(int rowResult);
}