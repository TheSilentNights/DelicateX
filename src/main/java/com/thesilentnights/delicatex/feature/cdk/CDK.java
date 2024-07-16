package com.thesilentnights.delicatex.feature.cdk;

import com.thesilentnights.delicatex.DelicateX;
import com.thesilentnights.delicatex.model.ICommand;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CDK implements ICommand {


    //param: create item money cdk expire
    @SuppressWarnings("ConstantValue")
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        boolean ifVaultEnabled = DelicateX.getInstance().getServer().getPluginManager().isPluginEnabled("Vault");

        if (!ifVaultEnabled) {
            MessageSender.send(new MessageToSingle("&4请注意! vault未加载 部分功能不会生效", commandSender));
        }

        if (strings[0].equals("remove") && commandSender.isOp()) {
            CDKRepo.remove(strings[1]);
            return true;
        }

        if (strings[0].equals("list") && commandSender.isOp()) {
            CDKRepo.ListKey().forEach(key -> MessageSender.send(new MessageToSingle(key, commandSender)));
            return true;
        }

        if (commandSender instanceof Player player) {
            if (strings[0].equals("create") && commandSender.isOp()) {
                List<ItemStack> itemStacks = new ArrayList<>();
                int moneyVal = 0;
                long expire = 0;

                //item
                if (!strings[1].equals("none")) {
                    //返回值可能为空
                    itemStacks = Arrays.stream(player.getInventory().getContents()).filter(itemStack -> itemStack != null).toList();
                }
                //money
                if (!strings[2].equals("none")) {
                    try {
                        moneyVal = Integer.parseInt(strings[2]);
                    } catch (Exception e) {
                        MessageSender.send(new MessageToSingle("货币参数错误", player));
                    }
                }

                //expire
                if (!strings[4].isEmpty()) {
                    try {
                        expire = Long.parseLong(strings[4]);
                    } catch (Exception e) {
                        MessageSender.send(new MessageToSingle("时间参数错误", player));
                    }
                }

                //生成
                CDKModel cdkModel = new CDKModel(strings[3]);


                if (itemStacks != null && !itemStacks.isEmpty()) {
                    cdkModel.setItemGive(itemStacks);
                }
                cdkModel.setMoneyGive(moneyVal);
                cdkModel.setExpire(expire);
                CDKRepo.createCDK(strings[3], cdkModel);


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
                    return List.of("create", "remove", "list");
                case 2:
                    if (strings[0].equals("create")) {
                        return List.of("inventory", "none");
                    } else if (strings[0].equals("remove")) {
                        return CDKRepo.ListKey().stream().toList();
                    }
                case 3:
                    return List.of("[moneyValue(require Integer)]", "none");
                case 4:
                    return List.of("[key]");
                case 5:
                    return List.of("[expireTime]");
            }
        }
        return List.of();
    }
}
