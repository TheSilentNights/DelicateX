package com.thesilentnights.delicatex.utils.messageSender.messageImp;

import com.thesilentnights.delicatex.utils.color.ChatColorFormatter;
import com.thesilentnights.delicatex.utils.messageSender.Message;
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
        from.sendMessage(ChatColorFormatter.replace("&dTo "+"&a"+to.getName()+"&f: "+body));
        to.sendMessage(ChatColorFormatter.replace("&5From "+"&a"+from.getName()+"&f: "+body));
    }
}
