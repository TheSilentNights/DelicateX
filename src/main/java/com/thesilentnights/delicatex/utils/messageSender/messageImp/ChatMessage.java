package com.thesilentnights.delicatex.utils.messageSender.messageImp;

import com.thesilentnights.delicatex.DelicateX;
import com.thesilentnights.delicatex.utils.messageSender.Message;
import org.bukkit.entity.Player;

public class ChatMessage extends Message {
    private Player from;

    public ChatMessage(String body, Player from) {
        this.body = body;
        this.from = from;
    }

    @Override
    protected void send() {
        DelicateX.getInstance().getServer().getOnlinePlayers().forEach(player -> player.sendMessage("["+from.getName()+"]"+body));
    }
}
