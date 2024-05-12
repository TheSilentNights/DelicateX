package com.thesilentnights.admintoolspack.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChannelEvent;

public class IPlayerChatEvent implements Listener {
    @EventHandler
    public void PlayerChat(PlayerChannelEvent event){
        event.getChannel().replace('&', ChatColor.COLOR_CHAR);
    }
}
