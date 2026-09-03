package net.rinnzenzh.everlastingdusk.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.rinnzenzh.everlastingdusk.registries.EverlastingDuskDimRegistry;
import net.rinnzenzh.everlastingdusk.world.feature.EverlastingDuskConfiguredFeatures;
import net.rinnzenzh.everlastingdusk.world.feature.EverlastingDuskPlacedFeatures;
import net.rinnzenzh.everlastingdusk.world.wg.EverlastingDuskNoiseSettings;
import net.rinnzenzh.everlastingdusk.world.EverlastingDuskDimTypeAndStem;

public class EverlastingDuskDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(EverlastingDuskModelsProvider::new);
        pack.addProvider(EverlastingDuskConfiguredFeaturesProvider::new);
        pack.addProvider(EverlastingDuskPlacedFeatureProvider::new);
        pack.addProvider(EverlastingDuskRegistryProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(
                Registries.CONFIGURED_FEATURE,
                EverlastingDuskConfiguredFeatures::bootstrap
        );
        registryBuilder.add(
                Registries.PLACED_FEATURE,
                EverlastingDuskPlacedFeatures::bootstrap
        );
        registryBuilder
                .add(Registries.DIMENSION_TYPE, EverlastingDuskDimRegistry::bootstrapDimensionTypes)
                .add(Registries.LEVEL_STEM, EverlastingDuskDimTypeAndStem::bootstrapDimension)
                .add(Registries.NOISE_SETTINGS, EverlastingDuskNoiseSettings::bootstrap);
    }
}
