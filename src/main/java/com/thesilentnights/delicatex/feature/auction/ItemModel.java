package com.thesilentnights.delicatex.feature.auction;

import org.bukkit.inventory.ItemStack;

public class ItemModel {
    private final int index;
    private final ItemStack itemStack;
    private final int price;

    public ItemModel(ItemStack itemStack, int price,int index) {
        this.index = index;
        this.itemStack = itemStack.clone();
        this.price = price;
    }

    //获取所在槽位
    public int getIndex() {
        return index;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getPrice() {
        return price;
    }


}
