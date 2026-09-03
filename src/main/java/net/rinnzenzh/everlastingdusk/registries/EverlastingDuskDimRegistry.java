package net.rinnzenzh.everlastingdusk.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.rinnzenzh.everlastingdusk.EverlastingDusk;
import net.rinnzenzh.everlastingdusk.world.EverlastingDuskDimTypeAndStem;

public class EverlastingDuskDimRegistry {
    public static final ResourceKey<Level> EVERLASTING_DUSK = ResourceKey.create(
            Registries.DIMENSION,
            EverlastingDusk.of("everlasting_dusk_dim")
    );

    public static final ResourceKey<DimensionType> EVERLASTING_DUSK_TYPE = ResourceKey.create(
            Registries.DIMENSION_TYPE,
            EverlastingDusk.of("everlasting_dusk_type")
    );

    public static final ResourceKey<LevelStem> EVERLASTING_DUSK_STEM = ResourceKey.create(
            Registries.LEVEL_STEM,
            EverlastingDusk.of("everlasting_dusk")
    );

    public static void bootstrapDimensionTypes(BootstrapContext<DimensionType> context) {
        var blockGetter = context.lookup(Registries.BLOCK);
        var timelineGetter = context.lookup(Registries.TIMELINE);
        var clockGetter = context.lookup(Registries.WORLD_CLOCK);

        context.register(
                EVERLASTING_DUSK_TYPE,
                EverlastingDuskDimTypeAndStem.bootstrapDimType(blockGetter, timelineGetter, clockGetter)
        );
    }
}
