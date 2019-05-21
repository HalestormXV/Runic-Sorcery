package halestormxv.world.gen;

import halestormxv.init.BlockInit;
import halestormxv.objects.blocks.BlockOres;
import halestormxv.utility.handlers.EnumHandler;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenCustomOres implements IWorldGenerator
{
    private WorldGenerator ore_nether_lupresium, ore_overworld_lupresium, ore_end_lupresium;
    private WorldGenerator ore_nether_apronyx, ore_overworld_apronyx, ore_end_apronyx;
    private WorldGenerator ore_nether_xoshian, ore_overworld_xoshian, ore_end_xoshian;

    public WorldGenCustomOres()
    {
        //Lupresium Generators - The Common Ores
        ore_nether_lupresium = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.LUPRESIUM),
                9, BlockMatcher.forBlock(Blocks.NETHERRACK));
        ore_overworld_lupresium = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.LUPRESIUM),
                9, BlockMatcher.forBlock(Blocks.STONE));
        ore_end_lupresium = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.LUPRESIUM),
                6, BlockMatcher.forBlock(Blocks.END_STONE));

        //Apronyx Generators - The Rarest Ores
        ore_nether_apronyx = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.APRONYX),
                5, BlockMatcher.forBlock(Blocks.NETHERRACK));
        ore_overworld_apronyx = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.APRONYX),
                5, BlockMatcher.forBlock(Blocks.STONE));
        ore_end_apronyx = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.APRONYX),
                5, BlockMatcher.forBlock(Blocks.END_STONE));

        //Xoshian Generators - The Rare Ores
        ore_nether_xoshian = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.XOSHIAN),
                7, BlockMatcher.forBlock(Blocks.NETHERRACK));
        ore_overworld_xoshian = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.XOSHIAN),
                7, BlockMatcher.forBlock(Blocks.STONE));
        ore_end_xoshian = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.XOSHIAN),
                7, BlockMatcher.forBlock(Blocks.END_STONE));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        switch(world.provider.getDimension())
        {
            case -1:
                runGenerator(ore_nether_lupresium, world, random, chunkX, chunkZ, 15, 35, 100);
                runGenerator(ore_nether_xoshian, world, random, chunkX, chunkZ, 5, 0, 100);
                runGenerator(ore_nether_apronyx, world, random, chunkX, chunkZ, 30, 0, 100);
                break;
            case 0:
                runGenerator(ore_overworld_lupresium, world, random, chunkX, chunkZ, 18, 32, 100);
                runGenerator(ore_overworld_xoshian, world, random, chunkX, chunkZ, 15, 18, 30);
                runGenerator(ore_nether_apronyx, world, random, chunkX, chunkZ, 13, 1, 16);
                break;
            case 1:
                runGenerator(ore_end_lupresium, world, random, chunkX, chunkZ, 30, 0, 256);
                runGenerator(ore_end_xoshian, world, random, chunkX, chunkZ, 30, 0, 256);
                runGenerator(ore_end_apronyx, world, random, chunkX, chunkZ, 30, 0, 256);
                break;
            default:
                runGenerator(ore_overworld_lupresium, world, random, chunkX, chunkZ, 30, 32, 100);
                break;
        }
    }

    private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight)
    {
        if(minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore generated out of bounds!");

        int heightDiff = maxHeight - minHeight + 1;
        for(int i = 0; i < chance; i++)
        {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunkZ * 16 + rand.nextInt(16);

            gen.generate(world, rand, new BlockPos(x, y, z));
        }
    }

}
