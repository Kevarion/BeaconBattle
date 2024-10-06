package net.kevarion.beaconbattle.manager;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class PlayerManager {

    private final Set<Player> players;

    public PlayerManager() {
        this.players = new HashSet<>();
    }

    /**
     * Adds a player to the manager.
     * @param player The player to add.
     */
    public void addPlayer(Player player) {
        players.add(player);
        System.out.println(player.getName() + " has joined the game.");
    }

    /**
     * Removes a player from the manager.
     * @param player The player to remove.
     */
    public void removePlayer(Player player) {
        players.remove(player);
        System.out.println(player.getName() + " has left the game.");
    }

    /**
     * Checks if a player is in the game.
     * @param player The player to check.
     * @return True if the player is in the game, false otherwise.
     */
    public boolean isPlayerInGame(Player player) {
        return players.contains(player);
    }

    /**
     * Gets the set of all players in the game.
     * @return A set of players.
     */
    public Set<Player> getPlayers() {
        return players;
    }

    /**
     * Clears the player list, used during game reset.
     */
    public void clearPlayers() {
        players.clear();
        System.out.println("All players have been cleared from the game.");
    }

}
