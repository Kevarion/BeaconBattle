package net.kevarion.beaconbattle.game;

import net.kevarion.beaconbattle.game.state.GameState;
import net.kevarion.beaconbattle.game.state.GameStateManager;
import net.kevarion.beaconbattle.arena.Arena;
import net.kevarion.beaconbattle.manager.PlayerManager;
import net.kevarion.beaconbattle.team.TeamManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameManager {

    private GameStateManager gameStateManager;
    private Arena arena;
    private TeamManager teamManager;
    private PlayerManager playerManager;
    private Game game;

    public GameManager(Arena arena) {
        this.arena = arena;
        this.gameStateManager = new GameStateManager();
        List<Location> spawnLocations = new ArrayList<>();
        this.teamManager = new TeamManager(spawnLocations);
        this.playerManager = new PlayerManager();
        this.game = new Game(this, arena); // Initialize Game here
    }

    public void initializeGame() {
        game.initializeGame(); // Call the Game's initialization
    }

    public void startGame() {
        game.startGame(); // Start the game through the Game class
    }

    public void endGame() {
        game.endGame(); // End the game through the Game class
    }

    public void onBeaconDestroyed(String teamColor) {
        // Logic for beacon destruction
    }

    public void onPlayerDeath(Player player) {
        // Logic for handling player death
    }

    public void checkWinCondition() {
        // Logic for checking win condition
    }

    public void resetGame() {
        game.resetGame(); // Reset the game through the Game class
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public Arena getArena() {
        return arena;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public Set<Player> getPlayers() {
        return playerManager.getPlayers(); // Assuming PlayerManager has a getPlayers method
    }
}
