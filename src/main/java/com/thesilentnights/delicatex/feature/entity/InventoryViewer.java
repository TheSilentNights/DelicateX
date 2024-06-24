package com.thesilentnights.delicatex.feature.entity;

import com.thesilentnights.delicatex.DelicateX;
import com.thesilentnights.delicatex.model.ICommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class InventoryViewer implements ICommand {
    public static final String COMMAND_NAME = "InventoryViewer";

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player target = DelicateX.getInstance().getServer().getPlayer(strings[2]);

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
