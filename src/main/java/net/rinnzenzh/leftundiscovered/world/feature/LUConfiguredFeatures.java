package net.rinnzenzh.leftundiscovered.world.feature;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.rinnzenzh.leftundiscovered.LeftUndiscovered;
import net.rinnzenzh.leftundiscovered.world.feature.end.FloatingRorialCrystals;

import java.util.ArrayList;
import java.util.List;

public class LUConfiguredFeatures {

    public static final List<ResourceKey<ConfiguredFeature<?, ?>>> CONFIGURED_FEATURES = new ArrayList<>();

    public static final Feature<NoneFeatureConfiguration> FLOATING_RORIAL_CRYTSALS_END_FEATURE = Registry.register(
            BuiltInRegistries.FEATURE,
            LeftUndiscovered.of("ender_rorial_crystals"),
            new FloatingRorialCrystals());
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOATING_RORIAL_CRYTSALS_END = of(
            "ender_rorial_crystals");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> lookup = context.lookup(
                Registries.CONFIGURED_FEATURE
        );
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);

        FeatureUtils.register(context, FLOATING_RORIAL_CRYTSALS_END, FLOATING_RORIAL_CRYTSALS_END_FEATURE);

    }

    public static void init() {
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> of(String id) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, LeftUndiscovered.of(id));
    }
}
