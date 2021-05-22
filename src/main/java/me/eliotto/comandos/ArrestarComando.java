package me.eliotto.comandos;

import me.eliotto.items.general.PrioridadAlta;
import me.eliotto.items.general.PrioridadBaja;
import me.eliotto.items.general.PrioridadMedia;
import me.eliotto.items.general.Skip;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ArrestarComando implements CommandExecutor {

    public Inventory prioridades;
    public String[] results = new String[2];
    public Player arrestado;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player))
            return true;

        if(args.length != 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    String.format("&l&7[&2Dios&7] &f&lKamisama &a➣ &rAsi no funciona el comando. Uso /arrestar <nombre>")));
            return true;
        }

        if(!sender.getServer().getPlayer(args[0]).isOnline()){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    String.format("&l&7[&2Dios&7] &f&lKamisama &a➣ &rEl jugador no esta conectado")));
            return true;
        }

        Player player = (Player) sender;

        this.arrestado = player.getServer().getPlayer(args[0]);


        World world = player.getWorld();
        double[] COORD_PLAYER = new double[]{
                Math.round(player.getLocation().getX()),
                Math.round(player.getLocation().getY()),
                Math.round(player.getLocation().getZ())
        };

        double[] COORD_ARRESTED = new double[]{
                Math.round(player.getServer().getPlayer(args[0]).getLocation().getX()),
                Math.round(player.getServer().getPlayer(args[0]).getLocation().getY()),
                Math.round(player.getServer().getPlayer(args[0]).getLocation().getZ())
        };

        double[] vector = new double[]{
                COORD_ARRESTED[0] - COORD_PLAYER[0],
                COORD_ARRESTED[1] - COORD_PLAYER[1],
                COORD_ARRESTED[2] - COORD_PLAYER[2]
        };


        double distancia = Math.sqrt(Math.sqrt(Math.pow(vector[0], 2) + Math.pow(vector[2], 2))+Math.pow(vector[1], 2));

        if(distancia <= 30){
            player.sendMessage(ChatColor.translateAlternateColorCodes(
                    '&',
                    String.format("&l&7[&2Dios&7] &f&lKamisama &e➣ &rEl jugador no esta cerca de ti")
            ));
            return true;
        }

        Inventory inventory = Bukkit.createInventory(
                player,
                27,
                ChatColor.translateAlternateColorCodes(
                        '&',
                        String.format("&f&lArrestar a &e%s", args[0])
                )
        );


        for (int i = 0; i < 27; i++) {

            if(i == 10 || i == 12 || i == 14){

                if(i == 10)
                    inventory.setItem(i, new PrioridadBaja());

                if(i == 12)
                    inventory.setItem(i, new PrioridadMedia());

                if(i == 14)
                    inventory.setItem(i, new PrioridadAlta());

            }else{
                inventory.setItem(i, new Skip());
            }
        }

        player.openInventory(inventory);

        this.prioridades = inventory;

        return true;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){

        event.setCancelled(true);

        if(event.getInventory() == this.prioridades){

            if(event.getCursor().getClass() == Skip.class)
               return;

            if(event.getCursor().getClass() == PrioridadBaja.class)
                results[0] = "Baja";
            if(event.getCursor().getClass() == PrioridadMedia.class)
                results[0] = "Media";
            if(event.getCursor().getClass() == PrioridadAlta.class)
                results[0] = "Alta";

            Inventory inventory = Bukkit.createInventory(
                    event.getWhoClicked(),
                    27,
                    ChatColor.translateAlternateColorCodes(
                            '&',
                            String.format("&f&lArrestar a &e%s", this.arrestado.getName())
                    )
            );

            int x = 1;

            for (int i = 0; i < 27; i++) {
                if(9 < i && i < 17){

                    ItemStack wooltime =
                                    (i == 10) ? new ItemStack(Material.BLUE_WOOL) :
                                    (i == 11) ? new ItemStack(Material.) :
                                    (i == 12) ? new ItemStack(Material.PURPLE_WOOL) :
                                    (i == 13) ? new ItemStack(Material.);
                                    (i == 14) ? new ItemStack(Material.) :
                                    (i == 15) ? new ItemStack(Material.) :
                                    (i == 16) ? new ItemStack(Material.);

                    x++;
                }else{
                    inventory.setItem(i, new Skip());
                }
            }



            event.getWhoClicked().openInventory();
        }


    }
}
