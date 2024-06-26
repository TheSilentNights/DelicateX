package com.thesilentnights.delicatex;

import com.thesilentnights.delicatex.config.Config;
import com.thesilentnights.delicatex.feature.cdk.CDK;
import com.thesilentnights.delicatex.feature.chat.Broadcast;
import com.thesilentnights.delicatex.feature.chat.PlayerChatListener;
import com.thesilentnights.delicatex.feature.chat.PrivateMessage;
import com.thesilentnights.delicatex.feature.distance.Distance;
import com.thesilentnights.delicatex.feature.enchant.Enchant;
import com.thesilentnights.delicatex.feature.entity.EntityClear;
import com.thesilentnights.delicatex.feature.entity.EntityCounter;
import com.thesilentnights.delicatex.feature.entity.InventoryViewer;
import com.thesilentnights.delicatex.feature.entity.PlayerJoinListener;
import com.thesilentnights.delicatex.feature.ip.IpLocation;
import com.thesilentnights.delicatex.feature.reboot.ScheduledReboot;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

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
        Objects.requireNonNull(getCommand(Distance.COMMAND_NAME)).setExecutor(new Distance());
        Objects.requireNonNull(getCommand(EntityCounter.COMMAND_NAME)).setExecutor(new EntityCounter());
        Objects.requireNonNull(getCommand(ScheduledReboot.COMMAND_NAME)).setExecutor(new ScheduledReboot());
        Objects.requireNonNull(getCommand(EntityClear.COMMAND_NAME)).setExecutor(new EntityClear());
        Objects.requireNonNull(getCommand(IpLocation.COMMAND_NAME)).setExecutor(new IpLocation());
        Objects.requireNonNull(getCommand(Broadcast.COMMAND_NAME)).setExecutor(new Broadcast());
        Objects.requireNonNull(getCommand(Enchant.COMMAND_NAME)).setExecutor(new Enchant());
        Objects.requireNonNull(getCommand(InventoryViewer.COMMAND_NAME)).setExecutor(new InventoryViewer());
        Objects.requireNonNull(getCommand(CDK.COMMAND_NAME)).setExecutor(new CDK());

        if (Config.getConfig("config").getBoolean("if-enable-private_message")){
            Objects.requireNonNull(getCommand(PrivateMessage.COMMAND_NAME)).setExecutor(new PrivateMessage());
        }
        //reg eventsListener
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        Config.saveConfigs();
    }

    public static DelicateX getInstance() {
        return instance;
    }

}
