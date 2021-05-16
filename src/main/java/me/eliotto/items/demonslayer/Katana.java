package me.eliotto.items.demonslayer;

import me.eliotto.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class Katana extends ItemStack {

    public Katana(){


        super(Material.DIAMOND_SWORD);


    }

    public ShapedRecipe getRecipe(Main plugin){

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, ""), this);

        return recipe;
    }
}
