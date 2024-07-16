package com.thesilentnights.delicatex.commands;

import com.thesilentnights.delicatex.DelicateX;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToALL;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import com.thesilentnights.delicatex.utils.task.tick.TickTimer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ScheduledReboot implements ICommand {
    public static final String COMMAND_NAME = "ScheduledReboot";
    private Long scheduledTime;
    private boolean ifBroadcast;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length != 2) {
            return false;
        }

        try {
            scheduledTime = Long.parseLong(strings[0]);
            ifBroadcast = Boolean.parseBoolean(strings[1]);
            new ScheduledRebootTimer().setLaterStart(scheduledTime);
            if (ifBroadcast) {
                for (int i = 0; i < 5; i++) {
                    MessageSender.send(new MessageToALL("服务器将在 " + scheduledTime + "s 后重启"));
                }
            }
            return true;
        } catch (Exception e) {
            MessageSender.send(new MessageToSingle("参数输入错误", commandSender));
            return false;
        }
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 1) {
            return List.of("[time]");
        }
        if (strings.length == 2) {
            return List.of("true", "false");
        }
        return List.of();
    }
}

class ScheduledRebootTimer extends TickTimer {
    @Override
    public void run() {
        DelicateX.getInstance().getServer().shutdown();
    }
}
