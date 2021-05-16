package me.eliotto.comandos;

import me.eliotto.items.general.MapaImagen;
import me.eliotto.renderers.LogoRenderer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

import java.util.ArrayList;
import java.util.List;

public class Imagen implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(player.getItemInHand().getClass() != MapaImagen.class)
            return true;

        if(player.getActiveItem() == null)
            return true;

        if(args.length == 0) {
            player.sendMessage(ChatColor.GOLD+"Usa /imagen <url> para cambiar la imagen");
            return true;
        }

        MapView view = Bukkit.createMap(player.getWorld());
        view.getRenderers().clear();

        LogoRenderer renderer = new LogoRenderer();
        if(!renderer.load(args[0])){
            player.sendMessage(ChatColor.RED+"ERROR! No se ha podido encontrar la imagen");
            return true;
        }

        view.addRenderer(renderer);
        MapaImagen mapa = (MapaImagen) player.getActiveItem();

        player.getInventory().removeItem(player.getActiveItem());

        MapMeta meta = (MapMeta) mapa.getItemMeta();
        meta.setMapView(view);

        List<String> lore = new ArrayList<String>();

        lore.add("Autor: "+player.getCustomName());
        lore.add("Imagen: "+args[0]);

        meta.setDisplayName("Imagen");
        meta.setLore(lore);
        mapa.setItemMeta(meta);

        player.getInventory().addItem(mapa);

        return true;
    }
}
