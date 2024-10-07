package net.kevarion.beaconbattle.arena;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArenaManager {

    private static ArenaManager instance;
    private Map<String, Arena> arenas;
    private File arenaFile;
    private FileConfiguration arenaConfig;

    public ArenaManager(File file) {
        arenas = new HashMap<>();
        this.arenaFile = file;
        loadArenas();
    }

    public static ArenaManager getInstance() {
        if (instance == null) {
            instance = new ArenaManager(new File(Bukkit.getServer().getPluginManager().getPlugin("YourPluginName").getDataFolder(), "arenas.yml"));
        }
        return instance;
    }

    public void addArena(Arena arena) {
        arenas.put(arena.getName().toLowerCase(), arena);
        saveArenaToYAML(arena);
        saveConfig();
    }

    public void removeArena(String arenaName) {
        arenas.remove(arenaName.toLowerCase());
        saveConfig();
    }

    public boolean arenaExists(String arenaName) {
        return arenas.containsKey(arenaName.toLowerCase());
    }

    public Arena getArena(String arenaName) {
        return arenas.get(arenaName.toLowerCase());
    }

    public Arena getArenaByPlayer(Player player) {
        Location playerLocation = player.getLocation();
        for (Arena arena : arenas.values()) {
            if (arena.isLocationInBounds(playerLocation)) {
                return arena; // Return the first arena that contains the player's location
            }
        }
        return null; // Return null if the player is not in any arena
    }

    public Map<String, Arena> getArenas() {
        return arenas;
    }

    public List<String> getArenaNames() {
        return arenas.values().stream().map(Arena::getName).collect(Collectors.toList());
    }

    private void saveArenaToYAML(Arena arena) {
        String basePath = "arenas." + arena.getName().toLowerCase();
        arenaConfig.set(basePath + ".centerLocation", serializeLocation(arena.getCenterLocation()));
        arenaConfig.set(basePath + ".teamSpawns", arena.getTeamSpawns().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> serializeLocation(e.getValue()))));
        saveConfig();
    }

    private void loadArenas() {
        arenaFile = new File(Bukkit.getServer().getPluginManager().getPlugin("YourPluginName").getDataFolder(), "arenas.yml");
        if (!arenaFile.exists()) {
            try {
                arenaFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        arenaConfig = YamlConfiguration.loadConfiguration(arenaFile);

        if (arenaConfig.isConfigurationSection("arenas")) {
            for (String arenaName : arenaConfig.getConfigurationSection("arenas").getKeys(false)) {
                Arena arena = new Arena(arenaName);
                arena.setCenterLocation(deserializeLocation(arenaConfig.getString("arenas." + arenaName + ".centerLocation")));

                Map<String, Location> teamSpawns = new HashMap<>();
                for (String team : arenaConfig.getConfigurationSection("arenas." + arenaName + ".teamSpawns").getKeys(false)) {
                    Location spawn = deserializeLocation(arenaConfig.getString("arenas." + arenaName + ".teamSpawns." + team));
                    teamSpawns.put(team, spawn);
                }
                arena.setTeamSpawns(teamSpawns);
                arenas.put(arenaName.toLowerCase(), arena);
            }
        }
    }

    private void saveConfig() {
        try {
            arenaConfig.save(arenaFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String serializeLocation(Location location) {
        if (location == null) return null;
        return location.getWorld().getName() + "," + location.getX() + "," + location.getY() + "," + location.getZ() + "," + location.getYaw() + "," + location.getPitch();
    }

    private Location deserializeLocation(String locationString) {
        if (locationString == null || locationString.isEmpty()) return null;
        String[] parts = locationString.split(",");
        return new Location(Bukkit.getWorld(parts[0]),
                Double.parseDouble(parts[1]),
                Double.parseDouble(parts[2]),
                Double.parseDouble(parts[3]),
                Float.parseFloat(parts[4]),
                Float.parseFloat(parts[5]));
    }
}
