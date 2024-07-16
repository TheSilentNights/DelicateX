package com.thesilentnights.openchestanywhere.utils.messageSender.messageImp;

import com.thesilentnights.openchestanywhere.utils.color.ChatColorFormatter;
import com.thesilentnights.openchestanywhere.utils.messageSender.Message;
import org.bukkit.command.CommandSender;

public class MessageToSingle extends Message {

    public MessageToSingle(String body, CommandSender targetPlayer) {
        this.targetPlayer = targetPlayer;
        this.body = body;
    }

    @Override
    protected void send() {
        this.targetPlayer.sendMessage(ChatColorFormatter.replace("&a[OpenChestAnywhere]:&f "+body));
    }
}
