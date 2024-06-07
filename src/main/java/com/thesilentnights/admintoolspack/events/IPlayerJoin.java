package com.thesilentnights.admintoolspack.events;

import com.thesilentnights.admintoolspack.AdminToolsPack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


//自动给予effect
public class IPlayerJoin implements Listener {
    @EventHandler
    public void IPlayerJoin(PlayerJoinEvent event){
        if (event.getPlayer().isOp() && AdminToolsPack.getDefaultConfig().getBoolean("if-enable-effect_auto")){
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,PotionEffect.INFINITE_DURATION,0));
        }
    }
}
