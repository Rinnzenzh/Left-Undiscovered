package net.rinnzenzh.everlastingdusk.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.item.Item;
import net.rinnzenzh.everlastingdusk.registries.EverlastingDuskBlocks;
import net.rinnzenzh.everlastingdusk.registries.EverlastingDuskItems;

import java.util.Arrays;

public class EverlastingDuskModelsProvider extends FabricModelProvider {

    protected EverlastingDuskModelsProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerator) {
        blockModelGenerator.createTrivialCube(EverlastingDuskBlocks.KARBIUM);

        /*Identifier verticalModel = TexturedModel.COLUMN.create(LUBlocks.RORIAL_CRYTSAL_BLOCK, blockModelGenerator.modelOutput);
        Identifier horizontalModel = TexturedModel.COLUMN_HORIZONTAL.create(LUBlocks.RORIAL_CRYTSAL_BLOCK, blockModelGenerator.modelOutput);

        blockModelGenerator.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(LUBlocks.RORIAL_CRYTSAL_BLOCK, BlockModelGenerators.plainVariant(verticalModel))
                        .with(PropertyDispatch.modify(BlockStateProperties.AXIS)
                                .select(Direction.Axis.Y, BlockModelGenerators.NOP)
                                .select(Direction.Axis.Z, BlockModelGenerators.X_ROT_90.then(BlockModelGenerators.UV_LOCK))
                                .select(Direction.Axis.X, BlockModelGenerators.X_ROT_90.then(BlockModelGenerators.Y_ROT_90).then(BlockModelGenerators.UV_LOCK))
                        )
        );*/
        EverlastingDuskBlocks.RORIAL_CRYSTAL_PILLAR_WITH_COLOR.forEach((dyeColor, block) ->
            blockModelGenerator.createAxisAlignedPillarBlock(block, TexturedModel.COLUMN)
        );

    }
    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        for (Item item : Arrays.asList(
                EverlastingDuskItems.RORIAL_CRYSTAL
        )) {
            itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
        }
    }
}
