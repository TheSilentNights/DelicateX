package com.thesilentnights.delicatex.feature.help;

import com.thesilentnights.delicatex.model.ICommand;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Helper implements ICommand {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> list = List.of(
                "/broadcast [message]",
                "/cdk create [item](需要空时填入none) [money](需要空时填入none) [cdk](兑换码) [expire](过期时间/s)",
                "/cdk remove [cdk](兑换码)",
                "/distance (两次使用，记录两点坐标)",
                "/enchant enchantment level(最大255)",
                "/entityClear [x] [y] [z] (均为范围) type(实体种类，凋落物为item)",
                "/entityCounter [x] [y] [z] [type](可选参数，获取指定种类实体计数)",
                "/inventoryViewer(简写open) [targetPlayer]",
                "/ipLocation [targetPlayer]",
                "/privateMsg [targetPlayer] [message]",
                "/scheduledReboot time [if-broadcast]"
        );
        list.forEach(string -> MessageSender.send(new MessageToSingle(string, commandSender)));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of("");
    }
}
