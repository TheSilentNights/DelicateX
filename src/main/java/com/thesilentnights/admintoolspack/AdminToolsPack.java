package com.thesilentnights.admintoolspack;

import com.thesilentnights.admintoolspack.commands.MainCommand;
import com.thesilentnights.admintoolspack.events.IPlayerChatEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class AdminToolsPack extends JavaPlugin {
    private static AdminToolsPack instance;
    private static FileConfiguration defaultConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!getDataFolder().exists()){
            saveDefaultConfig();
        }
        instance = this;
        defaultConfig = getConfig();

        //reg
        Objects.requireNonNull(getCommand("admintoolspack")).setExecutor(new MainCommand());
        getServer().getPluginManager().registerEvents(new IPlayerChatEvent(),this);
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
