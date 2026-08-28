/*package net.rinnzenzh.leftundiscovered.registries;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.rinnzenzh.leftundiscovered.LeftUndiscovered;

public final class LUTab {

    public static final CreativeModeTab LUTab = register("lu_items",
            FabricCreativeModeTab.builder()
                    .icon(() -> new ItemStack(LUItems.RORIAL_CRYSTAL))
                    .title(Component.translatable("itemGroup.left_undiscovered.luitems"))
                    .displayItems((displayContext, entries) -> BuiltInRegistries.ITEM.keySet()
                            .stream()
                            .sorted()
                            //.filter(key -> key.getNamespace().equals(LeftUndiscovered.MOD_ID) && !key.getPath()
                            //        .endsWith("wall_sign") && !key.getPath().endsWith("wall_hanging_sign"))
                            .map(BuiltInRegistries.ITEM::getValue)
                            .forEach(entries::accept))
                    .build());

    public static <T extends CreativeModeTab> T register(String name, T itemGroup) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, LeftUndiscovered.of(name),
                itemGroup);
    }

    public static void init() {

    }

}*/
