package dev.goldenedit.ns2core.Listeners;

import dev.goldenedit.ns2core.NS2Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class onJoin implements Listener {

    public static String chatColors(String str) {
        Pattern unicode = Pattern.compile("\\\\u\\+[a-fA-F0-9]{4}");
        Matcher match = unicode.matcher(str);
        while (match.find()) {
            String code = str.substring(match.start(),match.end());
            str = str.replace(code,Character.toString((char) Integer.parseInt(code.replace("\\u+",""),16)));
            match = unicode.matcher(str);
        }
        Pattern pattern = Pattern.compile("&#[a-fA-F0-9]{6}");
        match = pattern.matcher(str);
        while (match.find()) {
            String color = str.substring(match.start(),match.end());
            str = str.replace(color, net.md_5.bungee.api.ChatColor.of(color.replace("&","")) + "");
            match = pattern.matcher(str);
        }
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&',str);
    }

    private final NS2Core plugin;

    public onJoin(NS2Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        plugin.cooldown.put(p.getPlayer(), System.currentTimeMillis());
        plugin.water.put(p.getPlayer(), 1);
        if(p.hasPermission("n2.exile")){
            String text = chatColors("&eYour team: &#000000Exile");
            p.sendMessage(text);
        }else if(p.hasPermission("n2.snow")){
            String text = chatColors("&eYour team: &#7bf1ffSnow");
            p.sendMessage(text);
        }else if(p.hasPermission("n2.desert")){
            String text = chatColors("&eYour team: &#f4ff7fDesert");
            p.sendMessage(text);
        }else if(p.hasPermission("n2.jungle")){
            String text = chatColors("&eYour team: &#ffc437Jungle");
            p.sendMessage(text);
        }else if(p.hasPermission("n2.waste")){
            String text = chatColors("&eYour team: &#af4444Waste");
            p.sendMessage(text);
        }else if(p.hasPermission("n2.swamp")){
            String text = chatColors("&eYour team: &#c78971Swamp");
            p.sendMessage(text);
        }else if(p.hasPermission("n2.plain")) {
            String text = chatColors("&eYour team: &#9bff65Plain");
            p.sendMessage(text);
        }else{
            p.sendMessage("No Team");
        }
    }
}
