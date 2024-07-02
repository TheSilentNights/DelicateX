package com.thesilentnights.delicatex.feature.auction;

import com.thesilentnights.delicatex.model.ICommand;
import com.thesilentnights.delicatex.repo.VaultEssApi;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Auction implements ICommand {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player sender){
            if (strings.length == 0){
                AuctionHouse.showPage(sender,0);
                return true;
            }
            switch (strings[0]){
                case "sell"->{
                    AuctionHouse.addItem(sender.getInventory().getItemInMainHand(),Integer.parseInt(strings[1]));
                    return true;
                }
                default -> {
                    try{
                        int index = Integer.parseInt(strings[0]);
                        AuctionHouse.showPage(sender,index);
                    }catch (NumberFormatException formatException){
                        MessageSender.sendMessage(new MessageToSingle("参数错误",sender));
                    }
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
