package com.thesilentnights.admintoolspack;

import com.thesilentnights.admintoolspack.feature.distance.Distance;
import com.thesilentnights.admintoolspack.feature.entity.EntityClear;
import com.thesilentnights.admintoolspack.feature.entity.EntityCounter;
import com.thesilentnights.admintoolspack.feature.reboot.ScheduledReboot;
import com.thesilentnights.admintoolspack.feature.chat.PlayerChatListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class AdminToolsPack extends JavaPlugin {
    private static AdminToolsPack instance;
    private static FileConfiguration defaultConfig;

    @Override
    public void onEnable() {
        //init config
        if (!getDataFolder().exists()) {
            saveDefaultConfig();
        }
        instance = this;
        defaultConfig = getConfig();

        //reg command
        Objects.requireNonNull(getCommand(Distance.COMMAND_NAME)).setExecutor(new Distance());
        Objects.requireNonNull(getCommand(EntityCounter.COMMAND_NAME)).setExecutor(new EntityCounter());
        Objects.requireNonNull(getCommand(ScheduledReboot.COMMAND_NAME)).setExecutor(new ScheduledReboot());
        Objects.requireNonNull(getCommand(EntityClear.COMMAND_NAME)).setExecutor(new EntityClear());
        //reg eventsListener
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }

    public static AdminToolsPack getInstance() {
        return instance;
    }

    public static FileConfiguration getDefaultConfig() {
        return defaultConfig;
    }
}
