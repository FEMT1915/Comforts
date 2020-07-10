package voronoiaoc.byg.common.world.feature.features.nether.glowstonegardens;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraftforge.common.Tags;
import voronoiaoc.byg.core.byglists.BYGBlockList;

import java.util.Random;

public class WeepingRootsPlant extends Feature<NoFeatureConfig> {
    public WeepingRootsPlant(Codec<NoFeatureConfig> config) {
        super(config);
    }

    public boolean func_230362_a_(ISeedReader worldIn, StructureManager structureManager, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int randLength = rand.nextInt(7) + 3;

        if (!worldIn.isAirBlock(pos)) {
            return false;
        } else if (!worldIn.getBlockState(pos.up()).getBlock().isIn(Tags.Blocks.NETHERRACK) || !worldIn.getBlockState(pos.up()).getBlock().isIn(Tags.Blocks.STONE) && !worldIn.func_230315_m_().func_236040_e_()) {
            return false;
        } else {
            for (int WeepingRootPlantLength = 0; WeepingRootPlantLength <= randLength; WeepingRootPlantLength++) {
                BlockPos.Mutable mutable = new BlockPos.Mutable().setPos(pos.getX(), pos.getY() - WeepingRootPlantLength, pos.getZ());
//            if (worldIn.getBlockState(mutable).getBlock() == Blocks.AIR)
                worldIn.setBlockState(mutable, BYGBlockList.WEEPING_ROOTS_PLANT.getDefaultState(), 10);

            }
            return true;
        }
    }
}