package net.kevarion.beaconbattle.stat;

import net.kevarion.beaconbattle.BeaconBattle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class StatTracker implements Listener {

    private final StatManager statManager;

    public StatTracker(StatManager statManager) {
        this.statManager = statManager;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player killed =  (Player) event.getEntity();
            Player killer = killed.getKiller();

            if (killer != null) {
                statManager.addKill(killer);
                statManager.addDeath(killed);
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        BeaconBattle.getInstance().getStatsStorage().saveStatsConfig();
    }

}
