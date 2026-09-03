package net.rinnzenzh.everlastingdusk;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import net.rinnzenzh.everlastingdusk.registries.EverlastingDuskBlocks;
import net.rinnzenzh.everlastingdusk.registries.EverlastingDuskItems;
import net.rinnzenzh.everlastingdusk.world.EverlastingDuskGen;
import net.rinnzenzh.everlastingdusk.world.feature.EverlastingDuskConfiguredFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EverlastingDusk implements ModInitializer {
    public static final String MOD_ID = "everlasting_dusk";
    public static final String NAME = "Everlasting Dusk";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier of(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }

    @Override
    public void onInitialize() {
        EverlastingDuskBlocks.init();
        EverlastingDuskItems.init();
        EverlastingDuskConfiguredFeatures.init();
        EverlastingDuskGen.addCustomFeaturesToExistingBiomes();
    }
}
