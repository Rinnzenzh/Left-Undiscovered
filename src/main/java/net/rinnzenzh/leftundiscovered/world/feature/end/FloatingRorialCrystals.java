package net.rinnzenzh.leftundiscovered.world.feature.end;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.rinnzenzh.leftundiscovered.misc.AllPurposeUtility;
import net.rinnzenzh.leftundiscovered.registries.LUBlocks;

public class FloatingRorialCrystals extends Feature<NoneFeatureConfiguration> {

    public FloatingRorialCrystals() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        WorldGenLevel level = featurePlaceContext.level();
        BlockPos center = featurePlaceContext.origin();
        RandomSource random = featurePlaceContext.random();

        center = new BlockPos(center.getX(), AllPurposeUtility.randRange(16, 96, random), center.getZ());

        if (!level.getBlockState(center).isAir() || !level.getBlockState(center.above(10)).isAir() || !level.getBlockState(center.below(10)).isAir()) {
            return false;
        }

        for (Direction dir: AllPurposeUtility.horizontal()) {
            if (!level.getBlockState(center.relative(dir, 10)).isAir()) {
                return false;
            }
        }
        BlockStateProvider pillarProviderForTheCrystalFeatureThatHasAnExtremelyLongNameForNoParticularReasonAtAllThisIsJustToConfuseSomePeopleSoYeahIdSayThisEndsHere = new WeightedStateProvider(WeightedList.<BlockState>builder()
                .add(LUBlocks.RORIAL_CRYSTAL_PILLAR_WITH_COLOR.get(DyeColor.BLUE).defaultBlockState(), 2)
                .add(LUBlocks.RORIAL_CRYSTAL_PILLAR_WITH_COLOR.get(DyeColor.PURPLE).defaultBlockState(), 2)
                .add(LUBlocks.RORIAL_CRYSTAL_PILLAR_WITH_COLOR.get(DyeColor.MAGENTA).defaultBlockState(), 2)
                .build());

        BlockState pillarBlockVariableThatAlsoHasALongNameButNotAsLongAsTheProvidersName = pillarProviderForTheCrystalFeatureThatHasAnExtremelyLongNameForNoParticularReasonAtAllThisIsJustToConfuseSomePeopleSoYeahIdSayThisEndsHere.getState(level, random, center);

        int height = AllPurposeUtility.randRange(8, 12, random);
        makeCluster(level, center, height, random, pillarBlockVariableThatAlsoHasALongNameButNotAsLongAsTheProvidersName);

        int satellites = random.nextInt(5);
        float angleDelta = (float) Math.PI * 2 / satellites;
        float angle = (float) Math.PI * 2 * random.nextFloat();

        for (int i = 0; i < satellites; i++) {
            float distance = height * AllPurposeUtility.randRange(0.75F, 1.25F, random);
            int dx = Mth.floor((float) Math.sin(angle) * distance + 0.5F);
            int dz = Mth.floor((float) Math.cos(angle) * distance + 0.5F);
            int h = Mth.floor(AllPurposeUtility.randRange(height * 0.3F, height * 0.7F, random));
            if (h < 2) {
                continue;
            }
            makeCluster(level, center.offset(dx, 0, dz), h, random, pillarBlockVariableThatAlsoHasALongNameButNotAsLongAsTheProvidersName);
            angle += angleDelta;
        }

        return true;
    }

    private void makeCluster(WorldGenLevel level, BlockPos center, int height, RandomSource random, BlockState pillar) {
        float radius = height * 0.2F;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int count = (int) AllPurposeUtility.randRange(radius * 5, radius * 10, random);

        for (int i = 0; i < count; i++) {
            int px = Mth.floor(Mth.clamp((float) random.nextGaussian() * radius * 0.3F, -radius, radius) + 0.5F);
            int pz = Mth.floor(Mth.clamp((float) random.nextGaussian() * radius * 0.3F, -radius, radius) + 0.5F);
            pos.setX(px + center.getX());
            pos.setZ(pz + center.getZ());
            int h = Mth.floor(height - AllPurposeUtility.length(px, pz) * 3 - random.nextInt(2));
            int minY = center.getY() - h;
            int maxY = center.getY() + h;

            pos.setY(center.getY());
            if (!level.getBlockState(pos).isAir()) {
                continue;
            }

            for (int py = minY; py <= maxY; py++) {
                pos.setY(py);
                if (level.getBlockState(pos).isAir()) {
                    level.setBlock(pos, pillar, 18);
                }
            }
        }
    }
}

