package voronoiaoc.byg.common.world.feature.features.nether.glowstonegardens;

import com.mojang.serialization.Codec;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;
import voronoiaoc.byg.core.byglists.BYGBlockList;

import java.util.Random;

public class NetherBristle extends Feature<NoFeatureConfig> {
    public NetherBristle(Codec<NoFeatureConfig> config) {
        super(config);
    }

    public static final EnumProperty<DoubleBlockHalf> HALF;

    public boolean func_230362_a_(ISeedReader worldIn, StructureManager structureManager, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (!worldIn.isAirBlock(pos) || worldIn.getBlockState(pos.down()).getBlock() != BYGBlockList.OVERGROWN_NETHERRACK) {
            return false;
        } else {
            worldIn.setBlockState(pos, BYGBlockList.NETHER_BRISTLE.getDefaultState(), 10);
            worldIn.setBlockState(pos.offset(Direction.UP), BYGBlockList.NETHER_BRISTLE.getDefaultState().with(HALF, DoubleBlockHalf.UPPER), 10);
            return true;
        }
    }

    static {
        HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    }
}