package com.thesilentnights.delicatex.commands;

import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class EntityClear implements DelicateCommand {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player sender) {
            if (strings.length != 4) {
                MessageSender.send(new MessageToSingle("输入坐标范围错误", sender));
                return true;
            }


            double x, y, z;
            x = Double.parseDouble(strings[0]);
            y = Double.parseDouble(strings[1]);
            z = Double.parseDouble(strings[2]);

            Location blockLocation = sender.getLocation().toBlockLocation();
            Collection<Entity> nearbyEntities = blockLocation.getNearbyEntities(x, y, z);

            if (strings[3].equals("player")) {
                nearbyEntities.forEach(entity -> {
                    if (entity.getType().getKey().getKey().equals(strings[3])) {
                        Player player = (Player) entity;
                        player.kick(Component.text("kicked by admin"));
                        MessageSender.send(new MessageToSingle("删除了:" + entity.getType().getKey(), sender));
                    }
                });
                return true;
            }

            nearbyEntities.forEach(entity -> {
                if (entity.getType().getKey().getKey().equals(strings[3])) {
                    entity.remove();
                    MessageSender.send(new MessageToSingle("删除了:" + entity.getType().getKey(), sender));
                }
            });
            return true;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
