package me.eliotto.items.demonslayer;

import me.eliotto.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Katana extends ItemStack {

    public Katana(){


        super(Material.DIAMOND_SWORD);


        ItemMeta meta = this.getItemMeta();
        meta.setCustomModelData(327487);

        this.setItemMeta(meta);

    }

    public ShapedRecipe getRecipe(Main plugin){

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "katana"), this);

        return recipe;
    }
}
