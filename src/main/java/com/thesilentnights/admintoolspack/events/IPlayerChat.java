package com.thesilentnights.admintoolspack.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChannelEvent;

import com.thesilentnights.admintoolspack.AdminToolsPack;


public class IPlayerChat implements Listener {
    private Boolean isEnabled;
    public IPlayerChat() {
        this.isEnabled = AdminToolsPack.getDefaultConfig().getBoolean("if-enable-chat_color");
    }
    @EventHandler
    public void PlayerChat(PlayerChannelEvent event){
        if (isEnabled){
            event.getChannel().replace('&',ChatColor.COLOR_CHAR);
        }
    }
}
