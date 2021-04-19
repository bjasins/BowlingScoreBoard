package bowling;

import exceptions.BowlingScoreBoardException;
import interfaces.Frame;
import pojos.BowlingScoreBoard;
import pojos.Player;
import pojos.RegularFrame;
import pojos.TenthFrame;

/**
 * The interactive layer of the bowling scoreboard where basic input options are
 * made publicly available.
 */
public class BowlingScoreBoardUtil {

    private static final FrameRowUtil FRAME_ROW_UTIL = new FrameRowUtil();

    /**
     * @return a boolean indicating if all players have completed their frame rows.
     */
    public final boolean isGameComplete(BowlingScoreBoard bowlingScoreBoared) {
        for (Player player : bowlingScoreBoared.getPlayers()) {
            if (!FRAME_ROW_UTIL.isRowComplete(player.getPlayerFrames())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add a player to the game.
     * 
     * @param playerName the name of the player.
     */
    public void addPlayer(BowlingScoreBoard bowlingScoreBoared, String playerName) throws IllegalArgumentException, IllegalStateException {
        if (playerName == null || playerName.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be blank");
        }
        if (bowlingScoreBoared.getPlayers().size() == bowlingScoreBoared.getMaxPlayers()) {
            throw new IllegalStateException("Maximum of " + bowlingScoreBoared.getMaxPlayers() + " players allowed");
        }
        Player newPlayer = new Player(playerName);
        bowlingScoreBoared.addPlayer(newPlayer);

        if (bowlingScoreBoared.getPlayers().size() == 1) {
            bowlingScoreBoared.resetPlayerTurnIndex();
        }
    }

    /**
     * Input the result of a roll belonging to the current player.
     * 
     * @param rollResult an int representing the score of the roll.
     */
    public void inputRoll(BowlingScoreBoard bowlingScoreBoared, int rollResult) throws BowlingScoreBoardException {
        // check valid
        try {
            Player currentPlayer = bowlingScoreBoared.getCurrentPlayer();
            FRAME_ROW_UTIL.playerRoll(currentPlayer.getPlayerFrames(), rollResult);
            Frame frame = currentPlayer.getPlayerFrames().getCurrentFrame();
            if (frame instanceof TenthFrame) {
                TenthFrameUtil tenthFrameUtil = new TenthFrameUtil();
                if (tenthFrameUtil.isFrameComplete((TenthFrame)(frame))) {
                    if (FRAME_ROW_UTIL.getSize(currentPlayer.getPlayerFrames()) < 10) {
                        FRAME_ROW_UTIL.startNewFrame(currentPlayer.getPlayerFrames());
                    }
                    bowlingScoreBoared.changeTurn();
                }
            }
            else {
                RegularFrameUtil regularFrameUtil = new RegularFrameUtil();
                if (regularFrameUtil.isFrameComplete((RegularFrame)(frame))) {
                    if (FRAME_ROW_UTIL.getSize(currentPlayer.getPlayerFrames()) < 10) {
                        FRAME_ROW_UTIL.startNewFrame(currentPlayer.getPlayerFrames());
                    }
                    bowlingScoreBoared.changeTurn();
                }
            }

        } catch (RuntimeException e) {
            throw new BowlingScoreBoardException("Exception encountered while processing roll");
        }
    }
}
