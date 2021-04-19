package bowling;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;

import pojos.FrameRow;

public class FrameRowUtilTest {
    private FrameRowUtil frameRowUtil;
    @Mock
    private FrameRow mockFrameRow;

    @Before
    public void setup() {
        frameRowUtil = new FrameRowUtil();
        mockFrameRow = Mockito.mock(FrameRow.class);
    }

    @After
    public void tearDown() {
        frameRowUtil = null;
        mockFrameRow = null;
    }

    public void testIsRowCompleteTrue() {
        //TODO
    }

    public void testIsRowCompleteFalse() {
        //TODO
    }

    public void testGetTotalScoreEmptyRow() {
        //TODO
    }

    public void testGetTotalScorePartialRow() {
        //TODO
    }

    public void testGetTotalScoreCompleteRowPartialTenthFrame() {
        //TODO
    }

    public void testGetTotalScoreCompleteRowFullTenthFrame() {
        //TODO
    }

    public void testStartNewFrameRowAlreadyComplete() {
        //TODO
    }

    public void testStartNewFrameTenthFrame() {
        //TODO
    }

    public void testStartNewFrame() {
        //TODO
    }

    public void testPlayerRollFrameAlreadyComplete() {
        //TODO
    }

    public void testPlayerRollInvalidInput() {
        //TODO
    }

    public void testPlayerRollHappyPath() {
        //TODO
    }
}
