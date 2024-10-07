package net.kevarion.beaconbattle.event;

import net.kevarion.beaconbattle.scoreboard.SBManager;
import net.kevarion.beaconbattle.util.CC;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashSet;
import java.util.ServiceConfigurationError;
import java.util.Set;

public class JoinQuitListener implements Listener {

    private final SBManager scoreboardManager;
    private final Set<Player> playersInMatch;

    public JoinQuitListener(SBManager scoreboardManager) {
        this.scoreboardManager = scoreboardManager;
        this.playersInMatch = new HashSet<>();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        scoreboardManager.addPlayer(player);

        playersInMatch.add(player);
        for (Player on : playersInMatch) {
            on.sendMessage(ChatColor.AQUA + player.getName() + ChatColor.YELLOW + " has joined the game!");
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        scoreboardManager.removePlayer(player);

        playersInMatch.remove(player);
        for (Player on : playersInMatch) {
            on.sendMessage(ChatColor.AQUA + player.getName() + ChatColor.YELLOW + " has left the game!");
        }
    }

}
