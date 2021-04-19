package bowling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import interfaces.Frame;
import pojos.FrameRow;
import pojos.RegularFrame;
import pojos.TenthFrame;

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
    @Test
    public void testIsRowCompleteTrue() {
        List<Frame> mockFrames;//populates with 10 mock frames
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        //pass isframecomplete
        assertTrue(frameRowUtil.isRowComplete(mockFrameRow));
        
    }
    @Test
    public void testIsRowCompleteFalse() {
        List<Frame> mockFrames;//populates with mock 2 frames
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        //pass isframecomplete
        assertTrue(frameRowUtil.isRowComplete(mockFrameRow));
    }
    @Test
    public void testGetTotalScoreEmptyRow() {
        List<Frame> mockFrames;//dont populate
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        assertEquals(0, frameRowUtil.getTotalScore(mockFrameRow));
    }
    @Test
    public void testGetTotalScorePartialRow() {
        List<Frame> mockFrames;//partially populate
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        assertEquals(85, frameRowUtil.getTotalScore(mockFrameRow));
    }
    @Test
    public void testGetTotalScoreCompleteRowPartialTenthFrame() {
        List<Frame> mockFrames;//mostly populate, partial tenth
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        assertEquals(85, frameRowUtil.getTotalScore(mockFrameRow));
    }
    @Test
    public void testGetTotalScoreCompleteRowFullTenthFrame() {
        List<Frame> mockFrames;//fully populate
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        assertEquals(85, frameRowUtil.getTotalScore(mockFrameRow));
    }
    @Test(expected = IllegalStateException.class)
    public void testStartNewFrameRowAlreadyComplete() {
        List<Frame> mockFrames;//populates fully
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        //pass isframeRowcomplete
        frameRowUtil.startNewFrame(mockFrameRow);
    }
    @Test
    public void testStartNewFrameTenthFrame() {
        List<Frame> mockFrames;//populates 9 frames
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        // isframeRowcomplete not complete
        frameRowUtil.startNewFrame(mockFrameRow);
        assertTrue(mockFrames.get(9) instanceof TenthFrame);
    }
    @Test
    public void testStartNewFrame() {
        List<Frame> mockFrames;//populates 1 frame
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        // isframeRowcomplete not complete
        frameRowUtil.startNewFrame(mockFrameRow);
        assertTrue(mockFrames.get(1) instanceof RegularFrame);
    }
    @Test(expected = IllegalStateException.class)
    public void testPlayerRollFrameAlreadyComplete() {
        List<Frame> mockFrames;//populates fully , use active index
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        frameRowUtil.playerRoll(mockFrameRow, 2);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPlayerRollInvalidInput() {
        List<Frame> mockFrames;//populates 1 frame
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        frameRowUtil.playerRoll(mockFrameRow, 12);
    }
    @Test
    public void testPlayerRollHappyPath() {
        List<Frame> mockFrames;//populates 1 frame
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        frameRowUtil.playerRoll(mockFrameRow, 8);
    }
}
