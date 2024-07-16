package com.thesilentnights.openchestanywhere.utils.messageSender.messageImp;

import com.thesilentnights.openchestanywhere.OpenChestAnywhere;
import com.thesilentnights.openchestanywhere.utils.color.ChatColorFormatter;
import com.thesilentnights.openchestanywhere.utils.messageSender.Message;

public class MessageToALL extends Message {

    public MessageToALL(String body) {
        this.body = body;
    }

    @Override
    protected void send() {
        OpenChestAnywhere.getInstance().getServer().getOnlinePlayers().forEach(player -> {
            player.sendMessage(ChatColorFormatter.replace("&4[全体消息]: &f"+body));
        });
    }
}
