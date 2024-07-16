package com.thesilentnights.delicatex.utils.cdk;

import com.thesilentnights.delicatex.DelicateX;
import com.thesilentnights.delicatex.repo.VaultEssApi;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import com.thesilentnights.delicatex.utils.task.tick.TickTimer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CDKModel extends TickTimer {
    private final String key;
    private List<ItemStack> itemStacks;
    private int money;
    private final List<String> gained = new ArrayList<>();
    private boolean expire = false;

    public CDKModel(String key) {
        this.key = key;
    }

    public void setItemGive(List<ItemStack> itemStacks) {
        this.itemStacks = itemStacks;
    }

    public void setMoneyGive(int value) {
        this.money = value;
    }

    public void exchange(Player player) {
        if (gained.stream().anyMatch(s -> s.equals(player.getName()))) {
            MessageSender.send(new MessageToSingle("你已经领取过了哦", player));
            return;
        }
        if (!itemStacks.isEmpty()) {
            for (ItemStack itemStack : itemStacks) {
                player.getInventory().addItem(itemStack);
            }
        }
        //校验Vault API的加载
        if (DelicateX.getInstance().getServer().getPluginManager().isPluginEnabled("Vault")) {
            VaultEssApi.essentialsApi.depositPlayer(player, money);
        }
        MessageSender.send(new MessageToSingle("物品/金钱已添加进背包/钱包", player));
        gained.add(player.getName());
    }

    public void setExpire(Long time) {
        if (time == -1) {
            return;
        }
        this.setLaterStart(time);
    }

    public boolean ifExpired() {
        return expire;
    }

    @Override
    public void run() {
        this.expire = true;
    }
}
