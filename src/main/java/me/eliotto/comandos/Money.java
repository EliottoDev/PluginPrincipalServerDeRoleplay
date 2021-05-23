package me.eliotto.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Money implements CommandExecutor {
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

        return true;
    }
}
