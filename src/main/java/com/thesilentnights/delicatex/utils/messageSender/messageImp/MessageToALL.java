package com.thesilentnights.delicatex.utils.messageSender.messageImp;

import com.thesilentnights.delicatex.DelicateX;
import com.thesilentnights.delicatex.utils.messageSender.Message;

public class MessageToALL extends Message {

    public MessageToALL(String body) {
        this.body = body;
    }

    @Override
    protected void send() {
        DelicateX.getInstance().getServer().getOnlinePlayers().forEach(player -> {
            player.sendMessage("[全体消息]->"+body);
        });
    }
}
