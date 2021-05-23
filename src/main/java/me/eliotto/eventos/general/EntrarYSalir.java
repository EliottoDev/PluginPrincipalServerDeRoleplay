package me.eliotto.eventos.general;

import me.eliotto.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntrarYSalir implements Listener {

    Main plugin;
    public EntrarYSalir(Main plugin){
        this.plugin     = plugin;
        this.configs    = plugin.getConfigs();
    }
    FileConfiguration config = plugin.getConfig();
    HashMap<String, FileConfiguration> configs;


    @EventHandler
    void alEntrar(PlayerJoinEvent event) throws IOException {


        Player player = event.getPlayer();

        if(!configs.containsKey(player.getName())) {

            configs.put(player.getName(), plugin.createConfig(player.getName()));
            FileConfiguration pc = configs.get(player.getName());

            pc.set(
                    String.format("%s.UUID", player.getName()),
                    player.getUniqueId()
            );

            pc.set(
                    String.format("%s.IP", player.getName()),
                    player.getAddress().getAddress().getAddress().toString()
            );

            pc.set(
                    String.format("%s.IsArrested", player.getName()),
                    false
            );

            pc.set(
                    String.format("%s.ArrestedData.Cell", player.getName()),
                    "None"
            );

            pc.set(
                    String.format("%s.ArrestedData.Time", player.getName()),
                    "None"
            );

            pc.set(
                    String.format("%s.Money", player.getName()),
                    100
            );

            pc.set(
                    String.format("%s.Rol"),
                    "None"
            );

            List<String> byDefault = new ArrayList<>();

            byDefault.add("TGMCraft.play");

            pc.set(
                    String.format("%s.Permisos"),
                    byDefault
            );

            pc.save(String.format("%s.yml", player.getName()));
            config.set("Jugadores", config.getStringList("Jugadores").add(player.getName()));
        }
        player.setTexturePack("");
        event.setJoinMessage(ChatColor.GOLD+"["+ChatColor.GREEN+"+"+ChatColor.GOLD+"] "+player.getCustomName()+" se ha unido a la partida!!");



    }

    @EventHandler
    void alSalir(PlayerQuitEvent event){

        Player player = event.getPlayer();

        event.setQuitMessage(ChatColor.GOLD+"["+ChatColor.RED+"-"+ChatColor.GOLD+"] "+player.getCustomName()+" ha salido de la partida!!");



    }
}
