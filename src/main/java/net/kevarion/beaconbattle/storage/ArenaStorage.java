package net.kevarion.beaconbattle.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import net.kevarion.beaconbattle.BeaconBattle;

public class ArenaStorage {

    private final BeaconBattle plugin;
    private File arenaFile;
    private FileConfiguration arenaConfig;

    public ArenaStorage(BeaconBattle plugin) {
        this.plugin = plugin;
        createArenaFile();
    }

    private void createArenaFile() {
        arenaFile = new File(plugin.getDataFolder(), "arenas.yml");
        if (!arenaFile.exists()) {
            arenaFile.getParentFile().mkdirs();
            plugin.saveResource("arenas.yml", false);
        }
        arenaConfig = YamlConfiguration.loadConfiguration(arenaFile);
    }

    public FileConfiguration getArenaConfig() {
        return arenaConfig;
    }

    public void saveArenaConfig() {
        try {
            arenaConfig.save(arenaFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save arenas.yml!", e);
        }
    }

    public void reloadArenaConfig() {
        arenaConfig = YamlConfiguration.loadConfiguration(arenaFile);
    }

    // Methods to store and retrieve arena-specific data like beacons, spawn points, etc.
}