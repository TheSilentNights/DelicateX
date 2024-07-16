package com.thesilentnights.delicatex.listeners;

import com.thesilentnights.delicatex.utils.color.ChatColorFormatter;
import com.thesilentnights.delicatex.utils.config.Config;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToALL;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;


public class PlayerChatListener implements Listener {
    private Boolean isEnabled;

    public PlayerChatListener() {
        this.isEnabled = Config.getConfig("config").getBoolean("if-enable-chat_color");
    }

    @EventHandler
    public void PlayerChat(PlayerChatEvent event) {
        event.setCancelled(true);
        if (isEnabled) {
            String content = ChatColorFormatter.replace(event.getMessage());
            MessageSender.send(new MessageToALL(content));
        }
    }
}
