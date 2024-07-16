package com.thesilentnights.delicatex.commands;

import com.thesilentnights.delicatex.DelicateX;
import com.thesilentnights.delicatex.utils.config.Config;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Report implements ICommand {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        boolean anyMatch = DelicateX.getInstance().getServer().getOnlinePlayers().stream().anyMatch(player -> player.isOp());
        if (anyMatch) {
            DelicateX.getInstance().getServer().getOnlinePlayers().forEach(player -> {
                if (player.isOp()) {
                    MessageSender.send(new MessageToSingle("收到来自&4" + commandSender.getName() + "&f的举报:" + strings[0], commandSender));
                }
            });
        }
        Config.getConfig("reports").setList(commandSender.getName(), List.of(strings[0], new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())));
        Config.saveConfigs();
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
