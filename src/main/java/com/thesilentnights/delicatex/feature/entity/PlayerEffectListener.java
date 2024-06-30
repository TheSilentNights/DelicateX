package com.thesilentnights.delicatex.feature.entity;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import com.thesilentnights.delicatex.config.Config;
import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerEffectListener implements Listener {
    @EventHandler
    public void playerJoinListener(PlayerJoinEvent event){
        if (Config.getConfig("config").getBoolean("if-enable-auto_effect") && event.getPlayer().isOp()){
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,PotionEffect.INFINITE_DURATION,1));
        }
    }

    @EventHandler
    public void playerDieListener(PlayerPostRespawnEvent event){
        if (event.getPlayer().isOp()){
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,PotionEffect.INFINITE_DURATION,1));
        }
    }

}