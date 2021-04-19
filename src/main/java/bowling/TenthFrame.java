package bowling;

import java.util.Collections;
import java.util.List;

import interfaces.Frame;

/**
 * An implementation of {@link Frame} meant to represent the tenth frame in a
 * bowling row. This implementation is characterized by the initial limit of two
 * rolls, with a third roll possible.
 */
public class TenthFrame implements Frame {
    private static final int MAX_ROLLS_ALLOWED = 3;
    private static final int NORMAL_ROLLS_ALLOWED = 2; // general class
    // private static final int MINIMUM_SCORE_POSSIBLE = 0;
    private static final int MINIMUM_SCORE_TO_UNLOCK_MAX_ROLLS = 10;

    private final List<Integer> rollResultList;

    protected TenthFrame() {
        rollResultList = Collections.emptyList();
    }

    @Override
    public boolean isFrameComplete() {
        if (rollResultList.size() == MAX_ROLLS_ALLOWED) {
            return true;
        }
        if (rollResultList.size() == NORMAL_ROLLS_ALLOWED && getFinalFrameScore() < MINIMUM_SCORE_TO_UNLOCK_MAX_ROLLS) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isFrameStrike() {
        return getRollResultList().size() > 0 && getRollResultList().get(0) == 10;
    }

    @Override
    public boolean isFrameSpare() {
        return getRollResultList().size() > 1 && getRollResultList().get(0) + getRollResultList().get(1) == 10;
    }

    @Override
    public List<Integer> getRollResultList() {
        return rollResultList;
    }

    @Override
    public void enterRollResult(int rollResult) throws IllegalArgumentException, IllegalStateException {
        if (isFrameComplete()) {
            throw new IllegalStateException("Frame has already been completed");
        }
        if (rollResult < 0 || rollResult > 10) {
            throw new IllegalArgumentException("Individual frame roll must result in 0-10 pins");
        }
        rollResultList.add(rollResult);
    }
}
