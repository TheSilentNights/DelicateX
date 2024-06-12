package com.thesilentnights.admintoolspack;

import com.thesilentnights.admintoolspack.commands.CommandImp.Distance;
import com.thesilentnights.admintoolspack.commands.CommandImp.Entity;
import com.thesilentnights.admintoolspack.commands.CommandImp.ScheduledReboot;
import com.thesilentnights.admintoolspack.chat.events.IPlayerChat;
import com.thesilentnights.admintoolspack.events.IPlayerJoin;
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
        Objects.requireNonNull(getCommand(Entity.COMMAND_NAME)).setExecutor(new Entity());
        Objects.requireNonNull(getCommand(ScheduledReboot.COMMAND_NAME)).setExecutor(new ScheduledReboot());

        //reg eventsListener
        getServer().getPluginManager().registerEvents(new IPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new IPlayerJoin(), this);
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
