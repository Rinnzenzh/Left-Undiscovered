package net.rinnzenzh.leftundiscovered.world.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.rinnzenzh.leftundiscovered.LeftUndiscovered;

import java.util.ArrayList;
import java.util.List;

public class LUPlacedFeatures {

    public static final List<ResourceKey<PlacedFeature>> PLACED_FEATURES = new ArrayList<>();

    public static final ResourceKey<PlacedFeature> FLOATING_RORIAL_CRYTSALS_END = id("ender_rorial_crystals");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {

        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(
                FLOATING_RORIAL_CRYTSALS_END,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(LUConfiguredFeatures.FLOATING_RORIAL_CRYTSALS_END),
                        List.of(
                                RarityFilter.onAverageOnceEvery(16),
                                CountPlacement.of(2),
                                InSquarePlacement.spread(),
                                BiomeFilter.biome()
                        )
                )
        );

    }

    public static ResourceKey<PlacedFeature> id(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, LeftUndiscovered.of(id));
    }
}
