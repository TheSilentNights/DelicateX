package com.thesilentnights.admintoolspack.utils.task.tick;

import com.thesilentnights.admintoolspack.AdminToolsPack;
import org.bukkit.scheduler.BukkitRunnable;

abstract public class TickTimer extends BukkitRunnable {
    //重复执行
    public void setTimerStart(long delay, long time/*执行时间间隔*/) {
        this.runTaskTimer(AdminToolsPack.getInstance(), delay * 20L, 20L);
    }

    //延迟执行一次
    public void setLaterStart(long delay) {
        this.runTaskLater(AdminToolsPack.getInstance(), delay * 20);
    }
}
