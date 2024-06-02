package com.thesilentnights.admintoolspack.utils.messageSender.messageImp;

import com.thesilentnights.admintoolspack.AdminToolsPack;
import com.thesilentnights.admintoolspack.utils.messageSender.Message;
import com.thesilentnights.admintoolspack.utils.messageSender.Target;
import org.bukkit.entity.Player;

public class MessageToALL extends Message {

    public MessageToALL(String body) {
        this.target = Target.ALL;
        this.body = body;
    }

    @Override
    protected void send() {
        AdminToolsPack.getInstance().getServer().getOnlinePlayers().forEach(player -> {
            player.sendMessage("[全体消息]->"+body);
        });
    }
}
