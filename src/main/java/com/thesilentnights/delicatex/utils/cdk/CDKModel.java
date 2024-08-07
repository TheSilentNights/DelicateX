package com.thesilentnights.delicatex.utils.cdk;

import com.thesilentnights.delicatex.DelicateX;
import com.thesilentnights.delicatex.api.VaultEssApi;
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
    private Long last;
    private boolean expired = false;

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
            ItemStack[] array = itemStacks.toArray(new ItemStack[0]);
            player.getInventory().addItem(array);
        }

        //校验Vault API的加载
        if (DelicateX.getInstance().getServer().getPluginManager().isPluginEnabled("Vault")) {
            VaultEssApi.essentialsApi.depositPlayer(player, money);
        }
        MessageSender.send(new MessageToSingle("物品/金钱已添加进背包/钱包", player));
        gained.add(player.getName());
    }

    //设置过期时间
    public void setExpire(Long time) {
        if (time == -1) {
            return;
        }
        last = time;
    }

    public boolean ifExpired() {
        return expired;
    }

    public void startExpire() {
        this.setLaterStart(last);
    }

    @Override
    @Deprecated
    public void run() {
        this.expired = true;
    }
}
