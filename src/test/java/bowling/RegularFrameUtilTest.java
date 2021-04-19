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

import pojos.RegularFrame;

public class RegularFrameUtilTest {
    private RegularFrameUtil regularFrameUtil;
    @Mock
    private RegularFrame mockRegularFrame;

    @Before
    public void setup() {
        regularFrameUtil = new RegularFrameUtil();
        mockRegularFrame = Mockito.mock(RegularFrame.class);
        when(mockRegularFrame.getMaxRollsAllowed()).thenReturn(2);
    }

    @After
    public void tearDown() {
        regularFrameUtil = null;
        mockRegularFrame = null;
    }
    @Test
    public void testIsFrameCompleteTrue() {
        List<Integer> mockRollResultList = Arrays.asList(5, 5);
        when(mockRegularFrame.getRollResultList()).thenReturn(mockRollResultList);
        assertTrue(regularFrameUtil.isFrameComplete(mockRegularFrame));
    }
    @Test
    public void testIsFrameCompleteTrueStrike() {
        List<Integer> mockRollResultList = Arrays.asList(10);
        when(mockRegularFrame.getRollResultList()).thenReturn(mockRollResultList);
        assertTrue(regularFrameUtil.isFrameComplete(mockRegularFrame));
    }
    @Test
    public void testIsFrameCompleteFalse() {
        List<Integer> mockRollResultList = Arrays.asList(2);
        when(mockRegularFrame.getRollResultList()).thenReturn(mockRollResultList);
        assertFalse(regularFrameUtil.isFrameComplete(mockRegularFrame));
    }
    @Test
    public void testIsFrameStrikeTrue() {
        List<Integer> mockRollResultList = Arrays.asList(10);
        when(mockRegularFrame.getRollResultList()).thenReturn(mockRollResultList);
        assertTrue(regularFrameUtil.isFrameStrike(mockRegularFrame));
    }
    @Test
    public void testIsFrameStrikeFalse() {
        List<Integer> mockRollResultList = Arrays.asList(2,7);
        when(mockRegularFrame.getRollResultList()).thenReturn(mockRollResultList);
        assertFalse((regularFrameUtil.isFrameStrike(mockRegularFrame)));
    }
    @Test
    public void testIsFrameSpareTrue() {
        List<Integer> mockRollResultList = Arrays.asList(4,6);
        when(mockRegularFrame.getRollResultList()).thenReturn(mockRollResultList);
        assertTrue(regularFrameUtil.isFrameSpare(mockRegularFrame));
    }
    @Test
    public void testIsFrameSpareFalse() {
        List<Integer> mockRollResultList = Arrays.asList(4,3);
        when(mockRegularFrame.getRollResultList()).thenReturn(mockRollResultList);
        assertFalse(regularFrameUtil.isFrameSpare(mockRegularFrame));
    }
    @Test(expected = IllegalStateException.class)
    public void testEnterRollResultFrameCompleteError() {
        List<Integer> mockRollResultList = Arrays.asList(2, 6);
        when(mockRegularFrame.getRollResultList()).thenReturn(mockRollResultList);
        regularFrameUtil.enterRollResult(mockRegularFrame, 4);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testEnterRollResultInvalidScore() {
        List<Integer> mockRollResultList = Arrays.asList(6);
        when(mockRegularFrame.getRollResultList()).thenReturn(mockRollResultList);
        regularFrameUtil.enterRollResult(mockRegularFrame, 12);
    }
    @Test
    public void testEnterRollResultHappyPath() {
        List<Integer> mockRollResultList = new ArrayList<>(Arrays.asList(6));
        when(mockRegularFrame.getRollResultList()).thenReturn(mockRollResultList);
        regularFrameUtil.enterRollResult(mockRegularFrame, 3);
    }
}
