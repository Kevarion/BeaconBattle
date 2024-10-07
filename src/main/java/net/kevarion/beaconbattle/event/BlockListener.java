package net.kevarion.beaconbattle.event;

import net.kevarion.beaconbattle.arena.Arena;
import net.kevarion.beaconbattle.arena.ArenaManager;
import net.kevarion.beaconbattle.game.GameManager;
import net.kevarion.beaconbattle.manager.PlayerManager;
import net.kevarion.beaconbattle.scoreboard.SBManager;
import net.kevarion.beaconbattle.util.CC;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Arena arena = ArenaManager.getInstance().getArenaByPlayer(player);

        if (arena != null) {
            if (!arena.isLocationInBounds(event.getBlock().getLocation())) {
                event.setCancelled(true);
                player.sendMessage(CC.translate("&cYou cannot break blocks outside of boundaries."));
            }
        }

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Arena arena = ArenaManager.getInstance().getArenaByPlayer(player);

        if (arena != null) {
            if (!arena.isLocationInBounds(event.getBlock().getLocation())) {
                event.setCancelled(true);
                player.sendMessage(CC.translate("&cYou cannot place blocks outside of boundaries."));
            }
        }
    }

}
