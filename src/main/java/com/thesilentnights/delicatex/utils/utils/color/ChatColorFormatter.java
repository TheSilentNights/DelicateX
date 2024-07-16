package com.thesilentnights.openchestanywhere.utils.color;

import org.bukkit.ChatColor;

public class ChatColorFormatter {
    public static String replace(String target) {
        return target.replace('&', ChatColor.COLOR_CHAR);
    }
}
