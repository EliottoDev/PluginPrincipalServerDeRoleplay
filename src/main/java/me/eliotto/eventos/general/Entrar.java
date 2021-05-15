package me.eliotto.eventos.general;

import me.eliotto.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Entrar implements Listener {

    Main plugin;
    public Entrar(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    void alEntrar(PlayerJoinEvent event){

        FileConfiguration config = plugin.getConfig();
        Player player = event.getPlayer();

        event.setJoinMessage(ChatColor.GOLD+"["+ChatColor.GREEN+"+"+ChatColor.GOLD+"] "+player.getCustomName()+" se ha unido a la partida!!");



    }
}
