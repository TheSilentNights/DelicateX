package com.thesilentnights.admintoolspack.utils.messageSender.messageImp;

import com.thesilentnights.admintoolspack.AdminToolsPack;
import com.thesilentnights.admintoolspack.utils.messageSender.Message;
import com.thesilentnights.admintoolspack.utils.messageSender.Target;
import org.bukkit.entity.Player;

public class MessageToPlayer extends Message {

    public MessageToPlayer(String body, Player targetPlayer) {
        this.target = Target.PLAYER;
        this.targetPlayer = targetPlayer;
        this.body = body;
    }

    @Override
    protected void send() {
        this.targetPlayer.sendMessage("[私聊消息]->"+body);
    }
}
