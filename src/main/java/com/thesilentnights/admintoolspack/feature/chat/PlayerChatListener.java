package com.thesilentnights.admintoolspack.feature.chat;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChannelEvent;

import com.thesilentnights.admintoolspack.AdminToolsPack;


public class PlayerChatListener implements Listener {
    private Boolean isEnabled;

    public PlayerChatListener() {
        this.isEnabled = AdminToolsPack.getDefaultConfig().getBoolean("if-enable-chat_color");
    }

    @EventHandler
    public void PlayerChat(PlayerChannelEvent event) {
        if (isEnabled) {
            event.getChannel().replace('&', ChatColor.COLOR_CHAR);
        }
    }
}
