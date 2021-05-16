package me.eliotto.items.militar;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Escopeta extends ItemStack {


    public Escopeta(){

        super(Material.CROSSBOW);

        CrossbowMeta meta = (CrossbowMeta) this.getItemMeta();

        meta.setCustomModelData(672385);

        meta.setDisplayName(ChatColor.RED+"Cazadora de Aliens");


    }

    public ShapedRecipe getRecipe(){

    }
}
