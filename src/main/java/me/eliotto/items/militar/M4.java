package me.eliotto.items.militar;

import me.eliotto.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class M4 extends ItemStack {


    public M4(){
        super(Material.BOW);

        ItemMeta meta = this.getItemMeta();

        meta.setCustomModelData(982453);
        meta.setDisplayName(ChatColor.GREEN+"M4");

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.DARK_PURPLE+"\nPulsa click derecho para disparar!!");


        meta.setLore(lore);

        this.setItemMeta(meta);
    }

    public ShapedRecipe getRecipe(Main plugin){

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "assault_rifle_m4"), this);

        return recipe;
    }
}
