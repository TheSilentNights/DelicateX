package com.thesilentnights.delicatex.utils.messageSender.messageImp;

import com.thesilentnights.delicatex.utils.color.ChatColorFormatter;
import com.thesilentnights.delicatex.utils.messageSender.Message;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrivateChatMessage extends Message {
    private Player to;
    private CommandSender from;

    public PrivateChatMessage(Player to, CommandSender from, String body) {
        this.to = to;
        this.from = from;
        this.body = body;
    }

    @Override
    protected void send() {
        to.sendMessage(ChatColorFormatter.replace("&a"+from.getName()+"&f->"+body));
    }
}
