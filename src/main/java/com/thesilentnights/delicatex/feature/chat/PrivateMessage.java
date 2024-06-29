package com.thesilentnights.delicatex.feature.chat;

import com.thesilentnights.delicatex.DelicateX;
import com.thesilentnights.delicatex.model.ICommand;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.PrivateChatMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PrivateMessage implements ICommand {
    public static final String COMMAND_NAME = "PrivateMsg";


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length!= 2){
            MessageSender.sendMessage(new MessageToSingle("参数错误",commandSender));
            return true;
        }
        boolean ifHas = DelicateX.getInstance().getServer().getOnlinePlayers().stream().anyMatch(player -> player.getName().equals(strings[0]));
            if (ifHas){
                MessageSender.sendMessage(new PrivateChatMessage(DelicateX.getInstance().getServer().getPlayer(strings[0]),commandSender,strings[1]));
            }else{
                MessageSender.sendMessage(new MessageToSingle("玩家不在线或不存在",commandSender));
            }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 1){
            List<String> list = new ArrayList<>();
            DelicateX.getInstance().getServer().getOnlinePlayers().forEach(player -> {
                if (!player.getName().equals(commandSender.getName())) {
                    list.add(player.getName());
                }
            });
            return list;
        }
        if (strings.length == 2){
            return List.of("[message]");
        }
        return List.of();
    }
}
