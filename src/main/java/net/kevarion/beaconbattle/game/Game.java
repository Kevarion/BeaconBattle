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



}
