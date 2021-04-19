package pojos;

import java.util.Collections;

import interfaces.Frame;
import java.util.List;

/**
 * An implementation of {@link Frame} meant to represent one of the first nine
 * frames in a bowling row. This implementation is characterized by the limit of
 * only two rolls.
 */
public class RegularFrame implements Frame {

    private static final int MAX_ROLLS_ALLOWED = 2;
    private final List<Integer> rollResultList;

    public RegularFrame() {
        rollResultList = Collections.emptyList();
    }

    @Override
    public List<Integer> getRollResultList() {
        return rollResultList;
    }

    @Override
    public int getMaxRollsAllowed() {
        return MAX_ROLLS_ALLOWED;
    }

    @Override
    public void addRollResult(int rowResult) {
        rollResultList.add(rowResult);
    }
}