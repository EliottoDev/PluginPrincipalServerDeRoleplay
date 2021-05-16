package me.eliotto.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Enciclopedia implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player))
            return false;

        Player player = (Player) sender;

        me.eliotto.items.general.Enciclopedia enciclopedia = new me.eliotto.items.general.Enciclopedia();

        player.openBook(enciclopedia);

        return true;
    }


}
