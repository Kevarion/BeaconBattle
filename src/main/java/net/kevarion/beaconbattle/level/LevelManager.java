package net.kevarion.beaconbattle.level;

import net.kevarion.beaconbattle.storage.LevelStorage;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LevelManager {

    private final LevelStorage levelStorage;

    public LevelManager(LevelStorage levelStorage) {
        this.levelStorage = levelStorage;
    }

    public Level loadPlayerLevel(Player player) {
        UUID playerUUID = player.getUniqueId();
        int level = levelStorage.getLevelsConfig().getInt(playerUUID + ".level", 1);    // Default to level 1
        int prestige = levelStorage.getLevelsConfig().getInt(playerUUID + ".prestige", 0); // Default to prestige 0
        int xp = levelStorage.getLevelsConfig().getInt(playerUUID + ".xp", 0);          // Default to 0 XP
        int xpToNextLevel = 3000 + (level * 100);
        return new Level(level, prestige, xp, xpToNextLevel);
    }

    public void savePlayerLevel(Player player, Level level) {
        UUID playerUUID = player.getUniqueId();
        levelStorage.getLevelsConfig().set(playerUUID + ".level", level.getLevel());
        levelStorage.getLevelsConfig().set(playerUUID + ".prestige", level.getPrestige());
        levelStorage.getLevelsConfig().set(playerUUID + ".xp", level.getXP());
        levelStorage.saveLevelsConfig();
    }

    public void addXP(Player player, int xp) {
        Level level = loadPlayerLevel(player);
        level.addXP(xp);
        savePlayerLevel(player, level);
    }

}
