package bowling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private static final int NUMBER_OF_REGULAR_FRAMES=9;
    private FrameRowUtil frameRowUtil;
    @Mock
    private FrameRow mockFrameRow;
    @Mock
    private RegularFrameUtil mockRegularFrameUtil;
    @Mock
    private TenthFrameUtil mockTenthFrameUtil;

    @Before
    public void setup() {
        mockRegularFrameUtil = Mockito.mock(RegularFrameUtil.class);
        mockTenthFrameUtil = Mockito.mock(TenthFrameUtil.class);
        mockFrameRow = Mockito.mock(FrameRow.class);
        frameRowUtil = new FrameRowUtil(mockRegularFrameUtil, mockTenthFrameUtil);

    }

    @After
    public void tearDown() {
        frameRowUtil = null;
        mockFrameRow = null;
        mockRegularFrameUtil = null;
        mockTenthFrameUtil = null;
    }

    @Test
    public void testIsRowCompleteTrue() {
        List<Frame> mockFrames = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_REGULAR_FRAMES; i++) {
            mockFrames.add(null);
        }
        TenthFrame mockFrame = Mockito.mock(TenthFrame.class);
        mockFrames.add(mockFrame);
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        when(mockTenthFrameUtil.isFrameComplete(mockFrame)).thenReturn(true);
        assertTrue(frameRowUtil.isRowComplete(mockFrameRow));

    }

    @Test
    public void testIsRowCompleteFalse() {
        List<Frame> mockFrames = Collections.emptyList();
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        assertFalse(frameRowUtil.isRowComplete(mockFrameRow));
    }

    @Test
    public void testIsRowCompleteFalseIncompleteTenth() {
        List<Frame> mockFrames = new ArrayList<>(10);
        for (int i = 0; i < NUMBER_OF_REGULAR_FRAMES; i++) {
            mockFrames.add(null);
        }
        TenthFrame mockFrame = Mockito.mock(TenthFrame.class);
        mockFrames.add(mockFrame);
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        when(mockTenthFrameUtil.isFrameComplete(mockFrame)).thenReturn(false);
        assertFalse(frameRowUtil.isRowComplete(mockFrameRow));
    }

    @Test
    public void testGetTotalScoreEmptyRow() {
        List<Frame> mockFrames = Collections.emptyList();
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        assertEquals(0, frameRowUtil.getTotalScore(mockFrameRow));
    }

    @Test
    public void testGetTotalScorePartialRowNoSparesNoStrikes() {

        List<Frame> mockFrames = new ArrayList<>();

        RegularFrame mockFirstFrame = Mockito.mock(RegularFrame.class);
        int firstFrameScore = 8;
        when(mockRegularFrameUtil.getFinalFrameScore(mockFirstFrame)).thenReturn(firstFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockFirstFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockFirstFrame)).thenReturn(false);
        mockFrames.add(mockFirstFrame);

        RegularFrame mockSecondFrame = Mockito.mock(RegularFrame.class);
        int secondFrameScore = 4;
        when(mockRegularFrameUtil.getFinalFrameScore(mockSecondFrame)).thenReturn(secondFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockSecondFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockSecondFrame)).thenReturn(false);
        mockFrames.add(mockSecondFrame);

        RegularFrame mockThirdFrame = Mockito.mock(RegularFrame.class);
        int thirdFrameScore = 5;
        when(mockRegularFrameUtil.getFinalFrameScore(mockThirdFrame)).thenReturn(thirdFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockThirdFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockThirdFrame)).thenReturn(false);
        mockFrames.add(mockThirdFrame);

        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        int finalExpectedScore = firstFrameScore + secondFrameScore + thirdFrameScore;
        assertEquals(finalExpectedScore, frameRowUtil.getTotalScore(mockFrameRow));
    }

    @Test
    public void testGetTotalScorePartialRowWithSparesandStrikes() {

        List<Frame> mockFrames = new ArrayList<>();

        RegularFrame mockFirstFrame = Mockito.mock(RegularFrame.class);
        int firstFrameScore = 10;
        when(mockRegularFrameUtil.getFinalFrameScore(mockFirstFrame)).thenReturn(firstFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockFirstFrame)).thenReturn(true);
        when(mockRegularFrameUtil.isFrameSpare(mockFirstFrame)).thenReturn(false);
        mockFrames.add(mockFirstFrame);

        RegularFrame mockSecondFrame = Mockito.mock(RegularFrame.class);
        int secondFrameScore = 9;
        when(mockRegularFrameUtil.getFinalFrameScore(mockSecondFrame)).thenReturn(secondFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockSecondFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockSecondFrame)).thenReturn(false);
        mockFrames.add(mockSecondFrame);

        RegularFrame mockThirdFrame = Mockito.mock(RegularFrame.class);
        int thirdFrameScore = 10;
        when(mockRegularFrameUtil.getFinalFrameScore(mockThirdFrame)).thenReturn(thirdFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockThirdFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockThirdFrame)).thenReturn(true);
        mockFrames.add(mockThirdFrame);

        RegularFrame mockFourthFrame = Mockito.mock(RegularFrame.class);
        int fourthFrameScore = 6;
        when(mockRegularFrameUtil.getFinalFrameScore(mockFourthFrame)).thenReturn(fourthFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockFourthFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockFourthFrame)).thenReturn(false);
        int fourthFrameRoll = 3;
        when(mockFourthFrame.getRollResultList()).thenReturn(Arrays.asList(fourthFrameRoll, fourthFrameRoll));
        mockFrames.add(mockFourthFrame);

        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        int finalExpectedScore = (firstFrameScore+secondFrameScore) + secondFrameScore + (thirdFrameScore + fourthFrameRoll) + fourthFrameScore;
        assertEquals(finalExpectedScore, frameRowUtil.getTotalScore(mockFrameRow));
    }

    @Test
    public void testGetTotalScoreCompleteRowTenthFrameSpare() {
        List<Frame> mockFrames = new ArrayList<>();

        RegularFrame mockFirstFrame = Mockito.mock(RegularFrame.class);
        int firstFrameScore = 10;
        when(mockRegularFrameUtil.getFinalFrameScore(mockFirstFrame)).thenReturn(firstFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockFirstFrame)).thenReturn(true);
        when(mockRegularFrameUtil.isFrameSpare(mockFirstFrame)).thenReturn(false);
        mockFrames.add(mockFirstFrame);

        RegularFrame mockSecondFrame = Mockito.mock(RegularFrame.class);
        int secondFrameScore = 9;
        when(mockRegularFrameUtil.getFinalFrameScore(mockSecondFrame)).thenReturn(secondFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockSecondFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockSecondFrame)).thenReturn(false);
        mockFrames.add(mockSecondFrame);

        RegularFrame mockThirdFrame = Mockito.mock(RegularFrame.class);
        int thirdFrameScore = 10;
        when(mockRegularFrameUtil.getFinalFrameScore(mockThirdFrame)).thenReturn(thirdFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockThirdFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockThirdFrame)).thenReturn(true);
        mockFrames.add(mockThirdFrame);

        RegularFrame mockFourthFrame = Mockito.mock(RegularFrame.class);
        int fourthFrameScore = 6;
        when(mockRegularFrameUtil.getFinalFrameScore(mockFourthFrame)).thenReturn(fourthFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockFourthFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockFourthFrame)).thenReturn(false);
        int fourthFrameRoll = 3;
        when(mockFourthFrame.getRollResultList()).thenReturn(Arrays.asList(fourthFrameRoll, fourthFrameRoll));
        mockFrames.add(mockFourthFrame);

        RegularFrame mockFifthFrame = Mockito.mock(RegularFrame.class);
        int fifthFrameScore = 5;
        when(mockRegularFrameUtil.getFinalFrameScore(mockFifthFrame)).thenReturn(fifthFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockFifthFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockFifthFrame)).thenReturn(false);
        mockFrames.add(mockFifthFrame);

        RegularFrame mockSixthFrame = Mockito.mock(RegularFrame.class);
        int sixthFrameScore = 5;
        when(mockRegularFrameUtil.getFinalFrameScore(mockSixthFrame)).thenReturn(sixthFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockSixthFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockSixthFrame)).thenReturn(false);
        mockFrames.add(mockSixthFrame);

        RegularFrame mockSeventhFrame = Mockito.mock(RegularFrame.class);
        int seventhFrameScore = 5;
        when(mockRegularFrameUtil.getFinalFrameScore(mockSeventhFrame)).thenReturn(seventhFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockSeventhFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockSeventhFrame)).thenReturn(false);
        mockFrames.add(mockSeventhFrame);

        RegularFrame mockEighthFrame = Mockito.mock(RegularFrame.class);
        int eighthFrameScore = 5;
        when(mockRegularFrameUtil.getFinalFrameScore(mockEighthFrame)).thenReturn(eighthFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockEighthFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockEighthFrame)).thenReturn(false);
        mockFrames.add(mockEighthFrame);

        RegularFrame mockNinthFrame = Mockito.mock(RegularFrame.class);
        int ninthFrameScore=10;
        when(mockRegularFrameUtil.getFinalFrameScore(mockNinthFrame)).thenReturn(ninthFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockNinthFrame)).thenReturn(true);
        when(mockRegularFrameUtil.isFrameSpare(mockNinthFrame)).thenReturn(false);
        mockFrames.add(mockNinthFrame);

        TenthFrame mockTenthFrame = Mockito.mock(TenthFrame.class);
        int tenthFrameScore = 14;
        when(mockTenthFrameUtil.getFinalFrameScore(mockTenthFrame)).thenReturn(tenthFrameScore);
        when(mockTenthFrameUtil.isFrameStrike(mockTenthFrame)).thenReturn(false);
        when(mockTenthFrameUtil.isFrameSpare(mockTenthFrame)).thenReturn(true);
        int tenthFrameRollOne = 7;
        int tenthFrameRollTwo = 3;
        when(mockTenthFrame.getRollResultList()).thenReturn(Arrays.asList(tenthFrameRollOne, tenthFrameRollTwo, 4));
        mockFrames.add(mockTenthFrame);

        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        int finalExpectedScore = (firstFrameScore+secondFrameScore) + secondFrameScore + (thirdFrameScore + fourthFrameRoll) + fourthFrameScore
            + fifthFrameScore + sixthFrameScore + seventhFrameScore + eighthFrameScore + (ninthFrameScore+tenthFrameRollOne+tenthFrameRollTwo)+tenthFrameScore;
        assertEquals(finalExpectedScore, frameRowUtil.getTotalScore(mockFrameRow));
    }

    @Test
    public void testGetTotalScoreCompleteRowTenthFrameStrikes() {
        List<Frame> mockFrames = new ArrayList<>();

        RegularFrame mockFirstFrame = Mockito.mock(RegularFrame.class);
        int firstFrameScore = 10;
        when(mockRegularFrameUtil.getFinalFrameScore(mockFirstFrame)).thenReturn(firstFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockFirstFrame)).thenReturn(true);
        when(mockRegularFrameUtil.isFrameSpare(mockFirstFrame)).thenReturn(false);
        mockFrames.add(mockFirstFrame);

        RegularFrame mockSecondFrame = Mockito.mock(RegularFrame.class);
        int secondFrameScore = 9;
        when(mockRegularFrameUtil.getFinalFrameScore(mockSecondFrame)).thenReturn(secondFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockSecondFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockSecondFrame)).thenReturn(false);
        mockFrames.add(mockSecondFrame);

        RegularFrame mockThirdFrame = Mockito.mock(RegularFrame.class);
        int thirdFrameScore = 10;
        when(mockRegularFrameUtil.getFinalFrameScore(mockThirdFrame)).thenReturn(thirdFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockThirdFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockThirdFrame)).thenReturn(true);
        mockFrames.add(mockThirdFrame);

        RegularFrame mockFourthFrame = Mockito.mock(RegularFrame.class);
        int fourthFrameScore = 6;
        when(mockRegularFrameUtil.getFinalFrameScore(mockFourthFrame)).thenReturn(fourthFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockFourthFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockFourthFrame)).thenReturn(false);
        int fourthFrameRoll = 3;
        when(mockFourthFrame.getRollResultList()).thenReturn(Arrays.asList(fourthFrameRoll, fourthFrameRoll));
        mockFrames.add(mockFourthFrame);

        RegularFrame mockFifthFrame = Mockito.mock(RegularFrame.class);
        int fifthFrameScore = 5;
        when(mockRegularFrameUtil.getFinalFrameScore(mockFifthFrame)).thenReturn(fifthFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockFifthFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockFifthFrame)).thenReturn(false);
        mockFrames.add(mockFifthFrame);

        RegularFrame mockSixthFrame = Mockito.mock(RegularFrame.class);
        int sixthFrameScore = 5;
        when(mockRegularFrameUtil.getFinalFrameScore(mockSixthFrame)).thenReturn(sixthFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockSixthFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockSixthFrame)).thenReturn(false);
        mockFrames.add(mockSixthFrame);

        RegularFrame mockSeventhFrame = Mockito.mock(RegularFrame.class);
        int seventhFrameScore = 5;
        when(mockRegularFrameUtil.getFinalFrameScore(mockSeventhFrame)).thenReturn(seventhFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockSeventhFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockSeventhFrame)).thenReturn(false);
        mockFrames.add(mockSeventhFrame);

        RegularFrame mockEighthFrame = Mockito.mock(RegularFrame.class);
        int eighthFrameScore = 5;
        when(mockRegularFrameUtil.getFinalFrameScore(mockEighthFrame)).thenReturn(eighthFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockEighthFrame)).thenReturn(false);
        when(mockRegularFrameUtil.isFrameSpare(mockEighthFrame)).thenReturn(false);
        mockFrames.add(mockEighthFrame);

        RegularFrame mockNinthFrame = Mockito.mock(RegularFrame.class);
        int ninthFrameScore = 10;
        when(mockRegularFrameUtil.getFinalFrameScore(mockNinthFrame)).thenReturn(ninthFrameScore);
        when(mockRegularFrameUtil.isFrameStrike(mockNinthFrame)).thenReturn(true);
        when(mockRegularFrameUtil.isFrameSpare(mockNinthFrame)).thenReturn(false);
        mockFrames.add(mockNinthFrame);

        TenthFrame mockTenthFrame = Mockito.mock(TenthFrame.class);
        int tenthFrameScore = 30;
        when(mockTenthFrameUtil.getFinalFrameScore(mockTenthFrame)).thenReturn(tenthFrameScore);
        when(mockTenthFrameUtil.isFrameStrike(mockTenthFrame)).thenReturn(false);
        when(mockTenthFrameUtil.isFrameSpare(mockTenthFrame)).thenReturn(true);
        int tenthFrameRoll = 10;
        when(mockTenthFrame.getRollResultList()).thenReturn(Arrays.asList(tenthFrameRoll, tenthFrameRoll, tenthFrameRoll));
        mockFrames.add(mockTenthFrame);

        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        int finalExpectedScore = (firstFrameScore+secondFrameScore) + secondFrameScore + (thirdFrameScore + fourthFrameRoll) + fourthFrameScore
        + fifthFrameScore + sixthFrameScore + seventhFrameScore + eighthFrameScore + (ninthFrameScore+tenthFrameRoll+tenthFrameRoll)+tenthFrameScore;
        assertEquals(finalExpectedScore, frameRowUtil.getTotalScore(mockFrameRow));
    }

    @Test(expected = IllegalStateException.class)
    public void testStartNewFrameRowAlreadyComplete() {
        List<Frame> mockFrames = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_REGULAR_FRAMES; i++) {
            mockFrames.add(null);
        }
        TenthFrame mockFrame = Mockito.mock(TenthFrame.class);
        mockFrames.add(mockFrame);
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        when(mockTenthFrameUtil.isFrameComplete(mockFrame)).thenReturn(true);
        frameRowUtil.startNewFrame(mockFrameRow);
    }

    @Test
    public void testStartNewFrameTenthFrame() {
        List<Frame> mockFrames = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_REGULAR_FRAMES; i++) {
            mockFrames.add(null);
        }
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        frameRowUtil.startNewFrame(mockFrameRow);
        verify(mockFrameRow).addFrame(any(TenthFrame.class));
    }

    @Test
    public void testStartNewFrame() {
        List<Frame> mockFrames = Collections.emptyList();
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        frameRowUtil.startNewFrame(mockFrameRow);
        verify(mockFrameRow).addFrame(any(RegularFrame.class));
    }

    @Test(expected = IllegalStateException.class)
    public void testPlayerRollFrameAlreadyComplete() {
        List<Frame> mockFrames = new ArrayList<>();
        RegularFrame mockRegularFrame = Mockito.mock(RegularFrame.class);
        mockFrames.add(mockRegularFrame);
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        when(mockFrameRow.getActiveFrameIndex()).thenReturn(0);
        when(mockRegularFrameUtil.isFrameComplete(mockRegularFrame)).thenReturn(true);
        frameRowUtil.playerRoll(mockFrameRow, 2);
    }

    @Test(expected = IllegalStateException.class)
    public void testPlayerRollFrameAlreadyCompleteTenth() {
        List<Frame> mockFrames = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_REGULAR_FRAMES; i++) {
            mockFrames.add(null);
        }
        TenthFrame mockTenthFrame = Mockito.mock(TenthFrame.class);
        mockFrames.add(mockTenthFrame);
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        when(mockFrameRow.getActiveFrameIndex()).thenReturn(9);
        when(mockTenthFrameUtil.isFrameComplete(mockTenthFrame)).thenReturn(true);
        frameRowUtil.playerRoll(mockFrameRow, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayerRollInvalidInput() {
        List<Frame> mockFrames = new ArrayList<>();
        RegularFrame mockRegularFrame = Mockito.mock(RegularFrame.class);
        mockFrames.add(mockRegularFrame);
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        when(mockFrameRow.getActiveFrameIndex()).thenReturn(0);
        when(mockRegularFrameUtil.isFrameComplete(mockRegularFrame)).thenReturn(false);
        frameRowUtil.playerRoll(mockFrameRow, 12);
    }

    @Test
    public void testPlayerRollHappyPath() {
        List<Frame> mockFrames = new ArrayList<>();
        RegularFrame mockRegularFrame = Mockito.mock(RegularFrame.class);
        mockFrames.add(mockRegularFrame);
        when(mockFrameRow.getFrames()).thenReturn(mockFrames);
        when(mockFrameRow.getActiveFrameIndex()).thenReturn(0);
        when(mockRegularFrameUtil.isFrameComplete(mockRegularFrame)).thenReturn(false);
        frameRowUtil.playerRoll(mockFrameRow, 6);
        verify(mockRegularFrameUtil).enterRollResult(mockRegularFrame, 6);
    }
}
