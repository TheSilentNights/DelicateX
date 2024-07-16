package com.thesilentnights.delicatex.feature.cdk;

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
        if (cdkModel != null) {
            cdkModel.exchange(player);
        }else {
            MessageSender.send(new MessageToSingle("错误的cdk",player));
        }
    }

    public static void remove(String key){
        CDKs.remove(key);
    }

    public static Set<String> ListKey(){
        return CDKs.keySet();
    }

    public static CDKModel getCDK(String key){
        return CDKs.get(key);
    }

}


