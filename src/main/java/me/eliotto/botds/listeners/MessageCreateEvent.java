package me.eliotto.botds.listeners;

import me.eliotto.Main;
import me.eliotto.botds.data.Json;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;
import org.javacord.api.listener.message.MessageCreateListener;

public class MessageCreateEvent implements MessageCreateListener {


    private Json json;
    private Main plugin;

    public MessageCreateEvent(Json json, Main plugin){

        this.plugin = plugin;
        this.json = json;

    }


    @Override
    public void onMessageCreate(org.javacord.api.event.message.MessageCreateEvent event) {
        String content = event.getMessage().getContent();
        Message message = event.getMessage();
        String[] args = (String[]) ArrayUtils.remove(content.split(" "), 0);
        String cmd = content.split(" ")[0];
        User user = event.getMessage().getUserAuthor().get();

        // fact?say <server|player> <arg1> <arg2> ...

        if(cmd == "say"){
            if(args.length >= 1){
//                if(){
//                    String msg = "";
//                    for(String arg : (String[]) ArrayUtils.remove(ArrayUtils.remove(args, 0), 1)){
//                        if(ArrayUtils.indexOf(args, arg) == 1){
//                            msg = arg;
//                        }else{
//                            msg += " "+arg;
//                        }
//                    }
//                    plugin.getServer().getPlayer(args[0]).sendMessage(
//                            ChatColor.translateAlternateColorCodes(
//                                    '&',
//                                    String.format("&7&l[&r&9Discord&7] &r&l%s &r&a➣ &r%s", user.getDisplayName(message.getServer().get()), msg)
//                            )
//                    );
//                }else{
//                    String msg = "";
//                    for(String arg : args){
//                        if(ArrayUtils.indexOf(args, arg) == 0){
//                            msg = arg;
//                        }else{
//                            msg += " "+arg;
//                        }
//                    }
//                    plugin.getServer().broadcastMessage(
//                            ChatColor.translateAlternateColorCodes(
//                                    '&',
//                                    String.format("&7&l[&r&9Discord&7] &r&l%s &r&a➣ &r%s", user.getDisplayName(message.getServer().get()), msg)
//                            )
//                    );
                }
            }
        }
    }
}
