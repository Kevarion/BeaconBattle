package net.kevarion.beaconbattle.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.UUID;
import net.kevarion.beaconbattle.BeaconBattle;

public class StatsStorage {

    private final BeaconBattle plugin;
    private File statsFile;
    private FileConfiguration statsConfig;

    public StatsStorage(BeaconBattle plugin) {
        this.plugin = plugin;
        createStatsFile();
    }

    private void createStatsFile() {
        statsFile = new File(plugin.getDataFolder(), "stats.yml");
        if (!statsFile.exists()) {
            statsFile.getParentFile().mkdirs();
            plugin.saveResource("stats.yml", false);
        }
        statsConfig = YamlConfiguration.loadConfiguration(statsFile);
    }

    public FileConfiguration getStatsConfig() {
        return statsConfig;
    }

    public void saveStatsConfig() {
        try {
            statsConfig.save(statsFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save stats.yml!", e);
        }
    }

    public void reloadStatsConfig() {
        statsConfig = YamlConfiguration.loadConfiguration(statsFile);
    }

    public void addKill(Player player) {
        UUID uuid = player.getUniqueId();
        int kills = statsConfig.getInt("players." + uuid + ".kills", 0);
        statsConfig.set("players." + uuid + ".kills", kills + 1);
        saveStatsConfig();
    }

    // Other stats-related methods like addDeath, addWin, etc.
}