package dev.goldenedit.ns2core.Listeners;

import dev.goldenedit.ns2core.NS2Core;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class onHit implements Listener {
    private final NS2Core plugin;

    public onHit(NS2Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            //p.sendMessage("" + e.getDamage());
            if (p.hasPermission("n2.desert")) {
                if(p.getLocation().getBlock().getBiome() == Biome.BADLANDS || p.getLocation().getBlock().getBiome() == Biome.DESERT){
                    double dmg = e.getDamage();
                    e.setDamage(dmg + 1);
                    double dmg2 = dmg + 1;
                    //p.sendMessage("" + dmg2);
                }else if(!p.getLocation().getBlock().getBiome().equals(Biome.BADLANDS) || !p.getLocation().getBlock().getBiome().equals(Biome.DESERT)){
                    double dmg = e.getDamage();
                    e.setDamage(dmg - 1);
                    double dmg2 = dmg - 1;
                    //p.sendMessage("" + dmg2);
                }
            }else if(p.hasPermission("n2.swamp")){
                if ((e.getDamager() instanceof Player) && (e.getEntity() instanceof Player)) { //Check if both entities are players.
                    if(p.getLocation().getBlock().getBiome() == Biome.SWAMP || p.getLocation().getBlock().getBiome() == Biome.SWAMP_HILLS) {

                        Player player = (Player) e.getDamager();
                        Player target = (Player) e.getEntity();

                        int cooldownTime = 30;
                        long secondsLeft = ((plugin.cooldown.get(player) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
                        if (secondsLeft > 0) {
                            //String message = "§cYou cannot get water breathing for another " + secondsLeft + "s!";
                            //p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                        } else {
                            plugin.cooldown.put(p.getPlayer(), System.currentTimeMillis());
                            target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40, 1));
                        }
                    }
                }
            }
        }
    }
}
