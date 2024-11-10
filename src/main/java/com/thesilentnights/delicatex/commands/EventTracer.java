package com.thesilentnights.delicatex.commands;

import com.thesilentnights.delicatex.impl.tracer.EventTracerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EventTracer implements DelicateCommand {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = Bukkit.getServer().getPlayer(strings[1]);
        if (player == null) {
            return false;
        }
        switch (strings[0]) {
            case "create" -> {
                EventTracerManager.create(commandSender, player);
            }
            case "remove" -> {
                EventTracerManager.remove(commandSender);
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 0) {
            return List.of("create", "remove");
        } else if (strings.length == 1) {
            return List.of("[name]");
        } else {
            return null;
        }
    }
}
