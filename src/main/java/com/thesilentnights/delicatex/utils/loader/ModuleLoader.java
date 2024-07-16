package com.thesilentnights.delicatex.utils.loader;

import com.thesilentnights.delicatex.commands.CDK;
import com.thesilentnights.delicatex.commands.Broadcast;
import com.thesilentnights.delicatex.commands.PrivateMsg;
import com.thesilentnights.delicatex.commands.ChunkLoaderCommand;
import com.thesilentnights.delicatex.commands.Distance;
import com.thesilentnights.delicatex.commands.Enchant;
import com.thesilentnights.delicatex.commands.EntityClear;
import com.thesilentnights.delicatex.commands.EntityCounter;
import com.thesilentnights.delicatex.commands.ForceHat;
import com.thesilentnights.delicatex.commands.InventoryViewer;
import com.thesilentnights.delicatex.commands.Fix;
import com.thesilentnights.delicatex.commands.Helper;
import com.thesilentnights.delicatex.commands.IpLocation;
import com.thesilentnights.delicatex.commands.ScheduledReboot;
import com.thesilentnights.delicatex.commands.Report;
import com.thesilentnights.delicatex.commands.ICommand;
import com.thesilentnights.delicatex.utils.config.Config;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModuleLoader {
    private static final List<ICommand> modules = new ArrayList<>();

    static { //register module
        List<ICommand> needRegister = new ArrayList<>(List.of(
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
                new Fix()
        ));

        if (Config.getConfig("config").getBoolean("if-enable-private_message")) {
            needRegister.add(new PrivateMsg());
        }


        modules.addAll(needRegister);
    }

    public static void registerCommands(JavaPlugin plugin) {
        modules.forEach(command -> {
            Objects.requireNonNull(plugin.getCommand(command.getClass().getSimpleName())).setExecutor(command);
        });
    }
}
