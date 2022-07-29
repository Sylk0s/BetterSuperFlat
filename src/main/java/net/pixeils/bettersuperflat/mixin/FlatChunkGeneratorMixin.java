package net.pixeils.bettersuperflat.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.FlatChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(FlatChunkGenerator.class)
public class FlatChunkGeneratorMixin {

    /**
     * @author sylkos
     * @reason to do the thing
     */

    @Overwrite
    public CompletableFuture<Chunk> populateNoise(Executor ex, StructureAccessor structureAccessor, Chunk chunk) {
        for (int j = 0; j < 16; ++j) {
            for (int k = 0; k < 16; ++k) {
                BlockPos pos = chunk.getPos().getStartPos();
                int x = pos.getX() + j;
                int z = pos.getZ() + k;
                // NW is always 0,0
                // 0,0 -1,-1
                //lines
                if(x >= 0 && z >= 0) {
                    if (Math.abs((x<0?x:x+1)) % 512 < 2 || Math.abs((z<0?z:z+1)) % 512 < 2) {
                        // region borders
                        BlockPos blockPos =
                                new BlockPos(j, 0, k);
                        chunk.setBlockState(blockPos, Blocks.BLUE_STAINED_GLASS.getDefaultState(), false);
                    } else if (Math.abs((x<0?x:x+1)) % 128 < 2 || Math.abs((z<0?z:z+1)) % 128 < 2) {
                        // 8x grid
                        BlockPos blockPos =
                                new BlockPos(j, 0, k);
                        chunk.setBlockState(blockPos, Blocks.RED_STAINED_GLASS.getDefaultState(), false);
                    } else if (Math.abs((x<0?x:x+1)) % 16 < 2 || Math.abs((z<0?z:z+1)) % 16 < 2) {
                        // full borders
                        BlockPos blockPos =
                                new BlockPos(j, 0, k);
                        chunk.setBlockState(blockPos, Blocks.LIGHT_GRAY_STAINED_GLASS.getDefaultState(), false);
                    } else {
                        BlockPos blockPos =
                                new BlockPos(j, 0, k);
                        chunk.setBlockState(blockPos, Blocks.WHITE_STAINED_GLASS.getDefaultState(), false);
                    }
                }
                //borders
                if(x >= 0 && z < 0) {
                    if (Math.abs((x<0?x:x+1)) % 512 < 2 || Math.abs((z<0?z:z+1)) % 512 < 2) {
                        // region borders
                        BlockPos blockPos =
                                new BlockPos(j, 0, k);
                        chunk.setBlockState(blockPos, Blocks.BLUE_STAINED_GLASS.getDefaultState(), false);
                    } else {
                        BlockPos blockPos =
                                new BlockPos(j, 0, k);
                        chunk.setBlockState(blockPos, Blocks.WHITE_STAINED_GLASS.getDefaultState(), false);
                    }
                }
                //dots
                if(x < 0 && z >= 0) {
                    // almost works lol
                    if ((Math.abs((x<0?x:x+1)) % 512 < 2 || Math.abs((z<0?z:z+1)) % 512 < 2) && (Math.abs((x<0?x:x+1)) % 16 < 2 && Math.abs((z<0?z:z+1)) % 16 < 2)) {
                        // region borders
                        BlockPos blockPos =
                                new BlockPos(j, 0, k);
                        chunk.setBlockState(blockPos, Blocks.BLUE_STAINED_GLASS.getDefaultState(), false);
                    } else if ((Math.abs((x<0?x:x+1)) % 128 < 2 || Math.abs((z<0?z:z+1)) % 128 < 2) && (Math.abs((x<0?x:x+1)) % 16 < 2 && Math.abs((z<0?z:z+1)) % 16 < 2)) {
                        // 8x grid
                        BlockPos blockPos =
                                new BlockPos(j, 0, k);
                        chunk.setBlockState(blockPos, Blocks.RED_STAINED_GLASS.getDefaultState(), false);
                    } else if (Math.abs((x<0?x:x+1)) % 16 < 2 && Math.abs((z<0?z:z+1)) % 16 < 2) {
                        // full borders
                        BlockPos blockPos =
                                new BlockPos(j, 0, k);
                        chunk.setBlockState(blockPos, Blocks.LIGHT_GRAY_STAINED_GLASS.getDefaultState(), false);
                    } else {
                        BlockPos blockPos =
                                new BlockPos(j, 0, k);
                        chunk.setBlockState(blockPos, Blocks.WHITE_STAINED_GLASS.getDefaultState(), false);
                    }
                }
                //none
                if(x < 0 && z < 0) {
                    chunk.setBlockState(new BlockPos(j,0,k), Blocks.WHITE_STAINED_GLASS.getDefaultState(),false);
                }
            }
        }
        return CompletableFuture.completedFuture(chunk);
    }
}
