package net.kevarion.beaconbattle.game;

import net.kevarion.beaconbattle.arena.Arena;
import net.kevarion.beaconbattle.game.state.GameStateManager;
import net.kevarion.beaconbattle.manager.PlayerManager;
import org.bukkit.entity.Player;

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
            gameManager.startGame();
            System.out.println("Started.");
        }
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
