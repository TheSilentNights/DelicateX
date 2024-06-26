package com.thesilentnights.delicatex.feature.cdk;

import com.thesilentnights.delicatex.utils.messageSender.MessageSender;
import com.thesilentnights.delicatex.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CDKRepo {
    private static final Map<String, CDKModel> CDKs = new HashMap<>();

    public static void createCDK(String key, CDKModel target) {
        CDKs.put(key, target);
    }

    public static void exchange(String key, Player player) {
        CDKModel cdkModel = CDKs.get(key);
        if (cdkModel != null) {
            cdkModel.execute(player);
            MessageSender.sendMessage(new MessageToSingle("物品/金钱已添加进背包/钱包",player));
        }else {
            MessageSender.sendMessage(new MessageToSingle("错误的cdk",player));
        }
    }

}


