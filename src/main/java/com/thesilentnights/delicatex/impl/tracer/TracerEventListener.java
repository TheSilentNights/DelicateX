package com.thesilentnights.delicatex.impl.tracer;

import com.thesilentnights.delicatex.utils.loader.ModuleLoader;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TracerEventListener implements Listener {
    Map<EventTracer, Player> callerBinder = new HashMap<>();

    public TracerEventListener() {
        ModuleLoader.registerEvent(this);
    }

    public void bind(EventTracer tracer, Player targetPlayer) {
        callerBinder.put(tracer, targetPlayer);
    }

    @EventHandler
    public void listenAll(PlayerEvent event) {
        if (callerBinder.containsValue(event.getPlayer())) {
            callerBinder.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey))
                    .get(event.getPlayer()).callBack(event.getEventName());
        }
    }
}
