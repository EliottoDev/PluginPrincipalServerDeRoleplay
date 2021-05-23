package me.eliotto.comandos;

import me.eliotto.Main;
import me.eliotto.eventos.policia.Arrestar;
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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ArrestarComando implements CommandExecutor {

    public Inventory prioridades;
    public String[] results = new String[2];
    public Player arrestado;
    public Inventory tiempo;
    public Main plugin;

    public ArrestarComando(Main plugin){
        this.plugin = plugin;
    }


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

            if(event.getCurrentItem().getClass() == Skip.class)
               return;

            if(event.getCurrentItem().getClass() == PrioridadBaja.class)
                results[0] = "Baja";
            if(event.getCurrentItem().getClass() == PrioridadMedia.class)
                results[0] = "Media";
            if(event.getCurrentItem().getClass() == PrioridadAlta.class)
                results[0] = "Alta";

            event.getWhoClicked().closeInventory();

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
                                    (x == 1) ? new ItemStack(Material.CYAN_WOOL)       :
                                    (x == 2) ? new ItemStack(Material.LIGHT_BLUE_WOOL) :
                                    (x == 3) ? new ItemStack(Material.LIME_WOOL)       :
                                    (x == 4) ? new ItemStack(Material.GREEN_WOOL)      :
                                    (x == 5) ? new ItemStack(Material.YELLOW_WOOL)     :
                                    (x == 6) ? new ItemStack(Material.ORANGE_WOOL)     :
                                    (x == 7) ? new ItemStack(Material.RED_WOOL)        : null;

                    ItemMeta meta = wooltime.getItemMeta();

                    meta.setDisplayName(ChatColor.translateAlternateColorCodes(
                            '&',
                            String.format("Arrestar durante %d dias", x)
                    ));

                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
                    meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
                    meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                    meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    
                    wooltime.setItemMeta(meta);
                    
                    inventory.setItem(i, wooltime);

                    x++;
                }else{
                    inventory.setItem(i, new Skip());
                }
            }


            this.tiempo = inventory;
            event.getWhoClicked().openInventory(inventory);
        }
        
        
        if(event.getInventory() == this.tiempo){
            
            if(event.getCurrentItem().getClass() == Skip.class)
                return;
            
            
            ItemStack itemClicked = event.getCurrentItem();

            /*
            * Cyan          ->      1 dia       ->      20mins      ->
            * Light blue    ->      2 dias      ->      40mins      ->
            * Lime          ->      3 dias      ->      60mins      ->
            * Green         ->      4 dias      ->      80mins      ->
            * Yellow        ->      5 dias      ->      100mins     ->
            * Orange        ->      6 dias      ->      120mins     ->
            * Red           ->      7 dias      ->      140mins     ->
            * */

            if(itemClicked.getType() == Material.CYAN_WOOL)
                this.results[1] = "1";
            if(itemClicked.getType() == Material.LIGHT_BLUE_WOOL)
                this.results[1] = "2";
            if(itemClicked.getType() == Material.LIME_WOOL)
                this.results[1] = "3";
            if(itemClicked.getType() == Material.GREEN_WOOL)
                this.results[1] = "4";
            if(itemClicked.getType() == Material.YELLOW_WOOL)
                this.results[1] = "5";
            if(itemClicked.getType() == Material.ORANGE_WOOL)
                this.results[1] = "6";
            if(itemClicked.getType() == Material.RED_WOOL)
                this.results[1] = "7";

            event.getWhoClicked().closeInventory();





            
        }
    }
}
