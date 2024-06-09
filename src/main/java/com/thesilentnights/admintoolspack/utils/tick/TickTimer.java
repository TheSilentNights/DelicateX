package com.thesilentnights.admintoolspack.utils.tick;

import com.thesilentnights.admintoolspack.AdminToolsPack;
import org.bukkit.scheduler.BukkitRunnable;

public class TickTimer extends BukkitRunnable {

    @Override
    public void run() {

    }

    public void setStart(long delay) {
        this.runTaskLater(AdminToolsPack.getInstance(), delay * 20L);
    }
}
