package halestormxv.world.dimensions.gen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import halestormxv.entity.EntityCultist;
import halestormxv.entity.EntityPhantom;
import halestormxv.objects.fluids.ModFluids;
import halestormxv.world.biomes.BiomeLupresiumForest;
import halestormxv.world.gen.WorldGenMysteriumLake;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraftforge.event.terraingen.TerrainGen;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE;

public class MysteriumChunkGenerator implements IChunkGenerator
{
    /**
     * This is the ChunkGenerator. This actually makes the landscape. It basically generates the caves and the terrain.
     * The NormalTerrainGenerator class that we made basically is what fills in  the heightmap and then genrates a chunk based
     * on that. So this is where some of the good stuff can be changed around.
     */
        private final World worldObj;
        private Random random;
        private Biome[] biomesForGeneration;

        private List<Biome.SpawnListEntry> mobs = Lists.newArrayList(
                new Biome.SpawnListEntry(EntityCultist.class, 100, 2, 2),
                new Biome.SpawnListEntry(EntityPhantom.class, 100, 2, 4)
        );

        private MapGenBase caveGenerator = new MapGenCaves();
        private NormalTerrainGenerator terraingen = new NormalTerrainGenerator();

        public MysteriumChunkGenerator(World worldObj)
        {
            this.worldObj = worldObj;
            long seed = worldObj.getSeed();
            this.random = new Random((seed + 516) * 314);
            terraingen.setup(worldObj, random);
            caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, CAVE);
        }

        @Override
        public Chunk generateChunk(int x, int z) {
            ChunkPrimer chunkprimer = new ChunkPrimer();

            // Setup biomes for terraingen
            this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
            terraingen.setBiomesForGeneration(biomesForGeneration);
            terraingen.generate(x, z, chunkprimer);

            // Setup biomes again for actual biome decoration
            this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
            // This will replace stone with the biome specific stones
            terraingen.replaceBiomeBlocks(x, z, chunkprimer, this, biomesForGeneration);

            // Generate caves
            this.caveGenerator.generate(this.worldObj, x, z, chunkprimer);

            Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);

            byte[] biomeArray = chunk.getBiomeArray();
            for (int i = 0; i < biomeArray.length; ++i) {
                biomeArray[i] = (byte)Biome.getIdForBiome(this.biomesForGeneration[i]);
            }

            chunk.generateSkylightMap();
            return chunk;
        }

        @Override
        public void populate(int x, int z)
        {
            BlockFalling.fallInstantly = true;
            int k = x * 16;
            int l = z * 16;
            BlockPos pos = new BlockPos(k, 0, l);
            Biome biome = worldObj.getBiome(pos.add(16, 0, 16));
            random.setSeed(worldObj.getSeed());
            long i1 = random.nextLong() / 2L * 2L + 1L;
            long j1 = random.nextLong() / 2L * 2L + 1L;
            random.setSeed(x * i1 + z * j1 ^ worldObj.getSeed());
            boolean flag = false;
            int k1;
            int l1;
            int i2;

            // Add biome decorations (like flowers, grass, trees, ...)
            biome.decorate(this.worldObj, this.random, pos);
            if (TerrainGen.populate(this, worldObj, random, x, z, flag, LAKE) &&
                    !flag && random.nextInt(6) == 0)
            {
                k1 = random.nextInt(16) + 8;
                l1 = random.nextInt(128);
                i2 = random.nextInt(16) + 8;
                new WorldGenMysteriumLake(ModFluids.LIQUID_MYSTERIUM.getBlock()).generate(worldObj, random, pos.add(k1, l1, i2));
            }

            // Make sure animals appropriate to the biome spawn here when the chunk is generated
            WorldEntitySpawner.performWorldGenSpawning(this.worldObj, biome, k + 8, l + 8, 16, 16, this.random);

            BlockFalling.fallInstantly = false;
        }

        @Override
        public boolean generateStructures(Chunk chunkIn, int x, int z) {
            return false;
        }

        @Override
        public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
            // If you want normal creatures appropriate for this biome then uncomment the
            // following two lines:
//        Biome biome = this.worldObj.getBiome(pos);
//        return biome.getSpawnableList(creatureType);

            if (creatureType == EnumCreatureType.MONSTER){
                return mobs;
            }
            return ImmutableList.of();

        }

        @Nullable
        @Override
        public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
            return null;
        }

        @Override
        public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
            return false;
        }

        @Override
        public void recreateStructures(Chunk chunkIn, int x, int z) {

        }
}
