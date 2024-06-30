package com.thesilentnights.delicatex.feature.entity;

import com.thesilentnights.delicatex.model.ICommand;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ForceHat implements ICommand {

    public static final String COMMAND_NAME = "ForceHat";
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player sender){
            if (sender.getInventory().getItemInMainHand().getType().isAir()){
                MessageSender.sendMessage(new MessageToSingle("你必须拿着物品",sender));
                return false;
            }

            @Nullable ItemStack[] armorContents = sender.getInventory().getArmorContents();
            if (armorContents[3] == null){
                armorContents[3] = sender.getInventory().getItemInMainHand();
                sender.getInventory().setArmorContents(armorContents);
                sender.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                return true;
            }
            ItemStack armorContent = armorContents[3];
            armorContents[3] = sender.getInventory().getItemInMainHand();
            sender.getInventory().setArmorContents(armorContents);
            sender.getInventory().setItemInMainHand(armorContent);
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
