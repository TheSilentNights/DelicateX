package com.thesilentnights.delicatex.commands;

import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ForceExecute implements DelicateCommand {
    List<String> commandList = new ArrayList<String>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = Bukkit.getServer().getPlayer(strings[0]);
        if (player == null) {
            MessageSender.send(new MessageToSingle("玩家不存在", commandSender));
            return true;
        }
        player.performCommand(strings[1]);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        commandList.clear();
        commandList.addAll(Bukkit.getCommandAliases().keySet());
        return commandList;
    }
}
