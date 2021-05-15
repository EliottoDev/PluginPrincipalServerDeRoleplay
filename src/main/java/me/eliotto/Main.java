package me.eliotto;

import me.eliotto.eventos.policia.Arrestar;
import me.eliotto.items.Esposas;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {


    String PREFIJO_NOMBRE = ChatColor.GRAY+"["+ChatColor.BLUE+getName()+ChatColor.GRAY+"]";

    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(PREFIJO_NOMBRE+ChatColor.GREEN+" El plugin ha sido iniciado correctamente");
        registerEvents();
        registerRecipes();
    }

    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(PREFIJO_NOMBRE+ChatColor.GREEN+" El plugin ha sido cerrado correctamente");

    }

    public void registerRecipes(){
        new Esposas().getRecipe();
    }


    public void registerEvents(){

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new Arrestar(this), this);

    }
}
