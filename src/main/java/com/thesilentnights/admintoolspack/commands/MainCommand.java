package com.thesilentnights.admintoolspack.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MainCommand implements CommandExecutor {
    private final Map<String,ICommand> commandMap;
    public MainCommand() {
        commandMap = new HashMap<>();
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return commandMap.get(strings[0]).execute(commandSender,command,s,strings);
    }

    public void register(ICommand command){
        commandMap.put(command.getCommandName(),command);
    }
}
