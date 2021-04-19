package bowling;

import java.util.List;

import interfaces.FrameUtil;
import pojos.TenthFrame;

/**
 * An implementation of {@link FrameUtil} for the processing of a
 * {@link TenthFrame} frame in a bowling row.
 */
public class TenthFrameUtil implements FrameUtil<TenthFrame> {

    @Override
    public boolean isFrameComplete(TenthFrame tenthFrame) {
        List<Integer> rollResultList = tenthFrame.getRollResultList();
        if (rollResultList.size() == tenthFrame.getMaxRollsAllowed()) {
            return true;
        }
        if (rollResultList.size() == tenthFrame.getNormalRollsAllowed()
                && getFinalFrameScore(tenthFrame) < tenthFrame.getMinimumScoreToUnlockMaxRolls()) {
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
        return tenthFrame.getRollResultList().size() > 1
                && tenthFrame.getRollResultList().get(0) + tenthFrame.getRollResultList().get(1) == 10;
    }

    @Override
    public void enterRollResult(TenthFrame tenthFrame, int rollResult)
            throws IllegalArgumentException, IllegalStateException {
        if (isFrameComplete(tenthFrame)) {
            throw new IllegalStateException("Frame has already been completed");
        }
        if (rollResult < 0 || rollResult > 10) {
            throw new IllegalArgumentException("Individual frame roll must result in 0-10 pins");
        }
        tenthFrame.getRollResultList().add(rollResult);
    }
}
