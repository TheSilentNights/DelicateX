package com.thesilentnights.admintoolspack.commands;

import com.thesilentnights.admintoolspack.utils.messageSender.MessageSender;
import com.thesilentnights.admintoolspack.utils.messageSender.messageImp.MessageToPlayer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CalculateDistance implements CommandExecutor {
    private Map<String, Location> recordedLocation = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            if (recordedLocation.get(player.getName())!= null){
                Location currentLocation = player.getLocation().toBlockLocation();
                double distance = currentLocation.distance(recordedLocation.get(player.getName()));
                MessageSender.sendMessage(new MessageToPlayer(Double.toString(distance),player));
                recordedLocation.remove(player.getName());
                return true;
            }
            recordedLocation.put(player.getName(),player.getLocation().toBlockLocation());
            return false;
        }
        return false;
    }
}
