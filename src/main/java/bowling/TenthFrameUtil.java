package bowling;

import java.util.List;

import interfaces.Frame;
import interfaces.FrameUtil;
import pojos.TenthFrame;

/**
 * An implementation of {@link Frame} meant to represent the tenth frame in a
 * bowling row. This implementation is characterized by the initial limit of two
 * rolls, with a third roll possible.
 */
public class TenthFrameUtil implements FrameUtil<TenthFrame> { //TODO force type

    @Override
    public boolean isFrameComplete(TenthFrame tenthFrame) {
        List<Integer> rollResultList = tenthFrame.getRollResultList();
        if (rollResultList.size() == tenthFrame.getMaxRollsAllowed()) {
            return true;
        }
        if (rollResultList.size() == tenthFrame.getNormalRollsAllowed() && getFinalFrameScore(tenthFrame) < tenthFrame.getMinimumScoreToUnlockMaxRolls()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isFrameStrike(TenthFrame tenthFrame) {
        return tenthFrame.getRollResultList().size() > 0 && tenthFrame.getRollResultList().get(0) == 10;
    }

    @Override
    public boolean isFrameSpare(TenthFrame tenthFrame) {
        return tenthFrame.getRollResultList().size() > 1 && tenthFrame.getRollResultList().get(0) + tenthFrame.getRollResultList().get(1) == 10;
    }

    @Override
    public void enterRollResult(TenthFrame tenthFrame, int rollResult) throws IllegalArgumentException, IllegalStateException {
        if (isFrameComplete(tenthFrame)) {
            throw new IllegalStateException("Frame has already been completed");
        }
        if (rollResult < 0 || rollResult > 10) {
            throw new IllegalArgumentException("Individual frame roll must result in 0-10 pins");
        }
        tenthFrame.getRollResultList().add(rollResult);
    }
}
