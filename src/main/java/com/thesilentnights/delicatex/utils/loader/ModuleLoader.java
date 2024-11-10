package com.thesilentnights.delicatex.utils.loader;

import com.thesilentnights.delicatex.commands.*;
import com.thesilentnights.delicatex.listeners.PlayerChatListener;
import com.thesilentnights.delicatex.listeners.PlayerEffectListener;
import com.thesilentnights.delicatex.utils.config.Config;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.bukkit.Bukkit.getServer;
import static com.thesilentnights.delicatex.DelicateX.getInstance;

public class ModuleLoader {

    public static void registerDefault() {
        registerDefaultCommands();
        registerDefaultEvents();
    }

    private static void registerDefaultCommands() {
        List<DelicateCommand> modules = new ArrayList<>(List.of(
                new CDK(),
                new Broadcast(),
                new ChunkLoaderCommand(),
                new Distance(),
                new Enchant(),
                new EntityClear(),
                new EntityCounter(),
                new ForceHat(),
                new InventoryViewer(),
                new Helper(),
                new IpLocation(),
                new ScheduledReboot(),
                new Report(),
                new Fix(),
                new ForceExecute()
        ));
        if (Config.getConfig("config").getBoolean("if-enable-private_message")) {
            modules.add(new PrivateMsg());
        }
        modules.forEach(command -> {
            Objects.requireNonNull(getInstance().getCommand(command.getClass().getSimpleName())).setExecutor(command);
        });
    }

    private static void registerDefaultEvents() {
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), getInstance());
        getServer().getPluginManager().registerEvents(new PlayerEffectListener(), getInstance());
    }

    //注册新的事件
    public static void registerEvent(Listener listener){
        getServer().getPluginManager().registerEvents(listener,getInstance());
    }
}
