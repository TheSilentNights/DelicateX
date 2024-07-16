package com.thesilentnights.delicatex.listeners;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import com.thesilentnights.delicatex.utils.config.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerEffectListener implements Listener {
    @EventHandler
    public void playerJoinListener(PlayerJoinEvent event) {
        if (Config.getConfig("config").getBoolean("if-enable-auto_effect") && event.getPlayer().isOp()) {
            addEffectPack(event.getPlayer(), event);
        }
    }

    private void addEffectPack(Player player, PlayerEvent event) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, -1, 1));
        player.setAllowFlight(true);
    }

    @EventHandler
    public void playerDieListener(PlayerPostRespawnEvent event) {
        if (event.getPlayer().isOp()) {
            addEffectPack(event.getPlayer(), event);
        }
    }

}
