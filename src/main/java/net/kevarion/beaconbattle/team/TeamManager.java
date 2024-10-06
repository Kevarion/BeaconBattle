package net.kevarion.beaconbattle.team;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class TeamManager {
    private final List<Team> teams;
    private final Random random;
    private List<Location> spawnLocations;

    private final Map<Player, Team> playerTeams = new HashMap<>();

    public TeamManager(List<Location> islandLocations) {
        teams = new ArrayList<>();
        teams.add(new Team("Red", ChatColor.RED, islandLocations.get(0)));
        teams.add(new Team("Blue", ChatColor.BLUE, islandLocations.get(1)));
        teams.add(new Team("Green", ChatColor.GREEN, islandLocations.get(2)));
        teams.add(new Team("Yellow", ChatColor.YELLOW, islandLocations.get(3)));
        teams.add(new Team("Aqua", ChatColor.AQUA, islandLocations.get(4)));
        teams.add(new Team("White", ChatColor.WHITE, islandLocations.get(5)));
        teams.add(new Team("Pink", ChatColor.LIGHT_PURPLE, islandLocations.get(6)));
        teams.add(new Team("Gray", ChatColor.DARK_GRAY, islandLocations.get(7)));

        this.spawnLocations = spawnLocations;
        random = new Random();
    }

    public Team assignRandomTeam(Player player) {
        Collections.shuffle(teams);
        Team assignedTeam = teams.get(0);
        assignedTeam.addPlayer(player);
        return assignedTeam;
    }

    public Team getPlayerTeam(Player player) {
        return playerTeams.get(player);
    }

}