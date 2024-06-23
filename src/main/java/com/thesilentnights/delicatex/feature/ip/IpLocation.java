package com.thesilentnights.delicatex.feature.ip;

import com.alibaba.fastjson2.JSONObject;
import com.thesilentnights.delicatex.DelicateX;
import com.thesilentnights.delicatex.feature.model.ICommand;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSender;
import com.thesilentnights.delicatex.utils.request.RequestSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IpLocation implements ICommand {
    public static final String COMMAND_NAME = "IpLocation";

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {


        Player player = DelicateX.getInstance().getServer().getPlayer(strings[0]);
        if (player == null){
            MessageSender.sendMessage(new MessageToSender("该玩家不存在",commandSender));
            return false;
        }
        if (strings[0].equals("ip")){
            MessageSender.sendMessage(new MessageToSender(player.getAddress().getHostString(),commandSender));

            return true;
        }
        try {
            String response = new RequestSender("ip-api.com", "json",player.getAddress().getHostString()+"?fields=status,country,city,query").sendGet();
            JSONObject jsonObject = JSONObject.parseObject(response);

            if (jsonObject.getString("status").equals("success")){
                MessageSender.sendMessage(new MessageToSender(jsonObject.getString("country"),commandSender));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return true
    ;}

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
