package com.thesilentnights.delicatex.utils.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EntityCounter {
    public static Map<String, Integer> execute(Collection<org.bukkit.entity.Entity> nearbyEntities) {
        Map<String, Integer> map = new HashMap<>();

        nearbyEntities.forEach(entity -> {
            String namespace = entity.getType().getKey().getKey();
            if (map.get(namespace) == null) {
                map.put(namespace, 1);
            } else {
                map.merge(namespace, map.get(namespace), (k, val) -> {
                    return val + 1;
                });
            }
        });
        return map;
    }
}
