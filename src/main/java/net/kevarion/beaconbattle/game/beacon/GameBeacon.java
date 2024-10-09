package net.kevarion.beaconbattle.game.beacon;

import net.kevarion.beaconbattle.manager.PlayerManager;
import net.kevarion.beaconbattle.util.CC;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class GameBeacon implements Listener {

    private final Location location;
    private final String teamColor;
    private boolean active;
    private final PlayerManager playerManager;

    public GameBeacon(Location location, String teamColor, PlayerManager playerManager) {
        this.location = location;
        this.teamColor = teamColor;
        this.active = true;
        this.playerManager = playerManager;
    }

    public Location getLocation() {
        return location;
    }

    public String getTeamColor() {
        return teamColor;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void breakBeacon(Player player) {
        setActive(false);
        player.sendTitle(CC.translate("&b&lBEACON"), CC.translate("&fYour beacon has been destroyed!"));
        playerManager.getPlayers().forEach(p -> p.sendMessage(CC.translate("&b&lBEACON &7> &f") + player.getName() + CC.translate("&f's &bBeacon &fhas been destroyed!")));
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.BEACON && block.getLocation().equals(location)) {
            Player player = event.getPlayer();
            if (isActive()) {
                breakBeacon(player);
                event.setCancelled(false);
                player.sendMessage(CC.translate("&cYou've broken the beacon!"));
            } else {
                player.sendMessage(CC.translate("&cThis beacon has already been broken!"));
                event.setCancelled(true);
            }
        }
    }
}
