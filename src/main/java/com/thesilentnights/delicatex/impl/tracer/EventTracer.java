package com.thesilentnights.delicatex.impl.tracer;

import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EventTracer {
    private final Player targetPlayer;
    private final CommandSender createdBy;

    public EventTracer(Player targetPlayer, CommandSender createdBy) {
        this.targetPlayer = targetPlayer;
        this.createdBy = createdBy;
    }

    //监听开始
    public void listen() {
        EventTracerManager.listener.bind(this, targetPlayer);
    }

    //监听到事件执行回调
    public void callBack(String eventName) {
        MessageSender.send(new MessageToSingle(targetPlayer.name() + "触发了 : " + eventName, createdBy));
    }

    public CommandSender getCreator(){
        return createdBy;
    }
}
