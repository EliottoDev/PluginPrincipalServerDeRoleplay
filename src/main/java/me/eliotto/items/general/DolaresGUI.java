package me.eliotto.items.general;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DolaresGUI extends ItemStack {

    public DolaresGUI(Player player){
        super(Material.EMERALD);

        ItemMeta meta = this.getItemMeta();

        int x = 0;

        for(ItemStack item : player.getInventory().getStorageContents())
            if(item.getClass() == Dolar.class)
                x++;

        meta.setDisplayName(
                ChatColor.translateAlternateColorCodes(
                        '&',
                        String.format(
                                "&aTienes %d$",
                                x
                        )
                )
        );

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        this.setItemMeta(meta);

    }
}
