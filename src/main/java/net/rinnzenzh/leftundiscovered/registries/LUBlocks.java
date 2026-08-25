package net.rinnzenzh.leftundiscovered.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.*;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.rinnzenzh.leftundiscovered.LeftUndiscovered;

import java.util.function.BiFunction;
import java.util.function.Function;

public class LUBlocks {

    public static Block KARBIUM = register("karbium", settings -> new Block(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(5F, 6F).mapColor(MapColor.COLOR_CYAN).requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK).setId(settings.blockId())));

    public static Block RORIAL_CRYTSAL_BLOCK = register("rorial_crystal_block", settings -> new RotatedPillarBlock(
            Properties.of()
                    .instrument(NoteBlockInstrument.HAT)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .isValidSpawn(Blocks::never)
                    .isSuffocating(Blocks::never)
                    .isViewBlocking(Blocks::never)
                    .isRedstoneConductor(Blocks::never)
                    .strength(0.5F)
                    .lightLevel((bs) -> 12)
                    .mapColor(MapColor.COLOR_RED)
                    .setId(settings.blockId())
    ));

    public static Block register(String name, Function<Properties, Block> factory) {
        return register(name, factory, true);
    }

    public static Block register(String name, Function<Properties, Block> factory, boolean hasItem) {
        return register(name, factory, Properties.of(), hasItem);
    }

    private static Block register(
            String name,
            Function<Properties, Block> factory,
            Properties settings,
            boolean hasItem
    ) {
        if (hasItem) {
            BlockItemId id = BlockItemId.create(LeftUndiscovered.of(name), LeftUndiscovered.of(name));
            Block block = factory.apply(settings.setId(id.block()));
            LUItems.register(
                    id.item(),
                    new BlockItem(
                            block,
                            new Item.Properties()
                                    .setId(id.item())
                                    .useBlockDescriptionPrefix()
                                    .requiredFeatures(block.requiredFeatures())
                    )
            );
            return Registry.register(BuiltInRegistries.BLOCK, id.block(), block);
        }
        ResourceKey<Block> id = ResourceKey.create(Registries.BLOCK, LeftUndiscovered.of(name));
        Block block = factory.apply(settings.setId(id));
        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    private static Block registerSpecialBlockItem(
            String name,
            Function<Properties, Block> factory,
            BiFunction<Block, BlockItemId, BlockItem> itemFactory
    ) {
        return registerSpecialBlockItem(name, name, factory, itemFactory);
    }

    private static Block registerSpecialBlockItem(
            String blockName,
            String itemName,
            Function<Properties, Block> factory,
            BiFunction<Block, BlockItemId, BlockItem> itemFactory
    ) {
        BlockItemId id = BlockItemId.create(LeftUndiscovered.of(blockName), LeftUndiscovered.of(itemName));
        Block block = factory.apply(Properties.of().setId(id.block()));
        LUItems.register(id.item(), itemFactory.apply(block, id));
        return Registry.register(BuiltInRegistries.BLOCK, id.block(), block);
    }

    public static void init(){
    }
}
