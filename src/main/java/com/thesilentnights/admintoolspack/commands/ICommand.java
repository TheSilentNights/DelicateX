package com.thesilentnights.admintoolspack.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

abstract public class ICommand {
    public ICommand(String commandName) {
        this.commandName = commandName;
    }

    protected final String commandName;

    //need imp
    abstract public boolean execute(CommandSender commandSender,Command command,String s,String[] strings);


    public String getCommandName(){
        return this.commandName;
    }
}
