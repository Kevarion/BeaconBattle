package net.kevarion.beaconbattle.arena;

import org.bukkit.Location;
import java.util.HashMap;
import java.util.Map;

public class Arena {

    private String name;
    private Location centerLocation;
    private Map<String, Location> teamSpawns;
    private Location minBoundary; // Minimum corner of the arena
    private Location maxBoundary; // Maximum corner of the arena

    public Arena(String name) {
        this.name = name;
        this.teamSpawns = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setCenterLocation(Location centerLocation) {
        this.centerLocation = centerLocation;
    }

    public Location getCenterLocation() {
        return centerLocation;
    }

    public void setTeamSpawn(String teamName, Location location) {
        teamSpawns.put(teamName, location);
    }

    public Location getTeamSpawn(String teamName) {
        return teamSpawns.get(teamName);
    }

    public Map<String, Location> getTeamSpawns() {
        return teamSpawns;
    }

    public void setTeamSpawns(Map<String, Location> spawns) {
        this.teamSpawns = spawns;
    }

    // New methods to set boundaries
    public void setBounds(Location minBoundary, Location maxBoundary) {
        this.minBoundary = minBoundary;
        this.maxBoundary = maxBoundary;
    }

    public boolean isLocationInBounds(Location location) {
        if (location.getWorld() != minBoundary.getWorld()) return false;

        return location.getX() >= minBoundary.getX() && location.getX() <= maxBoundary.getX() &&
                location.getY() >= minBoundary.getY() && location.getY() <= maxBoundary.getY() &&
                location.getZ() >= minBoundary.getZ() && location.getZ() <= maxBoundary.getZ();
    }

    public Location getMinBoundary() {
        return minBoundary;
    }

    public Location getMaxBoundary() {
        return maxBoundary;
    }
}
