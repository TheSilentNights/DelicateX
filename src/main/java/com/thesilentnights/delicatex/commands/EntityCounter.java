package com.thesilentnights.delicatex.commands;

import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class EntityCounter implements DelicateCommand {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player sender = (Player) commandSender;
            if (strings.length != 3 && strings.length != 4) {
                MessageSender.send(new MessageToSingle("输入坐标范围错误", sender));
                return false;
            }

            double x, y, z;
            x = Double.parseDouble(strings[0]);
            y = Double.parseDouble(strings[1]);
            z = Double.parseDouble(strings[2]);

            Location blockLocation = sender.getLocation().toBlockLocation();
            Collection<org.bukkit.entity.Entity> nearbyEntities = blockLocation.getNearbyEntities(x, y, z);

            Map<String, Integer> map = com.thesilentnights.delicatex.utils.entity.EntityCounter.execute(nearbyEntities);
            if (strings.length == 3) {
                map.forEach((k, v) -> {
                    MessageSender.send(new MessageToSingle(k + " : " + v.toString(), sender));
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
