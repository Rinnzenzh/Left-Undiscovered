package net.rinnzenzh.everlastingdusk.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.rinnzenzh.everlastingdusk.world.feature.EverlastingDuskPlacedFeatures;

import java.util.ArrayList;
import java.util.List;

public class EverlastingDuskGen {


    public static void addCustomFeaturesToExistingBiomes() {

        
        // End Feature Additions
        List<ResourceKey<Biome>> aerialBiomes = new ArrayList<>();
        aerialBiomes.addAll(List.of(
                        Biomes.SMALL_END_ISLANDS
                )
        );

        try {
            aerialBiomes.add(
                    ResourceKey.create(Registries.BIOME,
                            Identifier.fromNamespaceAndPath("lighterend", "starfield"))
            );
            aerialBiomes.add(
                    ResourceKey.create(Registries.BIOME,
                            Identifier.fromNamespaceAndPath("betterend", "ice_starfield"))
            );
        } catch (NullPointerException ignored) {
        }

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(aerialBiomes),
                GenerationStep.Decoration.SURFACE_STRUCTURES,
                EverlastingDuskPlacedFeatures.FLOATING_RORIAL_CRYTSALS_END
        );
    }
}
