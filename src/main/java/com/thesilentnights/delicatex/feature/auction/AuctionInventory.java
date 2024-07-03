package com.thesilentnights.delicatex.feature.auction;

import com.thesilentnights.delicatex.utils.color.ChatColorFormatter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class AuctionInventory {
    private int page;
    private final Inventory inventory;
    //key starts from 0
    private Map<Integer, ItemModel> modelMap;
    private AuctionInventory previous;
    private AuctionInventory next;

    public AuctionInventory(int page, AuctionInventory previous, AuctionInventory next) {
        this.page = page;
        this.inventory = Bukkit.createInventory(new InventoryHolder() {
            @Override
            public @NotNull Inventory getInventory() {
                return null;
            }
        }, 54, Component.text(ChatColorFormatter.replace("&l&5DelicateShop-Page: &9" + this.page)));
        this.previous = previous;
        this.next = next;
        this.modelMap = new HashMap<>();
    }

    /*
     * mc部分
     * */
    public void showToPlayer(Player player) {
        player.openInventory(inventory);
    }

    public int getEmptySlots() {
        return Arrays.stream(inventory.getContents()).filter(itemStack -> itemStack != null).toList().size() - 9;
    }

    public int getSize() {
        return inventory.getSize();
    }

    public void addItem(ItemStack itemStack, int price) {
        ItemStack clone = itemStack.clone();
        modelMap.put(modelMap.size() , new ItemModel(clone, price, modelMap.size() ));
        if (clone.lore() == null) {
            clone.lore(List.of(Component.text("price: " + price)));
        } else {
            List<Component> lore = clone.lore();
            lore.add(Component.text("price: " + price));
            clone.lore(lore);
        }
        inventory.addItem(clone);
    }

    public ItemModel getItem(int slots/*inventoryCLick 返回的槽位信息*/) {
        if (slots <= modelMap.size()) {
            return modelMap.get(slots);
        } else {
            return null;
        }
    }

    /*
     * 链表部分
     * */

    //刷新当前的页面前后指向
    public void setPrevious(AuctionInventory previous) {
        this.previous = previous;
    }

    public void setNext(AuctionInventory next) {
        this.next = next;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
