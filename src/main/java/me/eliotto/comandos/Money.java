package me.eliotto.comandos;

import me.eliotto.Main;
import me.eliotto.items.general.DolaresGUI;
import me.eliotto.items.general.Skip;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class Money implements CommandExecutor {

    private HashMap<String, FileConfiguration> configs;
    private Inventory inv;
    private Main plugin;

    public Money(Main plugin){ this.plugin = plugin; }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(args.length != 0){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    String.format("&l&7[&2Dios&7] &f&lKamisama &a➣ &rAsi no funciona el comando. Uso /dinero")));
            return true;
        }

        Inventory inventory = Bukkit.createInventory(
                player,
                27,
                ChatColor.translateAlternateColorCodes(
                        '&',
                        String.format(
                                "&6Dinero de &l%s",
                                player.getName()
                        )
                )
        );

        for (int i = 0; i < 27; i++) {
            if(i == 10 || i == 16){
                if(i == 10)
                    inventory.setItem(i, new DolaresGUI(player));

                if(i == 16){

                    ItemStack item = new ItemStack(Material.GOLD_BLOCK);

                    ItemMeta meta = item.getItemMeta();

                    meta.setDisplayName(
                            ChatColor.translateAlternateColorCodes(
                                    '&',
                                    String.format(
                                            "&5Dinero en el banco: &r%x$",
                                            configs.get(player.getName()).getInt(String.format("%s.Money"))
                                    )
                            )
                    );
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
                    meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
                    meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                    meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

                    item.setItemMeta(meta);

                    inventory.setItem(i, item);

                }
            }
            else{
                inventory.setItem(i, new Skip());
            }
        }

        player.openInventory(inventory);

        return true;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){

        if(this.inv == event.getInventory()){
            event.setCancelled(true);
        }

    }

}
