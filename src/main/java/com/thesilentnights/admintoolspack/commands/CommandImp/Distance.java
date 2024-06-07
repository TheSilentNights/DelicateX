package com.thesilentnights.admintoolspack.commands.CommandImp;

import com.thesilentnights.admintoolspack.commands.ICommand;
import com.thesilentnights.admintoolspack.utils.messageSender.MessageSender;
import com.thesilentnights.admintoolspack.utils.messageSender.messageImp.MessageToPlayer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Distance extends ICommand {
    private final Map<String, Location> recordedLocation = new HashMap<>();

    public Distance(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute(CommandSender commandSender, Command command, String s, String[] strings) {
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
            MessageSender.sendMessage(new MessageToPlayer("再次输入以计算两点位置(以玩家所处坐标取整为准)",player));
        }
        return false;
    }
}
