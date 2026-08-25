package net.rinnzenzh.leftundiscovered.registries;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.material.Fluids;
import net.rinnzenzh.leftundiscovered.LeftUndiscovered;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LUItems {

    public static Map<Item, ResourceKey<Item>> idLookup = new HashMap<>();

    public static Item register(String name) {
        return register(name, new Properties());
    }

    public static Item register(String name, Properties settings) {
        return register(name, Item::new, settings);
    }

    public static Item register(
            String name,
            Function<Properties, Item> factory,
            Properties settings
    ) {
        ResourceKey<Item> id = ResourceKey.create(Registries.ITEM, LeftUndiscovered.of(name));
        Item item = factory.apply(settings.setId(id));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }
        return register(id, item);
    }

    public static Item register(ResourceKey<Item> id, Item item) {
        idLookup.put(item, id);
        return Registry.register(BuiltInRegistries.ITEM, id, item);
    }
    public static void initialize() {
    }
}
