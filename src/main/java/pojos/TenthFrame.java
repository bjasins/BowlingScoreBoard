package pojos;

import java.util.Collections;

import interfaces.Frame;
import java.util.List;

/**
 * TODO.
 */
public class TenthFrame  implements Frame{

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
    public void addRowResult(int rowResult) {
        rollResultList.add(rowResult);
    }

    public int getNormalRollsAllowed() {
        return NORMAL_ROLLS_ALLOWED;
    }

    public int getMinimumScoreToUnlockMaxRolls() {
        return MINIMUM_SCORE_TO_UNLOCK_MAX_ROLLS;
    }
}
