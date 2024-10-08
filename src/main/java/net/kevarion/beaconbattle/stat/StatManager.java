package net.kevarion.beaconbattle.stat;

import net.kevarion.beaconbattle.storage.StatsStorage;
import org.bukkit.entity.Player;
import java.util.UUID;

public class StatManager {

    private final StatsStorage statsStorage;

    public StatManager(StatsStorage statsStorage) {
        this.statsStorage = statsStorage;
    }

    public int getKills(Player player) {
        UUID uuid = player.getUniqueId();
        return statsStorage.getStatsConfig().getInt("players." + uuid + ".kills", 0);
    }

    public int getDeaths(Player player) {
        UUID uuid = player.getUniqueId();
        return statsStorage.getStatsConfig().getInt("players." + uuid + ".deaths", 0);
    }

    public int getWins(Player player) {
        UUID uuid = player.getUniqueId();
        return statsStorage.getStatsConfig().getInt("players." + uuid + ".wins", 0);
    }

    public void addKill(Player player) {
        statsStorage.addKill(player);
    }

    public void addDeath(Player player) {
        UUID uuid = player.getUniqueId();
        int deaths = statsStorage.getStatsConfig().getInt("players." + uuid + ".deaths", 0);
        statsStorage.getStatsConfig().set("players." + uuid + ".deaths", deaths + 1);
        statsStorage.saveStatsConfig();
    }

    public void addWin(Player player) {
        UUID uuid = player.getUniqueId();
        int wins = statsStorage.getStatsConfig().getInt("players." + uuid + ".wins", 0);
        statsStorage.getStatsConfig().set("players." + uuid + ".wins", wins + 1);
        statsStorage.saveStatsConfig();
    }

    public void resetStats(Player player) {
        UUID uuid = player.getUniqueId();
        statsStorage.getStatsConfig().set("players." + uuid + ".kills", 0);
        statsStorage.getStatsConfig().set("players." + uuid + ".deaths", 0);
        statsStorage.getStatsConfig().set("players." + uuid + ".wins", 0);
        statsStorage.saveStatsConfig();
    }
}
