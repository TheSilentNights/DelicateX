package com.thesilentnights.delicatex.utils.loader;

import com.thesilentnights.delicatex.config.Config;
import com.thesilentnights.delicatex.feature.cdk.CDK;
import com.thesilentnights.delicatex.feature.chat.Broadcast;
import com.thesilentnights.delicatex.feature.chat.PrivateMsg;
import com.thesilentnights.delicatex.feature.chunk.ChunkLoaderCommand;
import com.thesilentnights.delicatex.feature.distance.Distance;
import com.thesilentnights.delicatex.feature.enchant.Enchant;
import com.thesilentnights.delicatex.feature.entity.EntityClear;
import com.thesilentnights.delicatex.feature.entity.EntityCounter;
import com.thesilentnights.delicatex.feature.entity.ForceHat;
import com.thesilentnights.delicatex.feature.entity.InventoryViewer;
import com.thesilentnights.delicatex.feature.help.Helper;
import com.thesilentnights.delicatex.feature.ip.IpLocation;
import com.thesilentnights.delicatex.feature.reboot.ScheduledReboot;
import com.thesilentnights.delicatex.model.ICommand;
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
                new ScheduledReboot()
        ));
        if (Config.getConfig("config").getBoolean("if-enable-private_message")) {
            needRegister.add(new PrivateMsg());
        }
        modules.addAll(needRegister);
    }
    public static void registerCommands(JavaPlugin plugin){
        modules.forEach(command -> {
            Objects.requireNonNull(plugin.getCommand(command.getClass().getSimpleName())).setExecutor(command);
        });
    }
}
