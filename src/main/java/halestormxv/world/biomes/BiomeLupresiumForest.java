package halestormxv.world.biomes;

import halestormxv.entity.EntityPhantom;
import halestormxv.init.BlockInit;
import halestormxv.objects.blocks.BlockDirts;
import halestormxv.utility.handlers.EnumHandlerWood;
import halestormxv.world.gen.generators.WorldGenLupresiumTree;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeLupresiumForest extends Biome
{
    protected static final WorldGenAbstractTree TREE = new WorldGenLupresiumTree();

    public BiomeLupresiumForest()
    {
        super(new BiomeProperties("Lupresium Touched").setBaseHeight(0.4f).setHeightVariation(0.5f).setTemperature(0.6f).setWaterColor(40206));
        //topBlock = BlockInit.DIRT.getDefaultState().withProperty(BlockDirts.VARIANT, EnumHandlerWood.EnumTypeWood.LUPRESIUM);
        topBlock = BlockInit.LUPRESIUM_GRASS.getDefaultState();
        //fillerBlock = BlockInit.BLOCK_STONES.getDefaultState();
        fillerBlock = BlockInit.DIRT.getDefaultState().withProperty(BlockDirts.VARIANT, EnumHandlerWood.EnumTypeWood.LUPRESIUM);

        this.decorator.generateFalls = true;
        this.decorator.treesPerChunk = 3;

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableMonsterList.add(new SpawnListEntry(EntityBlaze.class, 6, 1, 3));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 10, 2, 6));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityWitherSkeleton.class, 4, 2, 8));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityPhantom.class, 7, 3, 9));

    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return TREE;
    }
}
