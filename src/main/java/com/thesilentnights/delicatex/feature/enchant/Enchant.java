package com.thesilentnights.delicatex.feature.enchant;

import com.thesilentnights.delicatex.model.ICommand;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Enchant implements ICommand {
    public static final String COMMAND_NAME = "Enchant";

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player){
            Enchantment byKey = Enchantment.getByKey(new NamespacedKey(NamespacedKey.MINECRAFT, strings[0]));
            if (byKey != null){
                player.getInventory().getItemInMainHand().addUnsafeEnchantment(byKey,Integer.parseInt(strings[1]));
                return true;
            }
            MessageSender.send(new MessageToSingle("该附魔不存在",player));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> result = new ArrayList<>();
        for (Enchantment enchantment: Enchantment.values()){
            result.add(enchantment.getKey().getKey());
        }

        return result;
    }
}
