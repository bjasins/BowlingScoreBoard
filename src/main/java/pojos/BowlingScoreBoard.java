package pojos;

import java.util.Collections;
import java.util.List;

public class BowlingScoreBoard {
    private static final int MAX_PLAYERS = 4;
    private final List<Player> players;
    private int playerTurnIndex;
    
    public BowlingScoreBoard() {
        players = Collections.emptyList(); // populate?
    }

    public int getMaxPlayers() {
        return MAX_PLAYERS;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public int getPlayerTurnIndex() {
        return playerTurnIndex;
    }
    public void resetPlayerTurnIndex() {
        playerTurnIndex = 0;
    }
    public Player getCurrentPlayer() {
        return players.get(playerTurnIndex);
    }
    public void addPlayer(Player player) {
        players.add(player);
    }
    public void changeTurn() {
        if (playerTurnIndex == players.size() - 1) {
            playerTurnIndex = 0;
        } else {
            playerTurnIndex++;
        }
    }
}
