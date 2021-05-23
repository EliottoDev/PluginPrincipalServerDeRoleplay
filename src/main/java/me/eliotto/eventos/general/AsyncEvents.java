package me.eliotto.eventos.general;

import me.eliotto.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.HashMap;

public class AsyncEvents implements Listener {

    private Main plugin;
    private HashMap<String, FileConfiguration> configs;
    public AsyncEvents(Main plugin) { this.plugin = plugin; this.configs = plugin.getConfigs();}

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        String prefix = configs.get(event.getPlayer().getName()).getString(String.format("%s.Rol", event.getPlayer().getName()));
        event.setFormat(ChatColor.translateAlternateColorCodes('&',
                String.format("&7&l[&r&l%s&7] &r&l%s &r&a➣ &r%s",prefix ,event.getPlayer(), event.getMessage())));
    }
}
