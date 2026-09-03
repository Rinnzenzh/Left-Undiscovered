package net.rinnzenzh.everlastingdusk.world.wg;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.rinnzenzh.everlastingdusk.EverlastingDusk;

import java.util.List;

public class EverlastingDuskNoiseSettings {

    public static final ResourceKey<NoiseGeneratorSettings> DIMNOISESETTINGS = ResourceKey.create(
            Registries.NOISE_SETTINGS,
            EverlastingDusk.of("everlasting_dusk_noise_settings")
    );

    public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> context) {
        HolderGetter<NormalNoise.NoiseParameters> noiseParams =
                context.lookup(Registries.NOISE);
        HolderGetter<DensityFunction> densityFunctions =
                context.lookup(Registries.DENSITY_FUNCTION);

        Holder<DensityFunction> erosionHolder = densityFunctions.getOrThrow(
                ResourceKey.create(Registries.DENSITY_FUNCTION,
                        Identifier.withDefaultNamespace("overworld_large_biomes/erosion"))
        );
        Holder<DensityFunction> ridgesHolder = densityFunctions.getOrThrow(
                ResourceKey.create(Registries.DENSITY_FUNCTION,
                        Identifier.withDefaultNamespace("overworld/ridges"))
        );
        Holder<DensityFunction> shiftXHolder = densityFunctions.getOrThrow(
                ResourceKey.create(Registries.DENSITY_FUNCTION,
                        Identifier.withDefaultNamespace("shift_x"))
        );
        Holder<DensityFunction> shiftZHolder = densityFunctions.getOrThrow(
                ResourceKey.create(Registries.DENSITY_FUNCTION,
                        Identifier.withDefaultNamespace("shift_z"))
        );

        DensityFunction erosion = new DensityFunctions.HolderHolder(erosionHolder);
        DensityFunction ridges = new DensityFunctions.HolderHolder(ridgesHolder);
        DensityFunction shiftX = new DensityFunctions.HolderHolder(shiftXHolder);
        DensityFunction shiftZ = new DensityFunctions.HolderHolder(shiftZHolder);

        Holder<NormalNoise.NoiseParameters> temperaturoise =
                noiseParams.getOrThrow(ResourceKey.create(Registries.NOISE, Identifier.withDefaultNamespace("temperature")));
        Holder<NormalNoise.NoiseParameters> vegetatioise =
                noiseParams.getOrThrow(ResourceKey.create(Registries.NOISE, Identifier.withDefaultNamespace("vegetation")));
        Holder<NormalNoise.NoiseParameters> graveloise =
                noiseParams.getOrThrow(ResourceKey.create(Registries.NOISE, Identifier.withDefaultNamespace("gravel")));
        Holder<NormalNoise.NoiseParameters> surfaceoise =
                noiseParams.getOrThrow(ResourceKey.create(Registries.NOISE, Identifier.withDefaultNamespace("surface")));
        DensityFunction temperature = DensityFunctions.shiftedNoise2d(shiftX, shiftZ, 0.115, temperaturoise);
        DensityFunction vegetation = DensityFunctions.shiftedNoise2d(shiftX, shiftZ, 0.115, vegetatioise);
        DensityFunction continents = DensityFunctions.constant(0.115);
        DensityFunction depth = DensityFunctions.add(
                DensityFunctions.yClampedGradient(-1, 0, 0.0, 0.05),
                DensityFunctions.add(DensityFunctions.yClampedGradient(58, 59, 0.0, 0.05), DensityFunctions.yClampedGradient(72, 73, 0.0, 0.9)
                )
        );
        DensityFunction colby = DensityFunctions.add(
                DensityFunctions.add(
                        DensityFunctions.constant(-1.0),
                        DensityFunctions.mul(
                                DensityFunctions.constant(2.0),
                                DensityFunctions.add(
                                        DensityFunctions.yClampedGradient(69, 284, 0.76, 0.0),
                                        DensityFunctions.constant(0.1)
                                )
                        )
                ),
                DensityFunctions.interpolated(
                        DensityFunctions.noise(graveloise, 0.08, 0.16)
                ));

        DensityFunction finalDensity = DensityFunctions.max(
                DensityFunctions.min(colby, DensityFunctions.add(
                                DensityFunctions.yClampedGradient(64, 320, -1.0, 0.1),
                                DensityFunctions.noise(graveloise, 0.5, 0.5)
                        )
                ),
                DensityFunctions.add(
                        DensityFunctions.yClampedGradient(-64, 0, 3.0, -1.0),
                        DensityFunctions.noise(surfaceoise, 0.5, 0.5)
                )
        );

        NoiseRouter router = new NoiseRouter(
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                temperature,
                vegetation,
                continents,
                erosion,
                depth,
                ridges,
                DensityFunctions.zero(),
                finalDensity,
                DensityFunctions.zero(),
                DensityFunctions.zero(),
                DensityFunctions.zero()
        );

        List<Climate.ParameterPoint> spawnTargets = List.of(
                Climate.parameters(
                        Climate.Parameter.span(-1f, 1f),
                        Climate.Parameter.span(-1f, 1f),
                        Climate.Parameter.span(-0.11f, 1f),
                        Climate.Parameter.span(-1f, 1f),
                        Climate.Parameter.span(-1f, -0.16f),
                        Climate.Parameter.point(0f), 0f
                ),
                Climate.parameters(
                        Climate.Parameter.span(-1f, 1f),
                        Climate.Parameter.span(-1f, 1f),
                        Climate.Parameter.span(-0.11f, 1f),
                        Climate.Parameter.span(-1f, 1f),
                        Climate.Parameter.span(0.16f, 1f),
                        Climate.Parameter.point(0f), 0f
                )
        );

        SurfaceRules.RuleSource surfaceRule = SurfaceRules.state(Blocks.STONE.defaultBlockState());

        context.register(DIMNOISESETTINGS, new NoiseGeneratorSettings(
                new NoiseSettings(-64, 320, 1, 1), // min_y, height, size_horizontal, size_vertical
                Blocks.STONE.defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                router,
                surfaceRule,
                spawnTargets,
                63,
                false,
                true,
                true,
                true
        ));
    }
}