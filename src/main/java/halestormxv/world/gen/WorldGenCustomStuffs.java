package halestormxv.world.gen;

import halestormxv.init.BlockInit;
import halestormxv.objects.blocks.BlockDirts;
import halestormxv.utility.HighQualityRandom;
import halestormxv.utility.handlers.EnumHandlerWood;
import halestormxv.world.biomes.BiomeLupresiumForest;
import halestormxv.world.biomes.BiomeMysticLands;
import halestormxv.world.gen.generators.WorldGenLupresiumTree;
import halestormxv.world.gen.generators.WorldGenMysticTree;
import halestormxv.world.gen.generators.WorldGenStructure;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WorldGenCustomStuffs implements IWorldGenerator {
    /*###STRUCTURES###*/
    //private static final WorldGenStructure ALTAR_OF_FIRE = new WorldGenStructure("altar_fire");

    /*###TREES###*/
    private final WorldGenerator LUPRESIUM_TREE = new WorldGenLupresiumTree();
    private final WorldGenerator MYSTIC_TREE = new WorldGenMysticTree();
    private Block lupresiumDirt = BlockInit.DIRT.getDefaultState().withProperty(BlockDirts.VARIANT, EnumHandlerWood.EnumTypeWood.LUPRESIUM).getBlock();
    private Block mysticDirt = BlockInit.DIRT.getDefaultState().withProperty(BlockDirts.VARIANT, EnumHandlerWood.EnumTypeWood.MYSTIC).getBlock();

    @Override
    //Greater the Chance the Less Likely it is to Spawn. If chance is 100 then there is a 1 in 100 chance of equaling 0. Reaching 0 will trigger the spawn.
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case -1:
                break;

            case 0:
                //generateStructures(new WorldGenStructure("cultist_hut"), world, random, chunkX, chunkZ , 10, getBiomeList().toArray(new Class[getBiomeList().size()]));
                generateRuneAltar(new WorldGenStructure("air_temple"), world, random, chunkX, chunkZ, 360, 98, 176);
                generateRuneAltar(new WorldGenStructure("earth_temple"), world, random, chunkX , chunkZ, 280, 84, 152);
                generateTrees(LUPRESIUM_TREE, world, random, chunkX, chunkZ, 10, lupresiumDirt, BiomeLupresiumForest.class);
                generateTrees(MYSTIC_TREE, world, random, chunkX, chunkZ, 10, mysticDirt, BiomeMysticLands.class);
                break;

            case 1:
                break;

            case 2:
                break;

        }

    }

    private void generateRuneAltar(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, int yMin, int yMax, Class<?>... classes) {
        ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
        HighQualityRandom highQualityRandom = new HighQualityRandom();
        int x = (chunkX * 16) + random.nextInt(15);
        int z = (chunkZ * 16) + random.nextInt(15);
        int y = highQualityRandom.nextInt(yMax - yMin) + yMin;
        BlockPos pos = new BlockPos(x, y, z);
        Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
        if (world.getWorldType() != WorldType.FLAT) {
            if (classesList.contains(biome) || classesList.isEmpty()) {
                if (random.nextInt(chance) == 0) {
                    generator.generate(world, random, pos);
                }
            }
        }
    }

    private void generateTrees(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes) {
        ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
        int x = (chunkX * 16) + random.nextInt(15);
        int z = (chunkZ * 16) + random.nextInt(15);
        int y = calculateGenerationHeight(world, x, z);
        BlockPos pos = new BlockPos(x, y, z);
        Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
        if (world.getWorldType() != WorldType.FLAT) {
            if (classesList.contains(biome)) {
                if (random.nextInt(chance) == 0) {
                    generator.generate(world, random, pos);
                }
            }
        }
    }

    private static int calculateGenerationHeight(World world, int x, int z) {
        int y = world.getHeight();
        boolean foundGround = false;
        while (!foundGround && y-- >= 64)
        {
            Block blockAt = world.getBlockState(new BlockPos(x, y, z)).getBlock();
            foundGround =  blockAt == Blocks.WATER
                    || blockAt == Blocks.FLOWING_WATER
                    || blockAt == Blocks.GRASS
                    || blockAt == Blocks.SAND
                    || blockAt == Blocks.SNOW
                    || blockAt == Blocks.SNOW_LAYER
                    || blockAt == Blocks.GLASS
                    || blockAt == Blocks.DIRT
                    || blockAt == Blocks.MYCELIUM;
        }
        return y;
    }

    private static List<Class> getBiomeList() {
        return ForgeRegistries.BIOMES.getValues().stream().map(Biome::getBiomeClass).collect(Collectors.toList());
    }
}
                          //===================================================\\
                         //-------------------REFERENCE POINT-------------------\\
                        //=======================================================\\
    /*private void generateStructures(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Class<?>... classes) {
        HighQualityRandom highQualityRandom = new HighQualityRandom();
        ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
        int x = (chunkX * 8) + highQualityRandom.nextInt(15);
        int z = (chunkZ * 8) + highQualityRandom.nextInt(15);
        float y = calculateGenerationHeight(world, x, z);
        for (int xSize = 0; xSize < 9; x++)
        {
            for (int zSize = 0; zSize < 7; z++)
            {
                int oldY = Math.round(y);
                y += calculateGenerationHeight(world, x + xSize, z + zSize);
                y /= 2;
                if (Math.round(y) != oldY)
                {
                    return;
                }
            }
        }
        int struc_y = (int) y - 1;
        BlockPos pos = new BlockPos(x, struc_y, z);
        Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
        if (world.getWorldType() != WorldType.FLAT) {
            if (classesList.contains(biome) || classesList.isEmpty()) {
                if (highQualityRandom.nextInt(chance) == 0)
                {

                    generator.generate(world, highQualityRandom, pos);
                }
            }
        }
    }*/
