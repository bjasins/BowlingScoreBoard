package bowling;

import exceptions.BowlingScoreBoardException;
import interfaces.Frame;
import pojos.BowlingScoreBoard;
import pojos.Player;
import pojos.RegularFrame;
import pojos.TenthFrame;

/**
 * Provides the functionality for interacting with a {@link BowlingScoreBoard}.
 */
public class BowlingScoreBoardUtil {

    private static final FrameRowUtil FRAME_ROW_UTIL = new FrameRowUtil();

    /**
     * @param bowlingScoreBoared the {@link BowlingScoreBoard} to evaluate.
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
     * @param bowlingScoreBoared the {@link BowlingScoreBoard} to evaluate.
     * @param playerName         the name of the player.
     * @throws {@code IllegalArgumentException} when invalid player name provided.
     * @throws {@code IllegalStateException} when the max players allowed are
     * already present.
     */
    public void addPlayer(BowlingScoreBoard bowlingScoreBoared, String playerName)
            throws IllegalArgumentException, IllegalStateException {
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
     * @param bowlingScoreBoared the {@link BowlingScoreBoard} to evaluate.
     * @param rollResult         an int representing the score of the roll.
     * @throws {@link BowlingScoreBoardException} if an error occurs during
     *                processing.
     */
    public void inputRoll(BowlingScoreBoard bowlingScoreBoared, int rollResult) throws BowlingScoreBoardException {
        // check valid
        try {
            Player currentPlayer = bowlingScoreBoared.getCurrentPlayer();
            FRAME_ROW_UTIL.playerRoll(currentPlayer.getPlayerFrames(), rollResult);
            Frame frame = currentPlayer.getPlayerFrames().getCurrentFrame();
            if (frame instanceof TenthFrame) {
                TenthFrameUtil tenthFrameUtil = new TenthFrameUtil();
                if (tenthFrameUtil.isFrameComplete((TenthFrame) (frame))) {
                    if (FRAME_ROW_UTIL.getSize(currentPlayer.getPlayerFrames()) < 10) {
                        FRAME_ROW_UTIL.startNewFrame(currentPlayer.getPlayerFrames());
                    }
                    changeTurn(bowlingScoreBoared);
                }
            } else {
                RegularFrameUtil regularFrameUtil = new RegularFrameUtil();
                if (regularFrameUtil.isFrameComplete((RegularFrame) (frame))) {
                    if (FRAME_ROW_UTIL.getSize(currentPlayer.getPlayerFrames()) < 10) {
                        FRAME_ROW_UTIL.startNewFrame(currentPlayer.getPlayerFrames());
                    }
                    changeTurn(bowlingScoreBoared);
                }
            }

        } catch (RuntimeException e) {
            throw new BowlingScoreBoardException("Exception encountered while processing roll");
        }
    }

    /**
     * Rotate the responsibility of active player.
     * 
     * @param bowlingScoreBoared the {@link BowlingScoreBoard} to evaluate.
     */
    private void changeTurn(BowlingScoreBoard bowlingScoreBoard) {
        if (bowlingScoreBoard.getPlayerTurnIndex() == bowlingScoreBoard.getPlayers().size() - 1) {
            bowlingScoreBoard.resetPlayerTurnIndex();
        } else {
            bowlingScoreBoard.incrementPlayerTurnIndex();
        }
    }
}
