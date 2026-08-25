package net.rinnzenzh.leftundiscovered.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.*;
import net.rinnzenzh.leftundiscovered.LeftUndiscovered;

import java.util.function.Function;

public class LUBlocks {



    public static Block register(String name, Function<Properties, Block> factory) {
        return register(name, factory, true);
    }

    public static Block register(String name, Function<Properties, Block> factory, boolean hasItem) {
        return register(name, factory, Properties.of(), hasItem);
    }

    private static Block register(String name, Function<Properties, Block> factory, Properties settings) {
        return register(name, factory, settings, true);
    }

    private static Block register(
            String name,
            Function<Properties, Block> factory,
            Properties settings,
            boolean hasItem
    ) {
        Identifier id = LeftUndiscovered.of(name);
        Block block = factory.apply(settings.setId(ResourceKey.create(Registries.BLOCK, id)));

        if (hasItem) {
            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);
            Registry.register(BuiltInRegistries.ITEM, itemKey,
                    new BlockItem(block, new Item.Properties().setId(itemKey)));
        }
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }
}
