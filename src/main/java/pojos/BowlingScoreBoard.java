package pojos;

import java.util.Collections;
import java.util.List;

/**
 * A POJO meant to represent a the players of a bowling game.
 */
public class BowlingScoreBoard {
    private static final int MAX_PLAYERS = 4;
    private final List<Player> players;
    private int playerTurnIndex;

    public BowlingScoreBoard() {
        players = Collections.emptyList(); // populate?
    }

    /**
     * @return an int representing the max number of players allowed in the game.
     */
    public int getMaxPlayers() {
        return MAX_PLAYERS;
    }

    /**
     * @return a List of {@link Player}.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * @return an int representing the index of the player whose turn it currently
     *         is.
     */
    public int getPlayerTurnIndex() {
        return playerTurnIndex;
    }

    /**
     * Increment the active player index.
     */
    public void incrementPlayerTurnIndex() {
        playerTurnIndex++;
    }

    /**
     * Reset the active player index.
     */
    public void resetPlayerTurnIndex() {
        playerTurnIndex = 0;
    }

    /**
     * @return the {@code Player} whose turn it currently is.
     */
    public Player getCurrentPlayer() {
        return players.get(playerTurnIndex);
    }

    /**
     * Add a new player to the list of players.
     * 
     * @param player the {@code Player} to add.
     */
    public void addPlayer(Player player) {
        players.add(player);
    }
}
