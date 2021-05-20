package me.eliotto.botds.listeners;

import me.eliotto.botds.data.Json;
import org.apache.commons.lang.ArrayUtils;
import org.javacord.api.listener.message.MessageCreateListener;

public class MessageCreateEvent implements MessageCreateListener {


    private Json json;

    public MessageCreateEvent(Json json){

        this.json = json;

    }


    @Override
    public void onMessageCreate(org.javacord.api.event.message.MessageCreateEvent event) {
        String content = event.getMessage().getContent();
        String[] args = (String[]) ArrayUtils.remove(content.split(" "), 0);
        String cmd = content.split(" ")[0];

        if(cmd == ""){

        }
    }
}
