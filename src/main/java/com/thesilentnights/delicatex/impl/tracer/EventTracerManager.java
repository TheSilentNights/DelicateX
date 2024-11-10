package com.thesilentnights.delicatex.impl.tracer;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class EventTracerManager {
    public static TracerEventListener listener = new TracerEventListener();
    static Map<CommandSender,EventTracer> tracers = new HashMap<>();

    public static void create(CommandSender createdBy, Player targetPlayer) {
        tracers.put(createdBy,new EventTracer(targetPlayer, createdBy));
    }

    public static void remove(CommandSender createdBy) {
        tracers.remove(createdBy);
    }

}
