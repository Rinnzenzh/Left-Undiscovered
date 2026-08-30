package net.rinnzenzh.leftundiscovered.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.rinnzenzh.leftundiscovered.world.feature.LUConfiguredFeatures;
import net.rinnzenzh.leftundiscovered.world.feature.LUPlacedFeatures;

public class LeftUndiscoveredDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(LUModelsProvider::new);
        pack.addProvider(LUConfiguredFeaturesProvider::new);
        pack.addProvider(LUPlacedFeatureProvider::new);
        pack.addProvider(LURegistryProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(
                Registries.CONFIGURED_FEATURE,
                LUConfiguredFeatures::bootstrap
        );
        registryBuilder.add(
                Registries.PLACED_FEATURE,
                LUPlacedFeatures::bootstrap
        );
    }
}
