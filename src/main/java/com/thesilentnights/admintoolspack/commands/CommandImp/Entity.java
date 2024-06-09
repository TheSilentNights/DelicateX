package com.thesilentnights.admintoolspack.commands.CommandImp;

import com.thesilentnights.admintoolspack.commands.ICommand;
import com.thesilentnights.admintoolspack.utils.entity.EntityCounter;
import com.thesilentnights.admintoolspack.utils.messageSender.MessageSender;
import com.thesilentnights.admintoolspack.utils.messageSender.messageImp.MessageToPlayer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entity extends ICommand {
    public static final String COMMAND_NAME = "Entity";

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player sender = (Player) commandSender;
            if (strings.length != 3) {
                MessageSender.sendMessage(new MessageToPlayer("输入坐标范围错误", sender));
                return false;
            }

            double x, y, z;
            x = Double.parseDouble(strings[0]);
            y = Double.parseDouble(strings[1]);
            z = Double.parseDouble(strings[2]);

            Location blockLocation = sender.getLocation().toBlockLocation();
            Collection<org.bukkit.entity.Entity> nearbyEntities = blockLocation.getNearbyEntities(x, y, z);

            Map<String, Integer> map = EntityCounter.execute(nearbyEntities);

            map.forEach((k, v) -> {
                MessageSender.sendMessage(new MessageToPlayer(k + " : " + v.toString(), sender));
            });
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of("1 1 1");
    }
}
