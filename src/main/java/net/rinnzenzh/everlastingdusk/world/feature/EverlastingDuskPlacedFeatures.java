package net.rinnzenzh.everlastingdusk.world.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.*;
import net.rinnzenzh.everlastingdusk.EverlastingDusk;

import java.util.ArrayList;
import java.util.List;

public class EverlastingDuskPlacedFeatures {

    public static final List<ResourceKey<PlacedFeature>> PLACED_FEATURES = new ArrayList<>();

    public static final ResourceKey<PlacedFeature> FLOATING_RORIAL_CRYTSALS_END = id("ender_rorial_crystals");
    public static final ResourceKey<PlacedFeature> SPIRAL_TREE_OAK = id("spiral_tree_oak");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {

        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(
                FLOATING_RORIAL_CRYTSALS_END,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(EverlastingDuskConfiguredFeatures.FLOATING_RORIAL_CRYTSALS_END),
                        List.of(
                                RarityFilter.onAverageOnceEvery(16),
                                CountPlacement.of(2),
                                InSquarePlacement.spread(),
                                BiomeFilter.biome()
                        )
                )
        );
        context.register(
                SPIRAL_TREE_OAK,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(EverlastingDuskConfiguredFeatures.SPIRAL_TREE_OAK),
                        VegetationPlacements.treePlacement(
                                PlacementUtils.countExtra(10, 0.5F, 2)
                        )
                )
        );

    }

    public static ResourceKey<PlacedFeature> id(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, EverlastingDusk.of(id));
    }
}
