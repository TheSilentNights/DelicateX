package com.thesilentnights.delicatex.commands;

import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityCounter implements DelicateCommand {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player sender) {
            if (strings.length != 3 && strings.length != 4) {
                MessageSender.send(new MessageToSingle("输入坐标范围错误", sender));
                return false;
            }

            Location blockLocation = sender.getLocation().toBlockLocation();
            Collection<Entity> nearbyEntities = null;

            try {
                double x, y, z;
                x = Double.parseDouble(strings[0]);
                y = Double.parseDouble(strings[1]);
                z = Double.parseDouble(strings[2]);
                nearbyEntities = blockLocation.getNearbyEntities(x, y, z);
            } catch (NumberFormatException e) {
                MessageSender.send(new MessageToSingle("坐标格式错误", sender));
                return false;
            }

            Map<String, List<Entity>> map = nearbyEntities.stream()
                    .collect(Collectors.groupingBy(entity -> entity.getType().getKey().getKey()));

            if (strings.length == 3) {
                map.forEach((k, v) -> {
                    MessageSender.send(new MessageToSingle(k + " : " + v.size(), sender));
                });
            } else {
                MessageSender.send(new MessageToSingle(map.get(strings[3]).toString(), sender));
            }

            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of("1 1 1");
    }
}
