package net.kevarion.beaconbattle.game;

import net.kevarion.beaconbattle.game.state.GameState;
import net.kevarion.beaconbattle.game.state.GameStateManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class GameManager {

    private final GameStateManager gameStateManager;
    private final Set<Player> players;

    public GameManager() {
        this.gameStateManager = new GameStateManager();
        this.players = new HashSet<>();
    }

    /**
     * STarts the game by setting the game state to STARTING
     */
    public void startGame() {
        if (gameStateManager.isPregame()) {
            gameStateManager.setGameState(GameState.STARTING);
        }
        //TODO: Implement countdown timer
    }

    /**
     * Begins the active game phase.
     */
    private void beginGame() {
        gameStateManager.setGameState(GameState.ACTIVE);
        for (Player player : players) {
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[!] " + ChatColor.GREEN + "The game has begun!");
            //TODO: Teleport players to the spawn points and arenas.
        }
    }

    /**
     * Stops the game and announces the winner.
     */
    public void stopGame() {
        gameStateManager.setGameState(GameState.ENDING);
    }

    /**
     * Announce the winner of the game.
     */
    private void announceWinner() {
        // TODO: Make win system
        Bukkit.broadcastMessage("The game has ended!");
    }

    /**
     * Resets the game for the next round.
     */
    private void resetGame() {
        gameStateManager.setGameState(GameState.RESETTING);
        //TODO: Reset Arena, teleport players to lobby, open arena back up for use.
        for (Player player : players) {
            // Teleport players back to the lobby or reset their locations
        }
        gameStateManager.setGameState(GameState.PREGAME);
    }

    /**
     * Adds a player to the game.
     * @param player The player to add.
     */
    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
            player.sendMessage("You have joined the game!");
            // Add additional things for when a player joins
        }
    }

    /**
     * Removes a player from the game.
     * @param player The player to remove.
     */
    public void removePlayer(Player player) {
        if (players.contains(player)) {
            players.remove(player);
            player.sendMessage("You have left the game!");
            // Add additional logic for when a player leaves
        }
    }

    /**
     * Get the current list of players in the game.
     * @return Set of players.
     */
    public Set<Player> getPlayers() {
        return players;
    }

}
