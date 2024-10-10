package net.kevarion.beaconbattle.level;

import net.kevarion.beaconbattle.storage.LevelStorage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LevelTracker {

    private final LevelManager levelManager;
    private final Map<UUID, Level> playerLevels;

    public LevelTracker(LevelStorage levelStorage) {
        this.levelManager = new LevelManager(levelStorage);
        this.playerLevels = new HashMap<>();
    }

    public void trackPlayerLevel(Player player) {
        UUID playerUUID = player.getUniqueId();
        Level level = levelManager.loadPlayerLevel(player);
        playerLevels.put(playerUUID, level);
    }

    public Level getPlayerLevel(Player player) {
        return playerLevels.get(player.getUniqueId());
    }

    public void addXPToPlayer(Player player, int xp) {
        UUID playerUUID = player.getUniqueId();
        Level level = playerLevels.get(playerUUID);
        if (level == null) {
            level = levelManager.loadPlayerLevel(player);
        }
        level.addXP(xp);
        levelManager.savePlayerLevel(player, level);
    }

    public void trackAllPlayers(Map<UUID, Player> players) {
        for (UUID uuid : players.keySet()) {
            Player player = players.get(uuid);
            trackPlayerLevel(player);
        }
    }

    public void saveAllPlayerLevels() {
        for (UUID playerUUID : playerLevels.keySet()) {
            Level level = playerLevels.get(playerUUID);
            if (level != null) {
                Player player = Bukkit.getPlayer(playerUUID);
                if (player != null) {
                    levelManager.savePlayerLevel(player, level);
                }
            }
        }
    }
}
