package me.eliotto.items.policia;

import me.eliotto.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Esposas extends ItemStack {


    public Esposas(){
        super(Material.CHAIN);

        ItemMeta meta = this.getItemMeta();
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"\nUsa click derecho en alguien para arrestarlo");

        meta.setCustomModelData(111111);
        meta.setDisplayName("Cadenas para esposar");
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(lore);

        this.setItemMeta(meta);
    }

    public ShapedRecipe getRecipe(Main plugin){

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, ""), this);

        recipe.shape(   "IIP",
                        "ICP",
                        "PII");

        recipe.setIngredient('P', Material.IRON_NUGGET);
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('C', Material.CHAIN);

        return recipe;
    }

    public ItemStack get(){
        return this;
    }
}
