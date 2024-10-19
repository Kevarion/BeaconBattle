package net.kevarion.beaconbattle.level;

import net.kevarion.beaconbattle.storage.LevelStorage;
import net.kevarion.beaconbattle.util.CC;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LevelManager {

    private final LevelStorage levelStorage;

    private final String[] prestigeTitles = {
            "&7Coal", "&fIron", "&2Emerald", "&6Gold", "&bDiamond",
            "&4Ruby", "&3Sapphire", "&5Opal", "#9966ccAmethyst", "#0047ABCobalt"
    };

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

        while (level.getXP() >= level.getXpToNextLevel()) {
            level.setXP(level.getXP() - level.getXpToNextLevel());
            level.setLevel(level.getLevel() + 1);
            level.setXPToNextLevel(calculateXPToNextLevel(level.getLevel(), level.getPrestige())); // Recalculate XP for the next level

            player.sendMessage(CC.translate("&aYou have leveled up to level " + level));


        }

        savePlayerLevel(player, level);
    }

    public void checkForPrestige(Player player, Level level) {
        int prestige = level.getPrestige();
        int nextPrestigeLevel = (prestige + 1) * 100;

        if (level.getLevel() >= nextPrestigeLevel) {
            level.setPrestige(prestige + 1);
            level.setLevel(0);
            level.setXP(0);
            level.setXPToNextLevel(calculateXPToNextLevel(1, prestige + 1));

            savePlayerLevel(player, level);

            player.sendMessage(CC.translate("&bCongratulations! You've reached " + prestigeTitles[prestige + 1] + " Prestige!"));
        }

    }

    private int calculateXPToNextLevel(int level, int prestige) {
        return 3000 + (level * 100) + (prestige * 200);
    }

}
