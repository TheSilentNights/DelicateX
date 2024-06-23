package com.thesilentnights.delicatex.feature.ip;

import com.alibaba.fastjson2.JSONObject;
import com.thesilentnights.delicatex.DelicateX;
import com.thesilentnights.delicatex.feature.model.ICommand;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import com.thesilentnights.delicatex.utils.request.RequestSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class IpLocation implements ICommand {
    public static final String COMMAND_NAME = "IpLocation";

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = DelicateX.getInstance().getServer().getPlayer(strings[0]);
        if (player == null) {
            MessageSender.sendMessage(new MessageToSingle("该玩家不存在", commandSender));
            return false;
        }
        if (strings[0].equals("ip")) {
            MessageSender.sendMessage(new MessageToSingle(player.getAddress().getHostString(), commandSender));
            return true;
        }
        try {
            String response = new RequestSender("ip-api.com", "json", player.getAddress().getHostString() + "?fields=status,country,city,query").sendGet();
            JSONObject jsonObject = JSONObject.parseObject(response);

            if (jsonObject.getString("status").equals("success")) {
                MessageSender.sendMessage(new MessageToSingle(jsonObject.getString("country"), commandSender));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> list = new ArrayList<>();
        for (Player player : DelicateX.getInstance().getServer().getOnlinePlayers()) {
            list.add(player.getName());
        }
        return list;
    }
}
