package com.thesilentnights.delicatex.commands;

import com.thesilentnights.delicatex.DelicateX;
import com.thesilentnights.delicatex.utils.chunk.ChunkLoader;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ChunkLoaderCommand implements DelicateCommand {
    //param: load world x z ifGenerate
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length != 5) {
            return false;
        }

        int x, z;
        boolean ifGenerate;
        try {
            x = Integer.parseInt(strings[2]);
            z = Integer.parseInt(strings[3]);
            ifGenerate = Boolean.parseBoolean(strings[4]);
        } catch (NumberFormatException e) {
            MessageSender.send(new MessageToSingle("坐标错误", commandSender));
            return true;
        }
        World world = DelicateX.getInstance().getServer().getWorld(strings[1]);
        if (world == null) {
            MessageSender.send(new MessageToSingle("世界不存在", commandSender));
            return true;
        }
        switch (strings[0]) {
            case "load" -> {
                ChunkLoader.loadChunk(world, x, z, ifGenerate);
            }
            case "unload" -> {
                ChunkLoader.unloadChunk(world, x, z);
            }
            default -> {
                MessageSender.send(new MessageToSingle("该操作不存在", commandSender));
            }
        }
        return true;

    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        switch (strings.length) {
            case 1 -> {
                return List.of("load", "unload");
            }
            case 2 -> {
                List<String> result = new ArrayList<>();
                DelicateX.getInstance().getServer().getWorlds().forEach(world -> {
                    result.add(world.getName());
                });
                return result;
            }
            case 3 -> {
                return List.of("[x]");
            }
            case 4 -> {
                return List.of("[z]");
            }
            case 5 -> {
                return List.of("[ifGenerate]");
            }
            default -> {
                return List.of("");
            }
        }
    }
}
