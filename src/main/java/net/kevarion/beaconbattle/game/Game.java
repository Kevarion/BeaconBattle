package net.kevarion.beaconbattle.game;

import net.kevarion.beaconbattle.BeaconBattle;
import net.kevarion.beaconbattle.arena.Arena;
import net.kevarion.beaconbattle.game.state.GameState;
import net.kevarion.beaconbattle.game.state.GameStateManager;
import net.kevarion.beaconbattle.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Game {

    private final GameManager gameManager;
    private final Set<Player> players;
    private final Arena arena;
    private final Map<Player, Integer> scores;

    public Game(GameManager gameManager, Arena arena) {
        this.gameManager = gameManager;
        this.players = gameManager.getPlayers();
        this.arena = arena;
        this.scores = new HashMap<>();
    }

    public void initializeGame() {
        for (Player player : players) {
            scores.put(player, 0);
        }
        gameManager.initializeGame();
    }

    public void startGame() {
        if (gameManager.getGameStateManager().isPregame()) {
            startCountdown();
            System.out.println("Started countdown.");
        }
    }

    private void startCountdown() {
        new BukkitRunnable() {
            int countdown = 10;

            @Override
            public void run() {
                if (countdown > 0) {
                    for (Player player : players) {
                        player.sendTitle("§c" + countdown, "", 10, 20, 10);
                    }

                    if (countdown <= 5) {
                        String message = "§a" + countdown;
                        for (Player player : players) {
                            player.sendMessage(message);
                        }
                    }

                    countdown--;
                } else {
                    // Countdown finished, start the game
                    this.cancel();
                    gameManager.getGameStateManager().setGameState(GameState.ACTIVE);
                    System.out.println("Game has started.");
                }
            }
        }.runTaskTimer(BeaconBattle.getInstance(), 0, 20); // Schedule task to run every second
    }

    public void endGame() {
        if (gameManager.getGameStateManager().isGameActive()) {
            gameManager.endGame();
            System.out.println("Ended");
        }
    }

    public void onPlayerScore(Player player) {
        scores.put(player, scores.getOrDefault(player, 0) + 1);
        System.out.println(player.getName() + " scored! Current score: " + scores.get(player));
    }

    public int getPlayerScore(Player player) {
        return scores.getOrDefault(player, 0);
    }

    public void resetGame() {
        scores.clear();
        gameManager.resetGame();
        System.out.println("Reset.");
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public Arena getArena() {
        return arena;
    }

}
