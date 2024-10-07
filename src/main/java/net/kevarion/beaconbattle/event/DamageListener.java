package net.kevarion.beaconbattle.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    // You can use EntityDamageByEntityEvent if you mean damage by another player.
    @EventHandler
    public void onDamage(EntityDamageEvent event) {

    }

}
