package pojos;

import java.util.Collections;

import interfaces.Frame;
import java.util.List;

/**
 * TODO.
 */
public class RegularFrame  implements Frame{

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
    public void addRowResult(int rowResult) {
        rollResultList.add(rowResult);
    }
}