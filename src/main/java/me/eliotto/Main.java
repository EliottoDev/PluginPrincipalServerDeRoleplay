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
import java.util.HashMap;
import java.util.logging.Logger;

public class Main extends JavaPlugin {


    public String getPrefijoNombre() {
        return PREFIJO_NOMBRE;
    }

    @Override
    public FileConfiguration getConfig() {
        return config;
    }

    public HashMap<String, FileConfiguration> getConfigs() {
        return configs;
    }

    private final String PREFIJO_NOMBRE = ChatColor.GRAY+"["+ChatColor.BLUE+getName()+ChatColor.GRAY+"]";
    private final FileConfiguration config;
    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private HashMap<String, FileConfiguration> configs;



    public FileConfiguration getCeldas() {
        return celdas;
    }

    private FileConfiguration celdas;

    private Thread bot;

    {
        config = getConfig();
    }


    boolean initbot = false;

    @Override
    public void onEnable() {

        log.info(ChatColor.translateAlternateColorCodes('&', PREFIJO_NOMBRE+" &aIniciado plugin"));

        if(initbot){
            try {
                this.bot = new Thread(new Bot(new Json(), log, this));
                this.bot.start();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            log.info(ChatColor.translateAlternateColorCodes('&',
                    String.format("%s &aBot del server activado", PREFIJO_NOMBRE)));
        }
        else {
            log.info(ChatColor.translateAlternateColorCodes('&',
                    String.format("%s &eBot del server no activado", PREFIJO_NOMBRE)));
        }

        if (!setupEconomy() ) {
            log.severe(ChatColor.translateAlternateColorCodes('&', PREFIJO_NOMBRE+" &cVault no encontrado, plugin no iniciado"));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }else{
            log.info(ChatColor.translateAlternateColorCodes('&', PREFIJO_NOMBRE+" &aVault encontrado correctamente, plugin iniciado con exito!!"));
        }

        log.info(ChatColor.translateAlternateColorCodes(
                '&',
                    "&a _________  ________  _____ ______   ________  ________  ________  ________ _________   \n" +
                            "|\\___   ___\\\\   ____\\|\\   _ \\  _   \\|\\   ____\\|\\   __  \\|\\   __  \\|\\  _____\\\\___   ___\\ \n" +
                            "\\|___ \\  \\_\\ \\  \\___|\\ \\  \\\\\\__\\ \\  \\ \\  \\___|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\__/\\|___ \\  \\_| \n" +
                            "     \\ \\  \\ \\ \\  \\  __\\ \\  \\\\|__| \\  \\ \\  \\    \\ \\   _  _\\ \\   __  \\ \\   __\\    \\ \\  \\  \n" +
                            "      \\ \\  \\ \\ \\  \\|\\  \\ \\  \\    \\ \\  \\ \\  \\____\\ \\  \\\\  \\\\ \\  \\ \\  \\ \\  \\_|     \\ \\  \\ \n" +
                            "       \\ \\__\\ \\ \\_______\\ \\__\\    \\ \\__\\ \\_______\\ \\__\\\\ _\\\\ \\__\\ \\__\\ \\__\\       \\ \\__\\\n" +
                            "        \\|__|  \\|_______|\\|__|     \\|__|\\|_______|\\|__|\\|__|\\|__|\\|__|\\|__|        \\|__|\n" +
                            "                                                                                         "
        ));

        this.celdas     = createConfig("celdas.yml");

        configs = new HashMap<>();

        for (String jugador : config.getStringList("Jugadores"))
            configs.put(jugador, createConfig(jugador));


        registerEvents();
        registerCommands();
        registerRecipes();

    }

    @Override
    public void onDisable() {

        log.info(PREFIJO_NOMBRE+ChatColor.RED+" El plugin ha sido cerrado correctamente");


        this.bot.interrupt();
        log.info(PREFIJO_NOMBRE+ChatColor.RED+" Bot apagado correctamente");

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
