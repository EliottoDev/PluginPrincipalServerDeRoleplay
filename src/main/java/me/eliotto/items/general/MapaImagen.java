package me.eliotto.items.general;

import me.eliotto.Main;
import me.eliotto.renderers.LogoRenderer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MapaImagen extends ItemStack {

    public MapaImagen(){
        super(Material.FILLED_MAP);

        MapMeta meta = (MapMeta) this.getItemMeta();

    }

    public ShapelessRecipe getRecipe(Main plugin){

        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(plugin, ""), this);

        return recipe;
    }
}
