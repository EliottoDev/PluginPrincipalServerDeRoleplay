package me.eliotto.items.militar;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class M4 extends ItemStack {


    public M4(){
        super(Material.BOW);

        ItemMeta meta = this.getItemMeta();

        meta.setCustomModelData();

    }

    public ShapedRecipe getRecipe(){

        return recipe;
    }
}
