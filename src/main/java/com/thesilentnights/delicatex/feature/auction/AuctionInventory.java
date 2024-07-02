package com.thesilentnights.delicatex.feature.auction;

import com.thesilentnights.delicatex.utils.color.ChatColorFormatter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class AuctionInventory{
    private int page;
    private Inventory inventory;
    private AuctionInventory previous;
    private AuctionInventory next;

    public AuctionInventory(int page, AuctionInventory previous, AuctionInventory next) {
        this.page = page;
        this.inventory = Bukkit.createInventory(new InventoryHolder() {
            @Override
            public @NotNull Inventory getInventory() {
                return null;
            }
        }, 54, ChatColorFormatter.replace("&l&5DelicateShop-Page: &9"+this.page));
        this.previous = previous;
        this.next = next;
    }
    /*
    * mc部分
    * */
    public void showToPlayer(Player player){
        player.openInventory(inventory);
    }

    public int getEmptySlots(){
        return Arrays.stream(inventory.getContents()).filter(itemStack -> itemStack != null).toList().size()-9;
    }

    public int getSize(){
        return inventory.getSize();
    }

    public void addItem(ItemStack itemStack){
        inventory.addItem(itemStack);
    }

    /*
    * 链表部分
    * */

    //刷新当前的页面前后指向
    public void setPrevious(AuctionInventory previous){
        this.previous = previous;
    }

    public void setNext(AuctionInventory next){
        this.next = next;
    }

}
