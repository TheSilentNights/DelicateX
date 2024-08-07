package com.thesilentnights.delicatex.api;

import com.thesilentnights.delicatex.DelicateX;
import net.milkbowl.vault.economy.plugins.Economy_Essentials;
import org.bukkit.Bukkit;

public class VaultEssApi {
    public static final Economy_Essentials essentialsApi;

    static {
        essentialsApi = Bukkit.getPluginManager().isPluginEnabled("Vault")
                ? new Economy_Essentials(DelicateX.getInstance())
                : null;
    }

}
