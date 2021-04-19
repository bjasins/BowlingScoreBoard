package bowling;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;

import pojos.TenthFrame;

public class TenthFrameUtilTest {
    
    private TenthFrameUtil tenthFrameUtil;
    @Mock
    private TenthFrame mockTenthFrame;

    @Before
    public void setup() {
        tenthFrameUtil = new TenthFrameUtil();
        mockTenthFrame = Mockito.mock(TenthFrame.class);
    }

    @After
    public void tearDown() {
        tenthFrameUtil = null;
        mockTenthFrame = null;
    }

    public void testIsFrameCompleteTrue() {
        //TODO
    }

    public void testIsFrameCompleteFalse() {
        //TODO
    }

    public void testIsFrameStrikeTrue() {
        //TODO
    }

    public void testIsFrameStrikeFalse() {
        //TODO
    }

    public void testIsFrameSpareTrue() {
        //TODO
    }

    public void testIsFrameSpareFalse() {
        //TODO
    }

    public void testEnterRollResultFrameCompleteError() {
        //TODO
    }

    public void testEnterRollResultInvalidScore() {
        //TODO
    }

    public void testEnterRollResultHappyPath() {
        //TODO
    }
}
