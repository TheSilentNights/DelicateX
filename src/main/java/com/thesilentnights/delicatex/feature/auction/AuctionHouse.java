package com.thesilentnights.delicatex.feature.auction;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class AuctionHouse {
    private static final LinkedList<AuctionInventory> inventories = new LinkedList<>();
    private static final Map<String, Integer> current = new HashMap<>();

    static {
        inventories.add(new AuctionInventory(0, null,null));
    }

    public static void showPage(Player player, int index) {
        inventories.get(index).showToPlayer(player);
        if (current.containsKey(player.getName())) {
            current.replace(player.getName(), index);
        } else {
            current.put(player.getName(), index);
        }
    }

    public static AuctionInventory getPage(int index) {
        return inventories.get(index);
    }

    public static void showNext(Player player) {
        if (current.containsKey(player.getName())) {
            inventories.get(current.get(player.getName())).showToPlayer(player);
        }
    }

    public static void addItem(ItemStack itemStack, int price) {
        if (inventories.getLast().getEmptySlots() < inventories.getLast().getSize() - 9) {
            inventories.getLast().addItem(itemStack);
        } else {
            AuctionInventory auctionInventory = new AuctionInventory(inventories.size(), inventories.getLast(), null);
            inventories.getLast().setNext(auctionInventory);
            inventories.add(auctionInventory);
        }
    }

}
