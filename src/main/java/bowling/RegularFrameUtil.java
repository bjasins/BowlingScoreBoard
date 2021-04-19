package bowling;

import java.util.List;

import interfaces.FrameUtil;
import pojos.RegularFrame;

/**
 * An implementation of {@link FrameUtil} meant to represent one of the first nine
 * frames in a bowling row. This implementation is characterized by the limit of
 * only two rolls.
 */
public class RegularFrameUtil implements FrameUtil<RegularFrame> {

    @Override
    public boolean isFrameComplete(RegularFrame regularFrame) {
        List<Integer> rollResultList = regularFrame.getRollResultList();
        int maxRolls = regularFrame.getMaxRollsAllowed();
        if (rollResultList.size() == maxRolls) {
            return true;
        } else if (getFinalFrameScore(regularFrame) == 10) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isFrameStrike(RegularFrame regularFrame) {
        List<Integer> rollResultList = regularFrame.getRollResultList();
        return getFinalFrameScore(regularFrame) == 10 && rollResultList.size() == 1;
    }

    @Override
    public boolean isFrameSpare(RegularFrame regularFrame) {
        List<Integer> rollResultList = regularFrame.getRollResultList();
        return getFinalFrameScore(regularFrame) == 10 && rollResultList.size() == 2;
    }

    // @Override
    // public List<Integer> getRollResultList(Frame regularFrame) {
    //     List<Integer> rollResultList = regularFrame.getRollResultList();
    //     return rollResultList;
    // }

    @Override
    public void enterRollResult(RegularFrame regularFrame, int rollResult) throws IllegalArgumentException, IllegalStateException {
        if (isFrameComplete(regularFrame)) {
            throw new IllegalStateException("Frame has already been completed");
        }
        if (rollResult < 0 || rollResult > 10) {
            throw new IllegalArgumentException("Individual frame roll must result in 0-10 pins");
        }
        regularFrame.addRowResult(rollResult);
    }

}
