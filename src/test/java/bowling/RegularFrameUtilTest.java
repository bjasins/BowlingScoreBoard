package bowling;

import org.mockito.Mock;
import org.mockito.Mockito;

import pojos.RegularFrame;

public class RegularFrameUtilTest {
    private RegularFrameUtil regularFrameUtil;
    @Mock
    private RegularFrame mockRegularFrame;

    @Before
    public void setup() {
        regularFrameUtil = new RegularFrameUtil();
        mockRegularFrame = Mockito.mock(RegularFrame.class);
    }

    @After
    public void tearDown() {
        regularFrameUtil = null;
        mockRegularFrame = null;
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
