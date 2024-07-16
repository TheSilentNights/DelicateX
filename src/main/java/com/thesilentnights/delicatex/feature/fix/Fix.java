package com.thesilentnights.delicatex.feature.fix;

import com.thesilentnights.delicatex.model.ICommand;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Fix implements ICommand {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            if (player.getInventory().getItemInMainHand().getType().isAir()) {
                MessageSender.send(new MessageToSingle("你必须手持物品", player));
                return true;
            }
            ItemStack stack = player.getInventory().getItemInMainHand();
            if (stack.getItemMeta() instanceof Damageable damageable) {
                damageable.setDamage(0);
                stack.setItemMeta(damageable);
            }
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
