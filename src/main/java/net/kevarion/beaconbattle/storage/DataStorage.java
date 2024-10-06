package net.kevarion.beaconbattle.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import net.kevarion.beaconbattle.BeaconBattle;

public class DataStorage {

    private final BeaconBattle plugin;
    private File dataFile;
    private FileConfiguration dataConfig;

    public DataStorage(BeaconBattle plugin) {
        this.plugin = plugin;
        createDataFile();
    }

    private void createDataFile() {
        dataFile = new File(plugin.getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            plugin.saveResource("data.yml", false);
        }
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    public FileConfiguration getDataConfig() {
        return dataConfig;
    }

    public void saveDataConfig() {
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save data.yml!", e);
        }
    }

    public void reloadDataConfig() {
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }
}