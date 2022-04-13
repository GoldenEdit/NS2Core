package dev.goldenedit.ns2core.Listeners;

import dev.goldenedit.ns2core.NS2Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnEntityDamage implements Listener {
    private final NS2Core plugin;

    public OnEntityDamage(NS2Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void EntityDamageEvent(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            if(e.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)){
                Player p = (Player)e.getEntity();
                if (p.hasPermission("n2.snow")) {
                    double ogdamage = e.getDamage();
                    double percent = ogdamage * 0.15;
                    double newdamage = ogdamage + percent;
                    e.setDamage(newdamage);
                }else if (p.hasPermission("n2.desert")){
                    double ogdamage = e.getDamage();
                    double percent = ogdamage * 0.15;
                    double newdamage = ogdamage - percent;
                    e.setDamage(newdamage);
                }
            }
            if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
                Player p = (Player)e.getEntity();
                if (p.hasPermission("n2.jungle")) {
                    double ogdamage = e.getDamage();
                    double percent = ogdamage * 0.36;
                    double newdamage = ogdamage - percent;
                    e.setDamage(newdamage);
                }
            }
        }
    }
}
