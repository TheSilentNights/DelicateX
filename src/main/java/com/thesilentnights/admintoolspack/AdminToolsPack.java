package com.thesilentnights.admintoolspack;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdminToolsPack extends JavaPlugin {
    private AdminToolsPack instance;
    private FileConfiguration defaultConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!getDataFolder().exists()){
            saveDefaultConfig();
        }

        defaultConfig = getConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public AdminToolsPack getInstance() {
        return instance;
    }

    public FileConfiguration getDefaultConfig() {
        return defaultConfig;
    }
}
