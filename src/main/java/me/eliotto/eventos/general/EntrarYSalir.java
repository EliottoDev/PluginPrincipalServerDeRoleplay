package me.eliotto.eventos.general;

import me.eliotto.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EntrarYSalir implements Listener {

    Main plugin;
    public EntrarYSalir(Main plugin){
        this.plugin = plugin;
    }
    FileConfiguration config = plugin.getConfig();

    @EventHandler
    void alEntrar(PlayerJoinEvent event){


        Player player = event.getPlayer();

        player.setTexturePack("");
        event.setJoinMessage(ChatColor.GOLD+"["+ChatColor.GREEN+"+"+ChatColor.GOLD+"] "+player.getCustomName()+" se ha unido a la partida!!");



    }

    @EventHandler
    void alSalir(PlayerQuitEvent event){

        Player player = event.getPlayer();

        event.setQuitMessage(ChatColor.GOLD+"["+ChatColor.RED+"-"+ChatColor.GOLD+"] "+player.getCustomName()+" ha salido de la partida!!");



    }
}
