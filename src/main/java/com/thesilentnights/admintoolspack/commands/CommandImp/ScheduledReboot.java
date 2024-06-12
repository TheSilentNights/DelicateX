package com.thesilentnights.admintoolspack.commands.CommandImp;

import com.thesilentnights.admintoolspack.AdminToolsPack;
import com.thesilentnights.admintoolspack.commands.ICommand;
import com.thesilentnights.admintoolspack.utils.messageSender.MessageSender;
import com.thesilentnights.admintoolspack.utils.messageSender.messageImp.MessageToALL;
import com.thesilentnights.admintoolspack.utils.messageSender.messageImp.MessageToSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import com.thesilentnights.admintoolspack.utils.task.tick.TickTimer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ScheduledReboot extends ICommand {
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
                    MessageSender.sendMessage(new MessageToALL("服务器将在 " + scheduledTime + "s 后重启"));
                }
            }
            return true;
        } catch (Exception e) {
            MessageSender.sendMessage(new MessageToSender("参数输入错误", commandSender));
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
        AdminToolsPack.getInstance().getServer().shutdown();
    }
}
