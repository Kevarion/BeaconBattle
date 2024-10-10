package net.kevarion.beaconbattle.level.xp;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kevarion.beaconbattle.game.Game;
import net.kevarion.beaconbattle.storage.LevelStorage;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameXPTracker {

    private final LevelStorage levelStorage;
    private final Map<UUID, GameXP> playerXPMap;

    public GameXPTracker(LevelStorage levelStorage) {
        this.levelStorage = levelStorage;
        this.playerXPMap = new HashMap<>();
    }

    public void loadPlayer(Player player) {
        UUID playerUUID = player.getUniqueId();
        int storedXP = levelStorage.getLevelsConfig().getInt(playerUUID +  ".xp", 0);
        GameXP gameXP = new GameXP();
        gameXP.setXP(storedXP);
        playerXPMap.put(playerUUID, gameXP);
    }

    public GameXP getPlayerXP(Player player) {
        return playerXPMap.get(player.getUniqueId());
    }

    public void addXP(Player player, int amount) {
        GameXP gameXP = playerXPMap.get(player.getUniqueId());
        if (gameXP != null) {
            gameXP.addXP(amount);
        }
    }

    public void savePlayer(Player player) {
        UUID playerUUID = player.getUniqueId();
        GameXP gameXP = playerXPMap.get(playerUUID);
        if (gameXP != null) {
            levelStorage.getLevelsConfig().set(player + ".xp", gameXP.getXP());
            levelStorage.saveLevelsConfig();
        }
    }

    public void reset() {
        playerXPMap.clear();
    }

    public void saveAllPlayers() {
        for (UUID playerUUID : playerXPMap.keySet()) {
            GameXP gameXP = playerXPMap.get(playerUUID);
            if (gameXP != null) {
                levelStorage.getLevelsConfig().set(playerUUID + ".xp", gameXP.getXP());
            }
        }
        levelStorage.saveLevelsConfig();
        reset();
    }

}
