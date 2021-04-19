package bowling;

import org.junit.After;
import org.junit.Before;
import org.mockito.*;
import pojos.BowlingScoreBoard;

public class BowlingScoreBoardUtilTest {
    private BowlingScoreBoardUtil bowlingScoreBoardUtil;
    @Mock
    private BowlingScoreBoard mockBowlingScoreBoard;

    @Before
    public void setup() {
        bowlingScoreBoardUtil = new BowlingScoreBoardUtil();
        mockBowlingScoreBoard = Mockito.mock(BowlingScoreBoard.class);
    }

    @After
    public void tearDown() {
        bowlingScoreBoardUtil = null;
        mockBowlingScoreBoard = null;
    }

    public void testIsGameCompleteTrue() {
        // TODO
    }

    public void testIsGameCompleteFalse() {
        // TODO
    }

    public void testAddPlayerNameBlank() {
        // TODO
    }

    public void testAddPlayerMaxPlayers() {
        // TODO
    }

    public void testAddPlayer() {
        // TODO
    }

    public void testInputRollInvalidEntry() {
        // TODO
    }

    public void testInputRollFrameComplete() {
        // TODO
    }

    public void testInputRollFrameCompleteTenth() {
        // TODO
    }

}
