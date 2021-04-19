package bowling;

import java.util.Collections;
import java.util.List;

import interfaces.Frame;

/**
 * An implementation of {@link Frame} meant to represent one of the first nine
 * frames in a bowling row. This implementation is characterized by the limit of
 * only two rolls.
 */
public class RegularFrame implements Frame {
    private static final int MAX_ROLLS_ALLOWED = 2;
    private final List<Integer> rollResultList;

    protected RegularFrame() {
        rollResultList = Collections.emptyList();
    }

    @Override
    public boolean isFrameComplete() {
        if (rollResultList.size() == MAX_ROLLS_ALLOWED) {
            return true;
        } else if (getFinalFrameScore() == 10) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isFrameStrike() {
        return getFinalFrameScore() == 10 && rollResultList.size() == 1;
    }

    @Override
    public boolean isFrameSpare() {
        return getFinalFrameScore() == 10 && rollResultList.size() == 2;
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
