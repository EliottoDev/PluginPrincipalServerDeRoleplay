package me.eliotto.items.ladron;

import me.eliotto.Main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class MascaraInvisibilidad extends ItemStack {

    private Main plugin;

    public MascaraInvisibilidad(Main plugin){

        super(Material.LEATHER_HELMET);
        this.plugin = plugin;
        LeatherArmorMeta meta = (LeatherArmorMeta) this.getItemMeta();

    }
}
