package net.rinnzenzh.everlastingdusk.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.rinnzenzh.everlastingdusk.EverlastingDusk;

public class EverlastingDuskBiomes {



    private static ResourceKey<Biome> register(String name) {
        ResourceKey<Biome> key = ResourceKey.create(Registries.BIOME, EverlastingDusk.of(name));
        return key;
    }

}
