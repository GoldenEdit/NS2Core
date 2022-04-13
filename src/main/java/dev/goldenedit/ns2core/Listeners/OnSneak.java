package dev.goldenedit.ns2core.Listeners;

import dev.goldenedit.ns2core.NS2Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class OnSneak implements Listener {
    private final NS2Core plugin;

    public OnSneak(NS2Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSneak(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        if(p.hasPermission("n2.plain")){
            if(!p.isSneaking()){
                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1, true, false));
            }else if (!p.isSneaking()){
                p.removePotionEffect(PotionEffectType.FAST_DIGGING);
            }
        }
    }
}
