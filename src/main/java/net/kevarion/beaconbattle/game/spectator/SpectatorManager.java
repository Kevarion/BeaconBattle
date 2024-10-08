package net.kevarion.beaconbattle.game.spectator;

import net.kevarion.beaconbattle.util.CC;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpectatorManager {

    private final Set<Player> spectators;

    public SpectatorManager() {
        this.spectators = new HashSet<>();
    }

    public void moveToSpectatorMode(Player player, boolean finalKill) {
        spectators.add(player);
        player.setInvisible(true);

        if (finalKill) {
            giveItemsOnDeath(player);
            player.sendTitle(CC.translate("&cLOSS"), CC.translate("&fYou have &clost &fthe game!"));
        } else {
            player.getInventory().clear();
            player.sendTitle(CC.translate("&cDEATH"), CC.translate("&fYou will respawn in &e3 &fseconds"), 10, 70, 20);
        }

        addSpectatorItems(player);
    }

    private void giveItemsOnDeath(Player player) {
        player.getInventory().addItem(createCompass());
        player.getInventory().addItem(createRedDye());
        player.getInventory().addItem(createPaper());
    }

    private void addSpectatorItems(Player player) {
        player.getInventory().clear(); /

        ItemStack compass = createCompass();
        player.getInventory().addItem(compass);

        ItemStack paper = createPaper();
        player.getInventory().addItem(paper);

        ItemStack redDye = createRedDye();
        player.getInventory().addItem(redDye);
    }

    private ItemStack createCompass() {
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = compass.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Teleporter");
        compass.setItemMeta(meta);
        return compass;
    }

    private ItemStack createPaper() {
        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta meta = paper.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Queue Game");
        paper.setItemMeta(meta);
        return paper;
    }

    private ItemStack createRedDye() {
        ItemStack dye = new ItemStack(Material.RED_DYE);
        ItemMeta meta = dye.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Leave Game");
        dye.setItemMeta(meta);
        return dye;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        boolean isFinalKill;
        //TODO
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (spectators.contains(player)) {
            if (event.getItem() != null && event.getItem().getType() == Material.COMPASS) {
                player.sendMessage(CC.translate("&aYou used the Teleporter!"));
                //TODO
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (spectators.contains(player)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        spectators.remove(player); // Remove from spectators on quit
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (spectators.contains(player)) {
            //TODO: event.setRespawnLocation();
            moveToSpectatorMode(player, false);
        }
    }

}
