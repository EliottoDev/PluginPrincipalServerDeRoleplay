package me.eliotto.eventos.policia;

import me.eliotto.Main;
import me.eliotto.items.Esposas;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Arrestar implements Listener {

    public Main plugin;
    public Arrestar(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void SiTePegaLucia(PlayerInteractEntityEvent event) throws InterruptedException {

        // Obtener los objetivos

        Player clicked = (Player) event.getRightClicked();
        Player player = event.getPlayer();

        // Comprobar si el item que tenga en la mano es exactamente las esposas

        if(player.getItemInHand().getClass() == Esposas.class)
            return;

        // Obtener el punto exacto donde esta

        int[] coords = new int[] {
                clicked.getLocation().getBlockX(),
                clicked.getLocation().getBlockY()+1,
                clicked.getLocation().getBlockZ(),
                Math.round(clicked.getLocation().getYaw()),
                Math.round(clicked.getLocation().getPitch())
        };


        // Atrapar al arrestado

        clicked.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,
                                99999,
                                2,
                                true,
                                false,
                                false
        ));

        World world = clicked.getWorld();
        List<Block> bloques = new ArrayList<Block>();

        // Colocar rejas

        bloques.add(world.getBlockAt(new Location(world, coords[0]+1, coords[1], coords[2])));
        bloques.add(world.getBlockAt(new Location(world, coords[0]-1, coords[1], coords[2])));

        bloques.add(world.getBlockAt(new Location(world, coords[0], coords[1]+1, coords[2])));
        bloques.add(world.getBlockAt(new Location(world, coords[0], coords[1]-1, coords[2])));

        bloques.add(world.getBlockAt(new Location(world, coords[0]+1, coords[1]+1, coords[2])));
        bloques.add(world.getBlockAt(new Location(world, coords[0]-1, coords[1]-1, coords[2])));

        bloques.add(world.getBlockAt(new Location(world, coords[0]+1, coords[1]-1, coords[2])));
        bloques.add(world.getBlockAt(new Location(world, coords[0]-1, coords[1]+1, coords[2])));

        coords[1]++;

        bloques.add(world.getBlockAt(new Location(world, coords[0]+1, coords[1], coords[2])));
        bloques.add(world.getBlockAt(new Location(world, coords[0]-1, coords[1], coords[2])));

        bloques.add(world.getBlockAt(new Location(world, coords[0], coords[1]+1, coords[2])));
        bloques.add(world.getBlockAt(new Location(world, coords[0], coords[1]-1, coords[2])));

        bloques.add(world.getBlockAt(new Location(world, coords[0]+1, coords[1]+1, coords[2])));
        bloques.add(world.getBlockAt(new Location(world, coords[0]-1, coords[1]-1, coords[2])));

        bloques.add(world.getBlockAt(new Location(world, coords[0]+1, coords[1]-1, coords[2])));
        bloques.add(world.getBlockAt(new Location(world, coords[0]-1, coords[1]+1, coords[2])));


        for(Block block : bloques){

            block.setType(Material.IRON_BARS);

        }

        // Notificar

        player.sendMessage(ChatColor.BOLD+""+ChatColor.GREEN+"¡Atrapado! Tienes 60s para interrogarlo antes de que desaparezcan los barrotes");
        clicked.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"¡Atrapado! Ahora seras interrogado por "+player.getCustomName());

        // Esperar

        Thread.sleep(60000);

        // Notificar

        player.sendMessage(ChatColor.BOLD+""+ChatColor.GREEN+"Van a desaparecer los barrotes");
        clicked.sendMessage(ChatColor.GREEN+""+ChatColor.BOLD+"Seras liberado en breves");

        // Liberar

        for(Block block : bloques){

            block.setType(Material.AIR);

        }

    }
}
