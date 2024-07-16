package com.thesilentnights.delicatex.utils.messageSender.messageImp;

import com.thesilentnights.delicatex.utils.color.ChatColorFormatter;
import com.thesilentnights.delicatex.utils.messageSender.Message;
import org.bukkit.command.CommandSender;

public class MessageToSingle extends Message {

    public MessageToSingle(String body, CommandSender targetPlayer) {
        this.targetPlayer = targetPlayer;
        this.body = body;
    }

    @Override
    protected void send() {
        this.targetPlayer.sendMessage(ChatColorFormatter.replace("&a[DelicateX]:&f "+body));
    }
}
