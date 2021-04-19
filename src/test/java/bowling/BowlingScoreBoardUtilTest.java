package bowling;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import exceptions.BowlingScoreBoardException;
import pojos.BowlingScoreBoard;
import pojos.Player;

public class BowlingScoreBoardUtilTest {
    private BowlingScoreBoardUtil bowlingScoreBoardUtil;
    @Mock
    private BowlingScoreBoard mockBowlingScoreBoard;

    @Before
    public void setup() {
        bowlingScoreBoardUtil = new BowlingScoreBoardUtil();
        mockBowlingScoreBoard = Mockito.mock(BowlingScoreBoard.class);
        when(mockBowlingScoreBoard.getMaxPlayers()).thenReturn(1);
    }

    @After
    public void tearDown() {
        bowlingScoreBoardUtil = null;
        mockBowlingScoreBoard = null;
    }
    @Test
    public void testIsGameCompleteTrue() {
        List<Player> mockPlayers; //populate 1
        //mockplayer row complete
        assertTrue(bowlingScoreBoardUtil.isGameComplete(mockBowlingScoreBoard));
    }
    @Test
    public void testIsGameCompleteFalse() {
        List<Player> mockPlayers; //populate 1
        //mockplayer row not complete
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
        //populate 1 player
        bowlingScoreBoardUtil.addPlayer(mockBowlingScoreBoard, "Fred");
    }
    @Test
    public void testAddPlayer() {
        bowlingScoreBoardUtil.addPlayer(mockBowlingScoreBoard, "Fred");
        //verify
    }
    @Test(expected = BowlingScoreBoardException.class)
    public void testInputRollInvalidEntry() {
        // TODO
    }
    @Test(expected = BowlingScoreBoardException.class)
    public void testInputRollFrameComplete() {
        // TODO
    }
    @Test
    public void testInputRollFrameCompleteTenth() {
        // TODO
    }

}
