package net.rinnzenzh.everlastingdusk.world;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.timeline.Timeline;
import net.rinnzenzh.everlastingdusk.registries.EverlastingDuskDimRegistry;
import net.rinnzenzh.everlastingdusk.world.wg.EverlastingDuskNoiseSettings;

import java.util.Optional;

public class EverlastingDuskDimTypeAndStem {

    public static DimensionType bootstrapDimType(
            HolderGetter<Block> blockGetter,
            HolderGetter<Timeline> timelineGetter,
            HolderGetter<WorldClock> clockGetter) {
        Optional<Holder<WorldClock>> fixedClock = Optional.empty();
        HolderSet<Block> infiniburnBlocks = blockGetter.getOrThrow(BlockTags.INFINIBURN_OVERWORLD);
        return new DimensionType(
                true,
                true,
                false,
                false,
                1.0,
                -64,
                384,
                384,
                infiniburnBlocks,
                0.1f,
                new DimensionType.MonsterSettings(new UniformInt(0, 7), 7),
                DimensionType.Skybox.OVERWORLD,
                CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.EMPTY,
                HolderSet.direct(),
                fixedClock
        );
    }
    public static void bootstrapDimension(BootstrapContext<LevelStem> ctx) {
        HolderGetter<DimensionType> dimensionTypes = ctx.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<Biome> biomes = ctx.lookup(Registries.BIOME);
        HolderGetter<StructureSet> structures = ctx.lookup(Registries.STRUCTURE_SET);
        HolderGetter<NoiseGeneratorSettings> noiseSettings = ctx.lookup(Registries.NOISE_SETTINGS);

        ctx.register(EverlastingDuskDimRegistry.EVERLASTING_DUSK_STEM, new LevelStem(
                dimensionTypes.getOrThrow(EverlastingDuskDimRegistry.EVERLASTING_DUSK_TYPE),
                new NoiseBasedChunkGenerator(
                        new FixedBiomeSource(biomes.getOrThrow(Biomes.PLAINS)),
                        noiseSettings.getOrThrow(EverlastingDuskNoiseSettings.DIMNOISESETTINGS)
                )
        ));
    }
}
