package com.thesilentnights.delicatex.feature.entity;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import com.thesilentnights.delicatex.utils.config.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerEffectListener implements Listener {
    @EventHandler
    public void playerJoinListener(PlayerJoinEvent event) {
        if (Config.getConfig("config").getBoolean("if-enable-auto_effect") && event.getPlayer().isOp()) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 1));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 1));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, PotionEffect.INFINITE_DURATION, 1));
            event.getPlayer().setAllowFlight(true);
        }
    }

    @EventHandler
    public void playerDieListener(PlayerPostRespawnEvent event) {
        if (event.getPlayer().isOp()) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 1));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 1));
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, PotionEffect.INFINITE_DURATION, 1));
            event.getPlayer().setAllowFlight(true);
        }
    }

}
