package net.rinnzenzh.leftundiscovered.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.rinnzenzh.leftundiscovered.registries.LUBlocks;

public class LUModelsProvider extends FabricModelProvider {

    protected LUModelsProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerator) {
        blockModelGenerator.createTrivialCube(LUBlocks.KARBIUM);
    }
    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
    }
}
