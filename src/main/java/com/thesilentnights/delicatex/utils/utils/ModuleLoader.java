package com.thesilentnights.openchestanywhere.utils;

import com.thesilentnights.openchestanywhere.OpenChestAnywhere;
import com.thesilentnights.openchestanywhere.commands.Create;
import com.thesilentnights.openchestanywhere.commands.ICommand;
import com.thesilentnights.openchestanywhere.commands.Record;
import com.thesilentnights.openchestanywhere.events.PlayerOpenChestListener;
import org.bukkit.event.Listener;
import com.thesilentnights.openchestanywhere.commands.Open;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ModuleLoader {
    private static final List<ICommand> commands;
    private static final List<Listener> listeners;

    static {
        listeners = new ArrayList<>();
        commands = new ArrayList<>();
        Collections.addAll(commands,
                new Open(),
                new Record(),
                new Create()
        );

        Collections.addAll(listeners,
                new PlayerOpenChestListener()
        );
    }
    //register event and command
    public static void load() {
        commands.forEach(iCommand -> Objects.requireNonNull(OpenChestAnywhere.getInstance().getCommand(iCommand.getClass().getSimpleName())).setExecutor(iCommand));
        listeners.forEach(listener -> OpenChestAnywhere.getInstance().getServer().getPluginManager().registerEvents(listener, OpenChestAnywhere.getInstance()));
    }
}
