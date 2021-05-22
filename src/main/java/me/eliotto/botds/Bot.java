package me.eliotto.botds;

import me.eliotto.Main;
import me.eliotto.botds.data.Json;
import me.eliotto.botds.listeners.MessageCreateEvent;
import org.bukkit.ChatColor;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.logging.Logger;

public class Bot implements Runnable{

    private final String TOKEN;
    private final Json json;
    private final Logger log;
    private final Main plugin;

    public Bot(Json json, Logger log, Main main) throws IOException, ParseException {
        TOKEN = (String) json.getConfig().get("token");
        this.json = json;
        this.log = log;
        this.plugin = main;
    }

    @Override
    public void run() {

        this.log.info(ChatColor.translateAlternateColorCodes('&',
                plugin.PREFIJO_NOMBRE+" &aIniciando el bot de discord"));

        DiscordApi api = new DiscordApiBuilder().setToken(TOKEN).login().join();

        api.addMessageCreateListener(new MessageCreateEvent(json));

    }
}
