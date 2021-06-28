package me.eliotto.botds.listeners;

import me.eliotto.Main;
import me.eliotto.botds.Mensaje;
import me.eliotto.botds.data.Json;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;
import org.javacord.api.listener.message.MessageCreateListener;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
                if(args[0] == "player") {
                    if (plugin.getConfigs().containsKey(plugin.getServer().getPlayer(args[1]).getName())) {
                        String msg = "";
                        for (String arg : (String[]) ArrayUtils.remove(ArrayUtils.remove(args, 0), 1)) {
                            if (ArrayUtils.indexOf(args, arg) == 0) {
                                msg = arg;
                            } else {
                                msg += " " + arg;
                            }
                        }
                        if(plugin.getServer().getPlayer(args[1]).isOnline()){

                            plugin.getServer().getPlayer(args[0]).sendMessage(
                                    ChatColor.translateAlternateColorCodes(
                                            '&',
                                            String.format("&7&l[&r&9Discord&7] &r&l%s &r&a➣ &r%s", user.getDisplayName(message.getServer().get()), msg)
                                    )
                            );
                        }else{
                            List<Mensaje> lista = (List<Mensaje>) plugin.getConfigs().get(plugin.getServer().getPlayer(args[1]).getName())
                                    .getList(String.format("%s.Mensajes", plugin.getServer().getPlayer(args[1]).getName()));
                            lista.add(new Mensaje(msg, user, LocalDateTime.now()));
                            plugin.getConfigs().get(plugin.getServer().getPlayer(args[1]).getName())
                                    .set(String.format("%s.Mensajes", plugin.getServer().getPlayer(args[1]).getName()), lista);
                            try {
                                plugin.getConfigs().get(plugin.getServer().getPlayer(args[1]).getName()).save(
                                        plugin.getServer().getPlayer(args[1]).getName()+".yml");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        String msg = "";
                        for (String arg : args) {
                            if (ArrayUtils.indexOf(args, arg) == 0) {
                                msg = arg;
                            } else {
                                msg += " " + arg;
                            }
                        }
                        plugin.getServer().broadcastMessage(
                                ChatColor.translateAlternateColorCodes(
                                        '&',
                                        String.format("&7&l[&r&9Discord&7] &r&l%s &r&a➣ &r%s", user.getDisplayName(message.getServer().get()), msg)
                                )
                        );
                    }
                }
            }
        }
    }
}
