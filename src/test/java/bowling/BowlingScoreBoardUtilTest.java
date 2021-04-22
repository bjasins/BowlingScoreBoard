package bowling;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import exceptions.BowlingScoreBoardException;
import pojos.BowlingScoreBoard;
import pojos.FrameRow;
import pojos.Player;
import pojos.RegularFrame;
import pojos.TenthFrame;

public class BowlingScoreBoardUtilTest {
    private BowlingScoreBoardUtil bowlingScoreBoardUtil;
    @Mock
    private BowlingScoreBoard mockBowlingScoreBoard;
    @Mock
    private FrameRowUtil mockFrameRowUtil;

    @Before
    public void setup() {
        mockFrameRowUtil = Mockito.mock(FrameRowUtil.class);
        bowlingScoreBoardUtil = new BowlingScoreBoardUtil(mockFrameRowUtil);
        mockBowlingScoreBoard = Mockito.mock(BowlingScoreBoard.class);
        when(mockBowlingScoreBoard.getMaxPlayers()).thenReturn(1);
    }

    @After
    public void tearDown() {
        bowlingScoreBoardUtil = null;
        mockBowlingScoreBoard = null;
        mockFrameRowUtil = null;
    }
    @Test
    public void testIsGameCompleteTrue() {
        Player mockPlayer = Mockito.mock(Player.class);
        List<Player> mockPlayers = Arrays.asList(mockPlayer);
        FrameRow mockFrameRow = Mockito.mock(FrameRow.class);
        when(mockPlayer.getPlayerFrames()).thenReturn(mockFrameRow);
        when(mockFrameRowUtil.isRowComplete(mockFrameRow)).thenReturn(true);
        when(mockBowlingScoreBoard.getPlayers()).thenReturn(mockPlayers);
        assertTrue(bowlingScoreBoardUtil.isGameComplete(mockBowlingScoreBoard));
    }
    @Test
    public void testIsGameCompleteFalse() {
        Player mockPlayer = Mockito.mock(Player.class);
        List<Player> mockPlayers = Arrays.asList(mockPlayer);
        FrameRow mockFrameRow = Mockito.mock(FrameRow.class);
        when(mockPlayer.getPlayerFrames()).thenReturn(mockFrameRow);
        when(mockFrameRowUtil.isRowComplete(mockFrameRow)).thenReturn(false);
        when(mockBowlingScoreBoard.getPlayers()).thenReturn(mockPlayers);
        assertFalse(bowlingScoreBoardUtil.isGameComplete(mockBowlingScoreBoard));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddPlayerNameNull() {
        bowlingScoreBoardUtil.addPlayer(mockBowlingScoreBoard, null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddPlayerNameBlank() {
        bowlingScoreBoardUtil.addPlayer(mockBowlingScoreBoard, "");
    }
    @Test(expected = IllegalStateException.class)
    public void testAddPlayerMaxPlayers() {
        Player mockPlayer = Mockito.mock(Player.class);
        List<Player> mockPlayers = Arrays.asList(mockPlayer);
        when(mockBowlingScoreBoard.getPlayers()).thenReturn(mockPlayers);
        bowlingScoreBoardUtil.addPlayer(mockBowlingScoreBoard, "Fred");
    }
    @Test
    public void testAddPlayer() {
        Player mockPlayer = Mockito.mock(Player.class);
        when(mockBowlingScoreBoard.getPlayers()).thenReturn(Arrays.asList(mockPlayer));
        when(mockBowlingScoreBoard.getMaxPlayers()).thenReturn(2);
        bowlingScoreBoardUtil.addPlayer(mockBowlingScoreBoard, "Fred");
        verify(mockBowlingScoreBoard).addPlayer(any(Player.class));
        verify(mockBowlingScoreBoard).resetPlayerTurnIndex();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testInputRollInvalidEntry() throws BowlingScoreBoardException {
        bowlingScoreBoardUtil.inputRoll(mockBowlingScoreBoard, 12);
    }
    @Test(expected = BowlingScoreBoardException.class)
    public void testInputRollFrameRowComplete() throws BowlingScoreBoardException {
        Player mockPlayer = Mockito.mock(Player.class);
        when(mockBowlingScoreBoard.getCurrentPlayer()).thenReturn(mockPlayer);
        FrameRow mockFrameRow = Mockito.mock(FrameRow.class);
        when(mockPlayer.getPlayerFrames()).thenReturn(mockFrameRow);
        Mockito.doThrow(new IllegalStateException()).when(mockFrameRowUtil).playerRoll(eq(mockFrameRow), eq(5));
        bowlingScoreBoardUtil.inputRoll(mockBowlingScoreBoard, 5);
    }
    @Test
    public void testInputRollTenthFrameComplete() throws BowlingScoreBoardException {
        Player mockPlayer = Mockito.mock(Player.class);
        when(mockBowlingScoreBoard.getCurrentPlayer()).thenReturn(mockPlayer);
        FrameRow mockFrameRow = Mockito.mock(FrameRow.class);
        when(mockPlayer.getPlayerFrames()).thenReturn(mockFrameRow);
        TenthFrame mockFrame = Mockito.mock(TenthFrame.class);
        when(mockFrameRow.getCurrentFrame()).thenReturn(mockFrame);
        Mockito.doNothing().when(mockFrameRowUtil).playerRoll(eq(mockFrameRow), eq(5));
        TenthFrameUtil mockTenthFrameUtil = Mockito.mock(TenthFrameUtil.class);
        when(mockFrameRowUtil.getTenthFrameUtil()).thenReturn(mockTenthFrameUtil);
        when(mockTenthFrameUtil.isFrameComplete(mockFrame)).thenReturn(true);
        when(mockBowlingScoreBoard.getPlayerTurnIndex()).thenReturn(0);
        when(mockBowlingScoreBoard.getPlayers()).thenReturn(Arrays.asList(mockPlayer));
        bowlingScoreBoardUtil.inputRoll(mockBowlingScoreBoard, 5);
        verify(mockTenthFrameUtil).isFrameComplete(mockFrame);
        verify(mockBowlingScoreBoard).resetPlayerTurnIndex();
    }

    @Test
    public void testInputRollTenthFrameNotComplete() throws BowlingScoreBoardException {
        Player mockPlayer = Mockito.mock(Player.class);
        when(mockBowlingScoreBoard.getCurrentPlayer()).thenReturn(mockPlayer);
        FrameRow mockFrameRow = Mockito.mock(FrameRow.class);
        when(mockPlayer.getPlayerFrames()).thenReturn(mockFrameRow);
        TenthFrame mockFrame = Mockito.mock(TenthFrame.class);
        when(mockFrameRow.getCurrentFrame()).thenReturn(mockFrame);
        Mockito.doNothing().when(mockFrameRowUtil).playerRoll(eq(mockFrameRow), eq(5));
        TenthFrameUtil mockTenthFrameUtil = Mockito.mock(TenthFrameUtil.class);
        when(mockFrameRowUtil.getTenthFrameUtil()).thenReturn(mockTenthFrameUtil);
        when(mockTenthFrameUtil.isFrameComplete(mockFrame)).thenReturn(false);
        bowlingScoreBoardUtil.inputRoll(mockBowlingScoreBoard, 5);
        verify(mockTenthFrameUtil).isFrameComplete(mockFrame);
        verify(mockBowlingScoreBoard, never()).getPlayerTurnIndex();
    }

    @Test
    public void testInputRollRegularFrameComplete() throws BowlingScoreBoardException {
        Player mockPlayer = Mockito.mock(Player.class);
        when(mockBowlingScoreBoard.getCurrentPlayer()).thenReturn(mockPlayer);
        FrameRow mockFrameRow = Mockito.mock(FrameRow.class);
        when(mockPlayer.getPlayerFrames()).thenReturn(mockFrameRow);
        RegularFrame mockFrame = Mockito.mock(RegularFrame.class);
        when(mockFrameRow.getCurrentFrame()).thenReturn(mockFrame);
        Mockito.doNothing().when(mockFrameRowUtil).playerRoll(eq(mockFrameRow), eq(5));

        RegularFrameUtil mockRegularFrameUtil = Mockito.mock(RegularFrameUtil.class);
        when(mockFrameRowUtil.getRegularFrameUtil()).thenReturn(mockRegularFrameUtil);
        when(mockRegularFrameUtil.isFrameComplete(mockFrame)).thenReturn(true);
        Mockito.doNothing().when(mockFrameRowUtil).startNewFrame(mockFrameRow);
        when(mockBowlingScoreBoard.getPlayerTurnIndex()).thenReturn(0);
        when(mockBowlingScoreBoard.getPlayers()).thenReturn(Arrays.asList(mockPlayer));
        bowlingScoreBoardUtil.inputRoll(mockBowlingScoreBoard, 5);
        verify(mockRegularFrameUtil).isFrameComplete(mockFrame);
        verify(mockFrameRowUtil).startNewFrame(mockFrameRow);
        verify(mockBowlingScoreBoard).resetPlayerTurnIndex();
    }

    @Test
    public void testInputRollRegularFrameNotComplete() throws BowlingScoreBoardException {
        Player mockPlayer = Mockito.mock(Player.class);
        when(mockBowlingScoreBoard.getCurrentPlayer()).thenReturn(mockPlayer);
        FrameRow mockFrameRow = Mockito.mock(FrameRow.class);
        when(mockPlayer.getPlayerFrames()).thenReturn(mockFrameRow);
        RegularFrame mockFrame = Mockito.mock(RegularFrame.class);
        when(mockFrameRow.getCurrentFrame()).thenReturn(mockFrame);
        Mockito.doNothing().when(mockFrameRowUtil).playerRoll(eq(mockFrameRow), eq(5));

        RegularFrameUtil mockRegularFrameUtil = Mockito.mock(RegularFrameUtil.class);
        when(mockFrameRowUtil.getRegularFrameUtil()).thenReturn(mockRegularFrameUtil);
        when(mockRegularFrameUtil.isFrameComplete(mockFrame)).thenReturn(false);

        bowlingScoreBoardUtil.inputRoll(mockBowlingScoreBoard, 5);
        verify(mockRegularFrameUtil).isFrameComplete(mockFrame);
        verify(mockFrameRowUtil, never()).startNewFrame(mockFrameRow);
        verify(mockBowlingScoreBoard, never()).resetPlayerTurnIndex();
    }
}
