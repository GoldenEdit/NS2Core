package dev.goldenedit.ns2core.Listeners;

import dev.goldenedit.ns2core.NS2Core;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
public class onPlayerMove implements Listener {
    private final NS2Core plugin;

    public onPlayerMove(NS2Core plugin) {
        this.plugin = plugin;
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent e) {
        if(e.getPlayer().getLocation().isChunkLoaded() == true){
            Player p = e.getPlayer();
            Material m = e.getPlayer().getLocation().getBlock().getType();
            if (m == Material.WATER) {
                if (p.hasPermission("n2.swamp")) {
//                int cooldownTime = 100;
//                long secondsLeft = ((cooldown.get(p.getPlayer()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
//                p.sendMessage("" + secondsLeft);
                    if (plugin.water.get(p) == 1) {
                        plugin.water.put(p.getPlayer(), 0);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, 0, true, false));
                    }
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, Integer.MAX_VALUE, 0, true, false));
                }
                } else {
                    if (p.hasPermission("n2.swamp")) {
                        //cooldown.put(p.getPlayer(), System.currentTimeMillis());
                        p.removePotionEffect(PotionEffectType.WATER_BREATHING);
                        p.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
                        plugin.water.put(p.getPlayer(), 1);
                    }
                }
            }
    }
}
