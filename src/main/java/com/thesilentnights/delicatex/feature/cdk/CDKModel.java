package com.thesilentnights.delicatex.feature.cdk;

import com.thesilentnights.delicatex.repo.VaultEssApi;
import com.thesilentnights.delicatex.utils.task.tick.TickTimer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CDKModel extends TickTimer {
    private final String key;
    private List<ItemStack> itemStacks;
    private int money;

    public CDKModel(String key) {
        this.key = key;
    }

    public void setItemGive(List<ItemStack> itemStacks){
         this.itemStacks = itemStacks;
    }

    public void setMoneyGive(int value){
        this.money = value;
    }

    public void exchange(Player player){
        if (!itemStacks.isEmpty()){
            for (ItemStack itemStack : itemStacks) {
              player.getInventory().addItem(itemStack);
            }
        }
        VaultEssApi.essentialsApi.depositPlayer(player,money);
    }

    public void setExpire(Long time){
        this.setLaterStart(time);
    }

    @Override
    public void run(){
        CDKRepo.remove(key);
    }
}
