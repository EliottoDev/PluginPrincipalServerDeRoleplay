package me.eliotto.items.policia;

import me.eliotto.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Taser extends ItemStack {

    public Taser(){


        super(Material.BOW);

        ItemMeta meta = this.getItemMeta();


        meta.setCustomModelData(528349);

    }


    public ShapedRecipe getRecipe(Main plugin){

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, ""), this);


        return recipe;
    }
}
