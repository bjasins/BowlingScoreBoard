package bowling;

import java.util.List;

import interfaces.FrameUtil;
import pojos.TenthFrame;

import static interfaces.Frame.MAX_VALID_ROLL_SCORE;
import static interfaces.Frame.MINIMUM_VALID_ROLL_SCORE;
import static interfaces.Frame.STRIKE_SPARE_SCORE;

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
        return tenthFrame.getRollResultList().size() > 0 && tenthFrame.getRollResultList().get(0) == STRIKE_SPARE_SCORE;
    }

    @Override
    public boolean isFrameSpare(TenthFrame tenthFrame) {
        return tenthFrame.getRollResultList().size() > 1
                && tenthFrame.getRollResultList().get(0) + tenthFrame.getRollResultList().get(1) == STRIKE_SPARE_SCORE;
    }

    @Override
    public void enterRollResult(TenthFrame tenthFrame, int rollResult)
            throws IllegalArgumentException, IllegalStateException {
        if (isFrameComplete(tenthFrame)) {
            throw new IllegalStateException("Frame has already been completed");
        }
        if (rollResult < MINIMUM_VALID_ROLL_SCORE || rollResult > MAX_VALID_ROLL_SCORE) {
            throw new IllegalArgumentException("Individual frame roll must result in " + MINIMUM_VALID_ROLL_SCORE + "-"
                    + MAX_VALID_ROLL_SCORE + " pins");
        }
        tenthFrame.getRollResultList().add(rollResult);
    }
}
