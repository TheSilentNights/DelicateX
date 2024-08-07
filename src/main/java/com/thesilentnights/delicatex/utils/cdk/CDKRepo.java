package com.thesilentnights.delicatex.utils.cdk;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CDKRepo {
    private static final Map<String, CDKModel> CDKs = new HashMap<>();

    public static void createCDK(String key, CDKModel target) {
        CDKs.put(key, target);
    }

    public static boolean exchange(String key, Player player) {
        CDKModel cdkModel = CDKs.get(key);
        //非空且无过期
        if (cdkModel != null && !cdkModel.ifExpired()) {
            cdkModel.exchange(player);
        }
        return false;
    }

    public static void remove(String key) {
        CDKs.remove(key);
    }

    public static Set<String> ListKey() {
        return CDKs.keySet();
    }

    public static CDKModel getCDK(String key) {
        return CDKs.get(key);
    }

}


