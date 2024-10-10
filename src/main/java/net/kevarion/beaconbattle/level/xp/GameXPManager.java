package net.kevarion.beaconbattle.level.xp;

import net.kevarion.beaconbattle.level.xp.GameXP;
import net.kevarion.beaconbattle.storage.LevelStorage;
import org.bukkit.entity.Player;
import java.util.UUID;

public class GameXPManager {

    private static final int KILL_XP = 5;
    private static final int BREAK_BEACON_XP = 10;
    private static final int WIN_XP = 50;

    private final LevelStorage levelStorage;

    public GameXPManager(LevelStorage levelStorage) {
        this.levelStorage = levelStorage;
    }

    public GameXP loadPlayerXP(Player player) {
        UUID playerUUID = player.getUniqueId();
        int storedXP = levelStorage.getLevelsConfig().getInt(playerUUID + ".xp", 0); // Load XP from levels.yml
        GameXP gameXP = new GameXP();
        gameXP.setXP(storedXP);
        return gameXP;
    }

    public void savePlayerXP(Player player, GameXP gameXP) {
        UUID playerUUID = player.getUniqueId();
        levelStorage.getLevelsConfig().set(playerUUID + ".xp", gameXP.getXP()); // Save XP to levels.yml
        levelStorage.saveLevelsConfig(); // Ensure it's written to file
    }

    public void awardKillXP(Player player) {
        GameXP gameXP = loadPlayerXP(player);
        gameXP.addXP(KILL_XP);
        savePlayerXP(player, gameXP);
    }

    public void awardBeaconBreakXP(Player player) {
        GameXP gameXP = loadPlayerXP(player);
        gameXP.addXP(BREAK_BEACON_XP);
        savePlayerXP(player, gameXP);
    }

    public void awardWinXP(Player player) {
        GameXP gameXP = loadPlayerXP(player);
        gameXP.addXP(WIN_XP);
        savePlayerXP(player, gameXP);
    }

    public void finalizePlayerXP(Player player) {
        GameXP gameXP = loadPlayerXP(player);
        int totalXP = gameXP.getXP();
        player.sendMessage("You have received a total of " + totalXP + " XP this game!");
        gameXP.resetXP();
        savePlayerXP(player, gameXP);
    }
}
