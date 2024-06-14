package com.thesilentnights.delicatex.utils.messageSender.messageImp;

import com.thesilentnights.delicatex.utils.messageSender.Message;
import org.bukkit.command.CommandSender;

public class MessageToSender extends Message {

    public MessageToSender(String body, CommandSender targetPlayer) {
        this.targetPlayer = targetPlayer;
        this.body = body;
    }

    @Override
    protected void send() {
        this.targetPlayer.sendMessage("[私聊消息]->" + body);
    }
}
