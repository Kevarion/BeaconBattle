package net.kevarion.beaconbattle.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import net.kevarion.beaconbattle.BeaconBattle;

public class LevelStorage {

    private final BeaconBattle plugin;
    private File levelsFile;
    private FileConfiguration levelsConfig;

    public LevelStorage(BeaconBattle plugin) {
        this.plugin = plugin;
        createLevelsFile();
    }

    private void createLevelsFile() {
        levelsFile = new File(plugin.getDataFolder(), "levels.yml");
        if (!levelsFile.exists()) {
            levelsFile.getParentFile().mkdirs();
            plugin.saveResource("levels.yml", false);
        }
        levelsConfig = YamlConfiguration.loadConfiguration(levelsFile);
    }

    public FileConfiguration getLevelsConfig() {  // This method provides access to levels.yml config
        return levelsConfig;
    }

    public void saveLevelsConfig() {  // Save method to write changes to levels.yml
        try {
            levelsConfig.save(levelsFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save levels.yml!", e);
        }
    }

    public void reloadLevelsConfig() {  // Reload method to refresh the config from file
        levelsConfig = YamlConfiguration.loadConfiguration(levelsFile);
    }
}
