package bowling;

import java.util.Collections;
import java.util.List;

import exceptions.BowlingScoreBoardException;

/**
 * The interactive layer of the bowling scoreboard where basic input options are
 * made publicly available.
 */
public class BowlingScoreBoared {
    private static final int MAX_PLAYERS = 4;
    private final List<Player> players;
    private int playerTurnIndex;

    BowlingScoreBoared() {
        players = Collections.emptyList(); // populate?
    }

    /**
     * @return a boolean indicating if all players have completed their frame rows.
     */
    public final boolean isGameComplete() {
        for (Player player : players) {
            if (!player.getPlayerFrames().isRowComplete()) {
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
    public void addPlayer(String playerName) throws IllegalArgumentException, IllegalStateException {
        if (playerName == null || playerName.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be blank");
        }
        if (players.size() == MAX_PLAYERS) {
            throw new IllegalStateException("Maximum of " + MAX_PLAYERS + " players allowed");
        }
        Player newPlayer = new Player(playerName);
        players.add(newPlayer);

        if (players.size() == 1) {
            playerTurnIndex = 0;
        }
    }

    /**
     * Input the result of a roll belonging to the current player.
     * 
     * @param rollResult an int representing the score of the roll.
     */
    public void inputRoll(int rollResult) throws BowlingScoreBoardException {
        // check valid
        try {
            Player currentPlayer = getCurrentPlayer();
            currentPlayer.getPlayerFrames().playerRoll(rollResult);

            if (currentPlayer.getPlayerFrames().getCurrentFrame().isFrameComplete()) {
                if (currentPlayer.getPlayerFrames().getSize() < 10) {
                    currentPlayer.getPlayerFrames().startNewFrame();
                }
                changeTurn();
            }
        } catch (RuntimeException e) {
            throw new BowlingScoreBoardException("Exception encountered while processing roll");
        }
    }

    /**
     * @return the {@link Player} whose turn it currently is.
     */
    private Player getCurrentPlayer() {
        return players.get(playerTurnIndex);
    }

    /**
     * Changes the turn to the next player.
     */
    private void changeTurn() {
        if (playerTurnIndex == players.size() - 1) {
            playerTurnIndex = 0;
        } else {
            playerTurnIndex++;
        }
    }

}
