package me.eliotto.eventos.policia;

import me.eliotto.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Arrestar implements Listener {

    public Main plugin;
    public Arrestar(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void SiTePegaLucia(PlayerInteractEntityEvent event){

        Player player = event.getPlayer();



    }
}
