package com.thesilentnights.delicatex.impl.chunk;

import org.bukkit.Chunk;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class ChunkLoader {
    static List<Chunk> chunks;

    public static void init() {
        chunks = new ArrayList<>();
    }

    public static void loadChunk(World world, int x, int z, boolean generate/*不存在时是否生成*/) {
        world.loadChunk(x, z, generate);
        chunks.add(world.getChunkAt(x, z));
    }

    public static void unloadChunk(World world, int x, int z) {
        if (world.getChunkAt(x, z).isLoaded()) {
            world.getChunkAt(x, z).unload(true);
        }
    }

    //卸载所有手动加载的区块
    public static void unloadAllChunks() {
        chunks.forEach(chunk -> {
            if (chunk.isLoaded()) {
                chunk.unload(true);
            }
        });
    }
}
