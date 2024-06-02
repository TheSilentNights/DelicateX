package com.thesilentnights.admintoolspack.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChannelEvent;

import com.thesilentnights.admintoolspack.AdminToolsPack;


public class IPlayerChatEvent implements Listener {
    private Boolean isEnabled;
    public IPlayerChatEvent() {
        this.isEnabled = AdminToolsPack.getDefaultConfig().getBoolean("if-enable-chat_color");
    }
    @EventHandler
    public void PlayerChat(PlayerChannelEvent event){
        event.getChannel().replace('&', ChatColor.COLOR_CHAR);
        if (isEnabled){
            event.getChannel().replace('&',ChatColor.COLOR_CHAR);
        }
    }

    
}
