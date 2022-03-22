package dev.goldenedit.ns2core.Listeners;

import dev.goldenedit.ns2core.NS2Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class onEat implements Listener {
    private final NS2Core plugin;

    public onEat(NS2Core plugin) {
        this.plugin = plugin;
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEat(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        if(p.hasPermission("n2.plain")){
            if(e.getItem().getType().equals(Material.MILK_BUCKET)) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 600, 0, true, false));
                    }
                }, 10L);
            }
        }else if(p.hasPermission("n2.waste")){
            if(e.getItem().getType().equals(Material.ROTTEN_FLESH)) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        p.removePotionEffect(PotionEffectType.HUNGER);
                    }
                }, 1L);
            }
        }
    }
}
