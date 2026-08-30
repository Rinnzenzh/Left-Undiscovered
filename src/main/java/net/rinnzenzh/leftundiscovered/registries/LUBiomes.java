package net.rinnzenzh.leftundiscovered.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.rinnzenzh.leftundiscovered.LeftUndiscovered;

public class LUBiomes {



    private static ResourceKey<Biome> register(String name) {
        ResourceKey<Biome> key = ResourceKey.create(Registries.BIOME, LeftUndiscovered.of(name));
        return key;
    }

}
