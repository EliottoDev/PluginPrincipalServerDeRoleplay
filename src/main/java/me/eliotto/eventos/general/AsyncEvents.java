package me.eliotto.eventos.general;

import me.eliotto.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class AsyncEvents implements Listener {

    private Main plugin;
    private FileConfiguration roles;
    public AsyncEvents(Main plugin) { this.plugin = plugin; this.roles = plugin.getRoles();}

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        String prefix = roles.getString(event.getPlayer().getName());
        event.setFormat(ChatColor.translateAlternateColorCodes('&',
                String.format("[%s] %s ➣ %s",prefix ,event.getPlayer(), event.getMessage())));
    }

    @EventHandler
    public void onPlayerPreLogin(AsyncPlayerPreLoginEvent event){

    }
}
