package me.eliotto.items.general;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PrioridadAlta extends ItemStack {

    public PrioridadAlta(){

        super(Material.RED_WOOL);

        ItemMeta meta = this.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes(
                '&',
                "&4&lPrioridad Alta"
        ));

        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.translateAlternateColorCodes(
                '&',
                "Envia al arrestado a una prision de alta prioridad"
        ));

        meta.setLore(lore);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        this.setItemMeta(meta);
    }
}
