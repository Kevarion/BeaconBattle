package net.kevarion.beaconbattle.scoreboard;

import net.kevarion.beaconbattle.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SBManager {

    private final Scoreboard scoreboard;
    private final Objective objective;
    private final Map<Player, String> teamStatuses;

    public SBManager() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        scoreboard = manager.getNewScoreboard();

        objective = scoreboard.registerNewObjective("gameStats", "dummy", CC.translate("&e&lKERIVAX"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        teamStatuses = new HashMap<>();
        initializeBoard();
    }

    private void initializeBoard() {
        // Date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        String date = dateFormat.format(new Date());

        objective.getScore(CC.translate("&7" + date)).setScore(9);

        objective.getScore(" ").setScore(8);

        objective.getScore(CC.translate("&cRed: &a✔")).setScore(7);
        objective.getScore(CC.translate("&9Blue: &a✔")).setScore(6);
        objective.getScore(CC.translate("&aGreen: &a✔")).setScore(5);
        objective.getScore(CC.translate("&eYellow: &a✔")).setScore(4);
        objective.getScore(CC.translate("&bAqua: &a✔")).setScore(3);
        objective.getScore(CC.translate("&fWhite: &a✔")).setScore(2);
        objective.getScore(CC.translate("&dPink: &a✔")).setScore(1);
        objective.getScore(CC.translate("&7Gray: &a✔")).setScore(0);

        objective.getScore("  ").setScore(-1);

        objective.getScore(CC.translate("&ekerivax.net")).setScore(-2);
    }

    public void updateTeamStatus(String team, boolean isAlive, boolean hasBeacon) {
        String status;
        if (isAlive) {
            if (hasBeacon) {
                status = CC.translate("&a✔");  // Alive and has a beacon
            } else {
                status = CC.translate("&e1");  // Alive but doesn't have a beacon
            }
        } else {
            status = CC.translate("&c❌"); // Doesn't have a beacon and dead
        }

        switch (team.toLowerCase()) {
            case "red":
                updateScore(CC.translate("&cRed: " + status), 7);
                break;
            case "blue":
                updateScore(CC.translate("&9Blue: " + status), 6);
                break;
            case "green":
                updateScore(CC.translate("&aGreen: " + status), 5);
                break;
            case "yellow":
                updateScore(CC.translate("&eYellow: " + status), 4);
                break;
            case "aqua":
                updateScore(CC.translate("&bAqua: " + status), 3);
                break;
            case "white":
                updateScore(CC.translate("&fWhite: " + status), 2);
                break;
            case "pink":
                updateScore(CC.translate("&dPink: " + status), 1);
                break;
            case "gray":
                updateScore(CC.translate("&7Gray: " + status), 0);
                break;
            default:
                break;
        }
    }

    private void updateScore(String entry, int score) {
        scoreboard.resetScores(entry);
        objective.getScore(entry).setScore(score);
    }

    public void addPlayer(Player player) {
        player.setScoreboard(scoreboard);
    }

    public void removePlayer(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    public void resetScoreboard() {
        scoreboard.getEntries().forEach(scoreboard::resetScores);
    }

}