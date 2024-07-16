package com.thesilentnights.delicatex.utils.config;

import com.thesilentnights.delicatex.DelicateX;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
    private static final Map<String, Config> configs = new HashMap<>();
    private YamlConfiguration config;

    public Config(YamlConfiguration config, String name) {
        this.config = config;
        configs.put(name, this);
    }

    public boolean getBoolean(String key) {
        return config.getBoolean(key);
    }

    public void setBoolean(String key, boolean value) {
        config.set(key, value);
    }

    public void setList(String key, List value) {
        config.set(key, value);
    }

    public void setString(String key, String val) {
        config.set(key, val);
    }

    public static void saveConfigs() {
        configs.forEach((name, config1) -> {
            try {
                config1.config.save(new File(DelicateX.getInstance().getDataFolder() + "\\" + name + ".yml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static Config getConfig(String name) {
        return configs.get(name);
    }

    public static Map<String, Config> getConfigs() {
        return configs;
    }
}
