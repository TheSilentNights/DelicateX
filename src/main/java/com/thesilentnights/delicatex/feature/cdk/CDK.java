package com.thesilentnights.delicatex.feature.cdk;

import com.thesilentnights.delicatex.model.ICommand;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.framework.qual.LiteralKind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class CDK implements ICommand {
    public static final String COMMAND_NAME = "CDK";

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length != 3) {
            MessageSender.sendMessage(new MessageToSingle("参数错误", commandSender));
            return false;
        }
        if (commandSender instanceof Player player) {
            if (strings[0].equals("create") && commandSender.isOp()) {
                //item
                if (!strings[1].equals("none")) {
                    ItemStack[] contents = player.getInventory().getContents();
                    CDKRepo.createCDK(strings[2], new CDKModel() {
                        @Override
                        public void execute(Player player) {
                            //去除空
                            List<ItemStack> list = Arrays.stream(contents).filter(itemStack -> itemStack != null).toList();
                            list.forEach(itemStack -> player.getInventory().addItem(itemStack));
                        }
                    });
                }
                //money
                if (!strings[2].equals("none")) {
                    MessageSender.sendMessage(new MessageToSingle("该功能未开发完毕", player));
                }

            } else if (strings[0].equals("remove") && player.isOp()) {
                CDKRepo.remove(strings[1]);
            } else {
                CDKRepo.exchange(strings[0], player);
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.isOp()) {
            switch (strings.length) {
                case 1:
                    return List.of("create", "remove");
                case 2:
                    if (strings[0].equals("create")) {
                        return List.of("inventory(any other is ok)", "none");
                    } else if (strings[0].equals("remove")) {
                        return CDKRepo.ListKey().stream().toList();
                    }
                case 3:
                    return List.of("[moneyValue(require Integer)]", "none");
            }
        }
        return List.of();
    }
}
