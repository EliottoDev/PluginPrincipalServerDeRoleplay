package me.eliotto;

import me.eliotto.comandos.Enciclopedia;
import me.eliotto.comandos.Imagen;
import me.eliotto.eventos.general.EntrarYSalir;
import me.eliotto.eventos.policia.Arrestar;
import me.eliotto.items.demonslayer.Katana;
import me.eliotto.items.general.IronTemplado;
import me.eliotto.items.militar.M4;
import me.eliotto.items.policia.Esposas;
import me.eliotto.items.policia.Taser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {


    String PREFIJO_NOMBRE = ChatColor.GRAY+"["+ChatColor.BLUE+getName()+ChatColor.GRAY+"]";
    FileConfiguration config;

    {
        config = getConfig();
    }


    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(PREFIJO_NOMBRE+ChatColor.GREEN+" El plugin ha sido iniciado correctamente");

        registerEvents();
        registerCommands();
        registerRecipes();

    }

    @Override
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(PREFIJO_NOMBRE+ChatColor.RED+" El plugin ha sido cerrado correctamente");

    }

    public void registerRecipes(){
        Bukkit.addRecipe(new Esposas().getRecipe(this));
        Bukkit.addRecipe(new M4().getRecipe(this));
        Bukkit.addRecipe(new Taser().getRecipe(this));
        Bukkit.addRecipe(new Katana().getRecipe(this));
        Bukkit.addRecipe(new IronTemplado().getRecipe(this));
    }


    public void registerEvents(){

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new Arrestar(this), this);
        pm.registerEvents(new EntrarYSalir(this), this);

    }

    public void registerCommands(){

        this.getCommand("enciclopedia").setExecutor(new Enciclopedia());
        this.getCommand("imagen").setExecutor(new Imagen());
    }
}
