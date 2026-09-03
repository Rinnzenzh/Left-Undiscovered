package net.rinnzenzh.everlastingdusk.registries;

import com.google.common.collect.Maps;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.*;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.rinnzenzh.everlastingdusk.EverlastingDusk;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class EverlastingDuskBlocks {

    public static Block KARBIUM = register("karbium", settings -> new Block(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(5F, 6F).mapColor(MapColor.COLOR_CYAN).requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK).setId(settings.blockId())));

    public static final Map<DyeColor, Block> RORIAL_CRYSTAL_PILLAR_WITH_COLOR = Maps.newEnumMap(DyeColor.class);

    static {

        for (int i = 0; i < 16; i++) {
            DyeColor color = DyeColor.byId(i);
            Block rorialcolored = register(
                    "rorial_crystal_pillar_" + color.getName(),
                    settings -> new RotatedPillarBlock(
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
                                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                                    .setId(settings.blockId()))
            );
            RORIAL_CRYSTAL_PILLAR_WITH_COLOR.put(color, rorialcolored);
        }
    }

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
            BlockItemId id = BlockItemId.create(EverlastingDusk.of(name), EverlastingDusk.of(name));
            Block block = factory.apply(settings.setId(id.block()));
            EverlastingDuskItems.register(
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
        ResourceKey<Block> id = ResourceKey.create(Registries.BLOCK, EverlastingDusk.of(name));
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
        BlockItemId id = BlockItemId.create(EverlastingDusk.of(blockName), EverlastingDusk.of(itemName));
        Block block = factory.apply(Properties.of().setId(id.block()));
        EverlastingDuskItems.register(id.item(), itemFactory.apply(block, id));
        return Registry.register(BuiltInRegistries.BLOCK, id.block(), block);
    }

    public static void init(){
    }
}
