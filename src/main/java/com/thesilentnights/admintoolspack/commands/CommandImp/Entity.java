package com.thesilentnights.admintoolspack.commands.CommandImp;

import com.thesilentnights.admintoolspack.commands.ICommand;
import com.thesilentnights.admintoolspack.utils.messageSender.MessageSender;
import com.thesilentnights.admintoolspack.utils.messageSender.messageImp.MessageToPlayer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Entity extends ICommand {

    public Entity(String commandName) {
        super(commandName);
    }

    @Override
    public boolean execute(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player sender = (Player) commandSender;
            if (strings.length != 3){
                MessageSender.sendMessage(new MessageToPlayer("输入坐标范围错误",sender));
                return false;
            }

            double x,y,z;
            x = Double.parseDouble(strings[0]);
            y = Double.parseDouble(strings[1]);
            z = Double.parseDouble(strings[2]);

            Location blockLocation = sender.getLocation().toBlockLocation();
            Collection<org.bukkit.entity.Entity> nearbyEntities = blockLocation.getNearbyEntities(x, y, z);

            Map<String,Integer> map = new HashMap<>();

            nearbyEntities.forEach(entity -> {
                String namespace = entity.getType().getKey().getKey();
                if (map.get(namespace) == null){
                    map.put(namespace,1);
                }else {
                    map.merge(namespace,map.get(namespace),(k,val)->{
                        return val+1;
                    });
                }
            });
            map.forEach((k,v)->{
                MessageSender.sendMessage(new MessageToPlayer(k+" : "+v.toString(),sender));
            });
            return true;
        }
        return false;
    }
}
