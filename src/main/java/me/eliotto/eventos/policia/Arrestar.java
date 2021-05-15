package me.eliotto.eventos.policia;

import me.eliotto.Main;
import me.eliotto.items.Esposas;
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

        Esposas esposas = new Esposas();
        Player player = event.getPlayer();

        if(player.getItemInHand().equals(esposas))
            return;

        Player clicked = (Player) event.getRightClicked();



    }
}
