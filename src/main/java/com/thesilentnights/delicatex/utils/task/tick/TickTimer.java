package com.thesilentnights.delicatex.utils.task.tick;

import com.thesilentnights.delicatex.DelicateX;
import org.bukkit.scheduler.BukkitRunnable;

abstract public class TickTimer extends BukkitRunnable {
    //重复执行
    public void setTimerStart(long delay, long time/*执行时间间隔*/) {
        this.runTaskTimer(DelicateX.getInstance(), delay * 20L, 20L);
    }

    //延迟执行一次
    public void setLaterStart(long delay) {
        this.runTaskLater(DelicateX.getInstance(), delay * 20);
    }

    //延迟异步执行
    public void setTimerAsynchronouslyStart(long delay ,long time){
        this.runTaskTimerAsynchronously(DelicateX.getInstance(),delay,time);
    }
}
