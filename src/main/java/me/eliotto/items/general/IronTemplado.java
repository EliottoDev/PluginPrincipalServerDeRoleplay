package me.eliotto.items.general;

import me.eliotto.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class IronTemplado extends ItemStack {

    public IronTemplado(){

        super(Material.IRON_INGOT);

        this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

        ItemMeta meta = this.getItemMeta();

        meta.setDisplayName("Iron Templado");

        this.setItemMeta(meta);

    }

    public FurnaceRecipe getRecipe(Main plugin){
        return new FurnaceRecipe(new NamespacedKey(plugin, "iron_templado"), this, Material.IRON_BLOCK, (float) 1.4, 10);
    }
}
