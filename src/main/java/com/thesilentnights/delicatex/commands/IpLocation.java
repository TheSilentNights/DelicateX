package com.thesilentnights.delicatex.commands;

import com.alibaba.fastjson2.JSONObject;
import com.thesilentnights.delicatex.DelicateX;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import com.thesilentnights.delicatex.impl.request.RequestLimit;
import com.thesilentnights.delicatex.impl.request.RequestSender;
import com.thesilentnights.delicatex.impl.tick.TickTimer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class IpLocation implements DelicateCommand {
    public static final String COMMAND_NAME = "IpLocation";
    //请求限制
    private final IpLocationRequestLimit ipLocationRequestLimit;

    public IpLocation() {
        this.ipLocationRequestLimit = new IpLocationRequestLimit();
        ipLocationRequestLimit.setTimerAsynchronouslyStart(0, 60);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = DelicateX.getInstance().getServer().getPlayer(strings[0]);
        if (player == null) {
            MessageSender.send(new MessageToSingle("该玩家不存在", commandSender));
            return true;
        }
        if (strings[0].equals("ip")) {
            MessageSender.send(new MessageToSingle(player.getAddress().getHostString(), commandSender));
            return true;
        }
        IpLocationRunnable ipLocationRunnable = new IpLocationRunnable(player, commandSender, ipLocationRequestLimit);
        Thread thread = new Thread(ipLocationRunnable);
        thread.start();
        return false;
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

class IpLocationRequestLimit extends TickTimer implements RequestLimit {
    private int count;

    public IpLocationRequestLimit() {
        this.count = 0;
    }

    @Override
    synchronized public boolean ifCan() {
        if (count < 46) {
            count++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run() {
        this.count = 0;
    }
}

class IpLocationRunnable implements Runnable {
    private Player player;
    private CommandSender commandSender;
    private IpLocationRequestLimit ipLocationRequestLimit;

    public IpLocationRunnable(Player player, CommandSender commandSender, IpLocationRequestLimit ipLocationRequestLimit) {
        this.player = player;
        this.commandSender = commandSender;
        this.ipLocationRequestLimit = ipLocationRequestLimit;
    }

    @Override
    public void run() {
        try {
            String response = new RequestSender("ip-api.com", "json", player.getAddress().getHostString() + "?fields=status,country,city,query", ipLocationRequestLimit).sendGet();
            if (response == null) {
                MessageSender.send(new MessageToSingle("请求过于频繁，请1分钟后再试", commandSender));
                return;
            }

            JSONObject jsonObject = JSONObject.parseObject(response);

            if (jsonObject.getString("status").equals("fail")) {
                MessageSender.send(new MessageToSingle("请求失败", commandSender));
                return;
            }

            MessageSender.send(new MessageToSingle(jsonObject.getString("country"), commandSender));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}