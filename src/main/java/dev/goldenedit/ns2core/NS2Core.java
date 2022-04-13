package dev.goldenedit.ns2core;


import dev.goldenedit.ns2core.Listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public final class NS2Core extends JavaPlugin implements Listener {
    private static Plugin plugin;
    public HashMap<Player, Long> cooldown = new HashMap<Player, Long>();
    public HashMap<Player, Integer> sneak = new HashMap<Player, Integer>();
    public HashMap<Player, Integer> water = new HashMap<Player, Integer>();
    @Override
    public void onEnable() {
        this.getLogger().info("NS2 Core has started");
        this.getLogger().info("https://goldenedit.dev");
        this.getServer().getPluginManager().registerEvents(new onPlayerMove(this), this);
        this.getServer().getPluginManager().registerEvents(new onEat(this), this);
        this.getServer().getPluginManager().registerEvents(new onSneak(this), this);
        this.getServer().getPluginManager().registerEvents(new onJoin(this), this);
        this.getServer().getPluginManager().registerEvents(new onHit(this), this);
        this.getServer().getPluginManager().registerEvents(new onEntityDamage(this), this);
        this.getServer().getPluginManager().registerEvents(new onLeave(this), this);
        new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("n2.snow")) {
                        p.setFreezeTicks(0);
                        //if (p.getLocation().subtract(0, 0, 0).getBlock().getType() == Material.POWDER_SNOW || p.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.POWDER_SNOW) {
                        //}
                    }
                }
            }
        }.runTaskTimerAsynchronously(this, 0, 1);
        new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if(p.getInventory().getHelmet() == null){
                        if (p.hasPermission("n2.jungle")) {
                            p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(18);
                            p.setHealthScale(18);
                        } else if (p.hasPermission("n2.waste")) {
                            p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(24);
                            p.setHealthScale(24);
                        } else {
                            p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                            p.setHealthScale(20);
                        }
                    }
                }
            }
        }.runTaskTimer(this, 0, 100);
        new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getWorld().hasStorm() == true) {
                        if (p.hasPermission("n2.plain")) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 0, true, true));
                        }
                    } else {
                        if (p.hasPermission("n2.plain")) {
                            p.removePotionEffect(PotionEffectType.WEAKNESS);
                        }
                    }

                }
            }
        }.runTaskTimer(this, 0, 40);
        new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("n2.jungle")) {
                        if (p.isSneaking()) {
                            sneak.put(p, sneak.get(p) + 1);
                            if (sneak.get(p) == 20) {
                                int cooldownTime = 30;
//                                p.sendMessage(""+System.currentTimeMillis());
//                                p.sendMessage(cooldown.get(p.getPlayer());
                                long secondsLeft = ((cooldown.get(p.getPlayer()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
                                //p.sendMessage(""+secondsLeft);
                                if (secondsLeft > 0) {
                                    p.sendMessage(ChatColor.RED + "You cant do that for another " + secondsLeft + " seconds!");
                                } else {
                                    cooldown.put(p.getPlayer(), System.currentTimeMillis());
                                    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 160, 2, true, false));
                                }
                            }
                        } else if (!p.isSneaking()) {
                            sneak.put(p, 0);
                        }
                    }
                }
            }
        }.runTaskTimer(this, 0, 5);
        new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("n2.waste")) {
                        if (p.getWorld().isDayTime() == true) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 0, true, true));
                        } else {
                            p.removePotionEffect(PotionEffectType.WEAKNESS);
                        }
                   }
                }
            }
        }.runTaskTimer(this, 0, 40);
        new BukkitRunnable(){
            public void run(){
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.hasPermission("n2.snow")){
                        if(p.getFoodLevel() < 20){
                            int food = p.getFoodLevel();
                            food = food + 2;
                            p.setFoodLevel(food);
                        }
                    }
                }
            }
        }.runTaskTimer(this, 900, 900);
        new BukkitRunnable(){
            public void run(){
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.hasPermission("n2.exile")){
                        p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 400, 0, true, false));
                    }
                }
            }
        }.runTaskTimer(this, 2400, 2400);
    }
    @Override
    public void onDisable() {
        this.getLogger().info("NS2 Core has stopped");
        this.getLogger().info("https://goldenedit.dev");
        plugin = null;
    }
}
