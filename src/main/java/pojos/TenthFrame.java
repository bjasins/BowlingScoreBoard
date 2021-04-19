package pojos;

import java.util.Collections;

import interfaces.Frame;
import java.util.List;

/**
 * An implementation of {@link Frame} meant to represent the tenth frame in a
 * bowling row. This implementation is characterized by the initial limit of two
 * rolls, with a third roll possible.
 */
public class TenthFrame implements Frame {

    private static final int MAX_ROLLS_ALLOWED = 3;
    private static final int NORMAL_ROLLS_ALLOWED = 2; // general class
    private static final int MINIMUM_SCORE_TO_UNLOCK_MAX_ROLLS = 10;

    private final List<Integer> rollResultList;

    public TenthFrame() {
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

    /**
     * @return an int representing the normal rolls allowed if max rolls are not
     *         unlocked.
     */
    public int getNormalRollsAllowed() {
        return NORMAL_ROLLS_ALLOWED;
    }

    /**
     * @return an int representing the minimum score needed in order to unlock max
     *         rolls.
     */
    public int getMinimumScoreToUnlockMaxRolls() {
        return MINIMUM_SCORE_TO_UNLOCK_MAX_ROLLS;
    }
}
