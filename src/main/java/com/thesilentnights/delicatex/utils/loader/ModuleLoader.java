package com.thesilentnights.delicatex.utils.loader;

import com.thesilentnights.delicatex.commands.*;
import com.thesilentnights.delicatex.utils.config.Config;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModuleLoader {

    public static void registerCommands(JavaPlugin plugin) {
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
            Objects.requireNonNull(plugin.getCommand(command.getClass().getSimpleName())).setExecutor(command);
        });
    }
}
