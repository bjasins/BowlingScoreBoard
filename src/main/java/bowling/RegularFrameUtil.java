package bowling;

import java.util.List;

import interfaces.FrameUtil;
import pojos.RegularFrame;
import static interfaces.Frame.MAX_VALID_ROLL_SCORE;
import static interfaces.Frame.MINIMUM_VALID_ROLL_SCORE;
import static interfaces.Frame.STRIKE_SPARE_SCORE;

/**
 * An implementation of {@link FrameUtil} for the processing of a
 * {@link RegularFrame} in a bowling row.
 */
public class RegularFrameUtil implements FrameUtil<RegularFrame> {

    @Override
    public boolean isFrameComplete(RegularFrame regularFrame) {
        List<Integer> rollResultList = regularFrame.getRollResultList();
        int maxRolls = regularFrame.getMaxRollsAllowed();
        if (rollResultList.size() == maxRolls) {
            return true;
        } else if (getFinalFrameScore(regularFrame) == STRIKE_SPARE_SCORE) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isFrameStrike(RegularFrame regularFrame) {
        List<Integer> rollResultList = regularFrame.getRollResultList();
        return getFinalFrameScore(regularFrame) == STRIKE_SPARE_SCORE && rollResultList.size() == 1;
    }

    @Override
    public boolean isFrameSpare(RegularFrame regularFrame) {
        List<Integer> rollResultList = regularFrame.getRollResultList();
        return getFinalFrameScore(regularFrame) == STRIKE_SPARE_SCORE && rollResultList.size() == 2;
    }

    @Override
    public void enterRollResult(RegularFrame regularFrame, int rollResult)
            throws IllegalArgumentException, IllegalStateException {
        if (isFrameComplete(regularFrame)) {
            throw new IllegalStateException("Frame has already been completed");
        }
        if (rollResult < MINIMUM_VALID_ROLL_SCORE || rollResult > MAX_VALID_ROLL_SCORE) {
            throw new IllegalArgumentException("Individual frame roll must result in " + MINIMUM_VALID_ROLL_SCORE + "-"+ MAX_VALID_ROLL_SCORE+" pins");
        }
        regularFrame.addRollResult(rollResult);
    }

}
