package me.eliotto;

import me.eliotto.botds.Bot;
import me.eliotto.botds.data.Json;
import me.eliotto.comandos.Enciclopedia;
import me.eliotto.comandos.Imagen;
import me.eliotto.eventos.general.EntrarYSalir;
import me.eliotto.eventos.policia.Arrestar;
import me.eliotto.items.demonslayer.Katana;
import me.eliotto.items.general.IronTemplado;
import me.eliotto.items.militar.M4;
import me.eliotto.items.policia.Esposas;
import me.eliotto.items.policia.Taser;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Main extends JavaPlugin {


    public String PREFIJO_NOMBRE = ChatColor.GRAY+"["+ChatColor.BLUE+getName()+ChatColor.GRAY+"]";
    public FileConfiguration config;
    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;

    public FileConfiguration getRoles() {
        return roles;
    }

    private FileConfiguration roles;

    private Thread bot;

    {
        config = getConfig();
    }


    @Override
    public void onEnable() {

        log.info(ChatColor.translateAlternateColorCodes('&', PREFIJO_NOMBRE+" &aIniciado plugin"));

        try {
            this.bot = new Thread(new Bot(new Json(), log, this));
            this.bot.start();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if (!setupEconomy() ) {
            log.severe(ChatColor.translateAlternateColorCodes('&', PREFIJO_NOMBRE+" &cVault no encontrado, plugin no iniciado"));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }else{
            log.info(ChatColor.translateAlternateColorCodes('&', PREFIJO_NOMBRE+" &aVault encontrado correctamente, plugin iniciado con exito!!"));
        }

        this.roles = createConfig("roles.yml");

        registerEvents();
        registerCommands();
        registerRecipes();

    }

    @Override
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(PREFIJO_NOMBRE+ChatColor.RED+" El plugin ha sido cerrado correctamente");

        this.bot.interrupt();

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

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public void registerCommands(){

        getCommand("enciclopedia").setExecutor(new Enciclopedia());
        getCommand("imagen").setExecutor(new Imagen());
    }


    public FileConfiguration createConfig(String file) {
        File customConfigFile = new File(getDataFolder(), file);
        boolean error = false;
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("custom.yml", false);
        }

        FileConfiguration customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            error = true;
        }

        if(!error)
            return customConfig;
        else
            return null;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
