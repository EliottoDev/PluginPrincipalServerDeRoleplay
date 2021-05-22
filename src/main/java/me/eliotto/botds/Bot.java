package me.eliotto.botds;

import me.eliotto.botds.data.Json;
import me.eliotto.botds.listeners.MessageCreateEvent;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Bot implements Runnable{

    private final String TOKEN;
    private final Json json;
    private final String ip;

    public Bot(Json json) throws IOException, ParseException {
        TOKEN = (String) json.getConfig().get("token");
        this.json = json;
        this.ip = json.getServerIP();
    }

    @Override
    public void run() {


        DiscordApi api = new DiscordApiBuilder().setToken(TOKEN).login().join();

        api.addMessageCreateListener(new MessageCreateEvent(json));



    }
}
