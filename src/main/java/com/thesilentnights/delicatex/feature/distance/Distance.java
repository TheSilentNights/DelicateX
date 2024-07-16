package com.thesilentnights.delicatex.feature.distance;

import com.thesilentnights.delicatex.model.ICommand;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Distance implements ICommand {
    private final Map<String, Location> recordedLocation = new HashMap<>();
    public static final String COMMAND_NAME = "Distance";

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (recordedLocation.get(player.getName()) != null) {
                Location currentLocation = player.getLocation().toBlockLocation();
                double distance = currentLocation.distance(recordedLocation.get(player.getName()));
                MessageSender.send(new MessageToSingle(Double.toString(distance), player));
                recordedLocation.remove(player.getName());
                return true;
            }
            recordedLocation.put(player.getName(), player.getLocation().toBlockLocation());
            MessageSender.send(new MessageToSingle("再次输入以计算两点位置(以玩家所处坐标取整为准)", player));
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }


}
