package com.thesilentnights.admintoolspack.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainCommand implements CommandExecutor {
    private final Map<String,ICommand> commandMap;
    public MainCommand() {
        commandMap = new HashMap<>();
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String[] argsWithOutFeatureName=  new String[strings.length-1];
        System.arraycopy(strings,1,argsWithOutFeatureName,0,argsWithOutFeatureName.length);
        return commandMap.get(strings[0]).execute(commandSender,command,s,argsWithOutFeatureName);
    }

    public void register(ICommand command){
        commandMap.put(command.getCommandName(),command);
    }
}
