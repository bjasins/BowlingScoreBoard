package bowling;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
        when(mockTenthFrame.getMaxRollsAllowed()).thenReturn(3);
        when(mockTenthFrame.getNormalRollsAllowed()).thenReturn(2);
        when(mockTenthFrame.getMinimumScoreToUnlockMaxRolls()).thenReturn(10);
    }

    @After
    public void tearDown() {
        tenthFrameUtil = null;
        mockTenthFrame = null;
    }

    @Test
    public void testIsFrameCompleteTrueThreeRolls() {
        List<Integer> mockRollResultList = Arrays.asList(5, 5, 8);
        when(mockTenthFrame.getRollResultList()).thenReturn(mockRollResultList);
        assertTrue(tenthFrameUtil.isFrameComplete(mockTenthFrame));
    }

    @Test
    public void testIsFrameCompleteTrue() {
        List<Integer> mockRollResultList = Arrays.asList(2, 6);
        when(mockTenthFrame.getRollResultList()).thenReturn(mockRollResultList);
        assertTrue(tenthFrameUtil.isFrameComplete(mockTenthFrame));
    }

    @Test
    public void testIsFrameCompleteFalse() {
        List<Integer> mockRollResultList = Arrays.asList(5);
        when(mockTenthFrame.getRollResultList()).thenReturn(mockRollResultList);
        assertFalse(tenthFrameUtil.isFrameComplete(mockTenthFrame));
    }

    @Test
    public void testIsFrameStrikeTrue() {
        List<Integer> mockRollResultList = Arrays.asList(10);
        when(mockTenthFrame.getRollResultList()).thenReturn(mockRollResultList);
        assertTrue(tenthFrameUtil.isFrameStrike(mockTenthFrame));
    }

    @Test
    public void testIsFrameStrikeFalse() {
        List<Integer> mockRollResultList = Arrays.asList(3, 2);
        when(mockTenthFrame.getRollResultList()).thenReturn(mockRollResultList);
        assertFalse(tenthFrameUtil.isFrameStrike(mockTenthFrame));
    }

    @Test
    public void testIsFrameSpareTrue() {
        List<Integer> mockRollResultList = Arrays.asList(3, 7);
        when(mockTenthFrame.getRollResultList()).thenReturn(mockRollResultList);
        assertTrue(tenthFrameUtil.isFrameSpare(mockTenthFrame));
    }

    @Test
    public void testIsFrameSpareFalse() {
        List<Integer> mockRollResultList = Arrays.asList(3, 2);
        when(mockTenthFrame.getRollResultList()).thenReturn(mockRollResultList);
        assertFalse(tenthFrameUtil.isFrameSpare(mockTenthFrame));
    }

    @Test(expected = IllegalStateException.class)
    public void testEnterRollResultFrameCompleteError() {
        List<Integer> mockRollResultList = Arrays.asList(2, 6);
        when(mockTenthFrame.getRollResultList()).thenReturn(mockRollResultList);
        tenthFrameUtil.enterRollResult(mockTenthFrame, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEnterRollResultInvalidScore() {
        List<Integer> mockRollResultList = Arrays.asList(2);
        when(mockTenthFrame.getRollResultList()).thenReturn(mockRollResultList);
        tenthFrameUtil.enterRollResult(mockTenthFrame, 12);
    }

    @Test
    public void testEnterRollResultHappyPath() {
        List<Integer> mockRollResultList = new ArrayList<>(Arrays.asList(4, 6));
        when(mockTenthFrame.getRollResultList()).thenReturn(mockRollResultList);
        tenthFrameUtil.enterRollResult(mockTenthFrame, 4);
    }
}
