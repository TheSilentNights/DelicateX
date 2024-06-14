package com.thesilentnights.admintoolspack;

import com.thesilentnights.admintoolspack.config.Config;
import com.thesilentnights.admintoolspack.feature.chat.PlayerChatListener;
import com.thesilentnights.admintoolspack.feature.distance.Distance;
import com.thesilentnights.admintoolspack.feature.entity.EntityClear;
import com.thesilentnights.admintoolspack.feature.entity.EntityCounter;
import com.thesilentnights.admintoolspack.feature.reboot.ScheduledReboot;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class AdminToolsPack extends JavaPlugin {
    private static AdminToolsPack instance;

    @Override
    public void onEnable() {
        //init config
        if (!getDataFolder().exists()) {
            saveDefaultConfig();
        }
        new Config((YamlConfiguration) getConfig(), "config");
        instance = this;

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

        Config.saveConfigs();
    }

    public static AdminToolsPack getInstance() {
        return instance;
    }

}
