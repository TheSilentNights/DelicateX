package com.thesilentnights.admintoolspack.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChannelEvent;

import com.thesilentnights.admintoolspack.AdminToolsPack;


public class IPlayerChatEvent implements Listener {
    @EventHandler
    public void PlayerChat(PlayerChannelEvent event){
        if (AdminToolsPack ) {
            
        }
        event.getChannel().replace('&', ChatColor.COLOR_CHAR);
    }

    
}
