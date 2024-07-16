package com.thesilentnights.delicatex.feature.entity;

import com.thesilentnights.delicatex.DelicateX;
import com.thesilentnights.delicatex.model.ICommand;
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

public class InventoryViewer implements ICommand {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (Bukkit.getPluginManager().isPluginEnabled("OpenChestAnyWhere")) {
            MessageSender.send(new MessageToSingle("我们建议您使用OpenChestAnyWhere进行inventory操作,它包含更多功能", commandSender));
        }

        if (strings.length != 1) {
            MessageSender.send(new MessageToSingle("参数错误", commandSender));
            return true;
        }
        Player target = DelicateX.getInstance().getServer().getPlayer(strings[0]);

        if (commandSender instanceof Player sender && target != null && target.isOnline()) {
            sender.openInventory(target.getInventory());
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> list = new ArrayList<>();
        DelicateX.getInstance().getServer().getOnlinePlayers().forEach(player -> list.add(player.getName()));
        return list;
    }
}
