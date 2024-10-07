package net.kevarion.beaconbattle.game;

import net.kevarion.beaconbattle.BeaconBattle;
import net.kevarion.beaconbattle.game.beacon.Beacon;
import net.kevarion.beaconbattle.game.state.GameState;
import net.kevarion.beaconbattle.game.state.GameStateManager;
import net.kevarion.beaconbattle.arena.Arena;
import net.kevarion.beaconbattle.manager.PlayerManager;
import net.kevarion.beaconbattle.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameManager {

    private final BeaconBattle instance;
    private GameStateManager gameStateManager;
    private Arena arena;
    private TeamManager teamManager;
    private PlayerManager playerManager;
    private Game game;

    public GameManager(BeaconBattle instance, Arena arena) {
        this.instance = instance;
        this.arena = arena;
        this.gameStateManager = new GameStateManager();
        List<Location> spawnLocations = new ArrayList<>();
        this.teamManager = new TeamManager(spawnLocations);
        this.playerManager = new PlayerManager();
        this.game = new Game(this, arena); // Initialize Game here
    }

    public void initializeGame() {
        game.initializeGame(); // Call the Game's initialization
        startCountdown(); // Start the countdown for the game
    }

    private void startCountdown() {
        gameStateManager.setGameState(GameState.STARTING); // Set the game state to STARTING
        int countdownTime = 10; // Countdown for 10 seconds
        Bukkit.getScheduler().runTaskTimer(BeaconBattle.getInstance(), new Runnable() {
            int timeLeft = countdownTime;

            @Override
            public void run() {
                if (timeLeft <= 0) {
                    game.startGame(); // Start the game
                    Bukkit.getScheduler().cancelTasks(BeaconBattle.getInstance());
                } else if (timeLeft == 10) {
                    sendTitleToAllPlayers("&c10");
                } else if (timeLeft <= 5) {
                    sendTitleToAllPlayers(getCountdownTitle(timeLeft));
                }
                timeLeft--;
            }
        }, 0, 20); // 20 ticks = 1 second
    }

    private void sendTitleToAllPlayers(String title) {
        for (Player player : getPlayers()) {
            player.sendTitle(title, "", 10, 20, 10); // Title, subtitle, fade in, stay, fade out
        }
    }

    private String getCountdownTitle(int time) {
        switch (time) {
            case 5: return "&a5";
            case 4: return "&b4";
            case 3: return "&63";
            case 2: return "&d2";
            case 1: return "&e1";
            default: return "";
        }
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
