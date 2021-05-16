package me.eliotto.items.general;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Enciclopedia extends ItemStack {

    public Enciclopedia(){

        super(Material.WRITTEN_BOOK);

        BookMeta meta = (BookMeta) this.getItemMeta();

        meta.setAuthor("Giyuu Tomioka");
        meta.setTitle("Guia para sobrevivir");
        meta.addPage(
                "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        ""
        );
    }
}
