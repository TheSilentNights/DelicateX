package com.thesilentnights.delicatex;

import com.thesilentnights.delicatex.config.Config;
import com.thesilentnights.delicatex.feature.chat.PlayerChatListener;
import com.thesilentnights.delicatex.feature.entity.*;
import com.thesilentnights.delicatex.utils.loader.ModuleLoader;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class DelicateX extends JavaPlugin {
    private static DelicateX instance;

    @Override
    public void onEnable() {

        //init config
        if (!getDataFolder().exists()) {
            saveDefaultConfig();
        }
        new Config((YamlConfiguration) getConfig(), "config");
        instance = this;

        //reg command
        ModuleLoader.registerCommands(this);

        //reg eventsListener

        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerEffectListener(), this);

        //load class
        com.thesilentnights.delicatex.utils.chunk.ChunkLoader.init();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        com.thesilentnights.delicatex.utils.chunk.ChunkLoader.unloadAllChunks();
        Config.saveConfigs();
    }

    public static DelicateX getInstance() {
        return instance;
    }

}
