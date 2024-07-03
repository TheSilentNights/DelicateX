package com.thesilentnights.delicatex.feature.auction;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AuctionInventoryClickEvent implements Listener {
    @EventHandler
    public void PlayerClickAuctionListener(InventoryClickEvent event){

        AuctionInventory auctionInventory = AuctionHouse.anyMatch(event.getClickedInventory());
        if (auctionInventory!= null && event.getWhoClicked() instanceof Player player){
            event.setCancelled(true);
            AuctionHouse.buyItem(auctionInventory,player, event.getSlot());
        }
    }
}
