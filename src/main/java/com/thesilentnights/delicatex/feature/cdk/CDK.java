package com.thesilentnights.delicatex.feature.cdk;

import com.thesilentnights.delicatex.model.ICommand;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class CDK implements ICommand {
    public static final String COMMAND_NAME = "CDK";
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player){
            if (strings[0].equals("create") && commandSender.isOp()){
                switch (strings[1]){
                    case "item":
                        ItemStack[] contents = player.getInventory().getContents();
                        if (contents.length == 0){
                            MessageSender.sendMessage(new MessageToSingle("背包是空的",player));
                            return true;
                        }
                        CDKRepo.createCDK(strings[2], new CDKModel() {
                            @Override
                            public void execute(Player player) {
                                //去除空
                                List<ItemStack> list = Arrays.stream(contents).filter(itemStack -> itemStack != null).toList();
                                list.forEach(itemStack ->player.getInventory().addItem(itemStack));
                            }
                        });
                }
            }else{
                CDKRepo.exchange(strings[0],player);
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return switch (strings.length) {
            case 1 -> List.of("create");
            case 2 -> List.of("item");
            case 3 -> List.of("[cdk]");
            default -> List.of();
        };
    }
}
