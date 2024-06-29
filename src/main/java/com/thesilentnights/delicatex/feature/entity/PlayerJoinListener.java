package com.thesilentnights.delicatex.feature.entity;

import com.thesilentnights.delicatex.config.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void playerJoinListener(PlayerJoinEvent event){
        if (Config.getConfig("config").getBoolean("if-enable-auto_effect") && event.getPlayer().isOp()){
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,PotionEffect.INFINITE_DURATION,1));
        }
    }
}
