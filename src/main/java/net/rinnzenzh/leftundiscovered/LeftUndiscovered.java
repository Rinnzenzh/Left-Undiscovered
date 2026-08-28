package net.rinnzenzh.leftundiscovered;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import net.rinnzenzh.leftundiscovered.registries.LUBlocks;
import net.rinnzenzh.leftundiscovered.registries.LUItems;
//import net.rinnzenzh.leftundiscovered.registries.LUTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeftUndiscovered implements ModInitializer {
    public static final String MOD_ID = "left_undiscovered";
    public static final String NAME = "Left Undiscovered";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier of(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }

    @Override
    public void onInitialize() {
        LUBlocks.init();
        LUItems.init();
        //LUTab.init();
    }
}
