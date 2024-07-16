package com.thesilentnights.delicatex.utils.cdk;

import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CDKRepo {
    private static final Map<String, CDKModel> CDKs = new HashMap<>();

    public static void createCDK(String key, CDKModel target) {
        CDKs.put(key, target);
    }

    public static void exchange(String key, Player player) {
        CDKModel cdkModel = CDKs.get(key);
        if (cdkModel == null) {
            MessageSender.send(new MessageToSingle("错误的cdk", player));
        } else if (cdkModel.ifExpired()) {
            MessageSender.send(new MessageToSingle("该cdk已过期", player));
        } else {
            cdkModel.exchange(player);
        }
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


