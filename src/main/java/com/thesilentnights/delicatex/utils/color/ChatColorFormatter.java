package com.thesilentnights.delicatex.utils.color;

import org.bukkit.ChatColor;

public class ChatColorFormatter {
    public static String replace(String content){
        return content.replace('&',ChatColor.COLOR_CHAR);
    }
}
