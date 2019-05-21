package halestormxv.world.biomes;

import halestormxv.entity.EntityCultist;
import halestormxv.init.BlockInit;
import halestormxv.objects.blocks.BlockDirts;
import halestormxv.utility.handlers.EnumHandlerWood;
import halestormxv.world.gen.generators.WorldGenMysticTree;
import net.minecraft.entity.monster.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeMysticLands extends Biome
{
    protected static final WorldGenAbstractTree TREE = new WorldGenMysticTree();

    public BiomeMysticLands()
    {
        super(new BiomeProperties("Mystic Lands").setBaseHeight(0.2f).setHeightVariation(0.8f).setTemperature(0.06f).setWaterColor(12013822).setSnowEnabled());
        //topBlock = BlockInit.DIRT.getDefaultState().withProperty(BlockDirts.VARIANT, EnumHandlerWood.EnumTypeWood.MYSTIC);
        topBlock = BlockInit.MYSTIC_GRASS.getDefaultState();
        //fillerBlock = BlockInit.BLOCK_STONES.getDefaultState().withProperty(BlockStones.VARIANT, EnumHandlerStone.EnumTypeStone.MYSTIC_SMOOTHSTONE);
        fillerBlock = BlockInit.DIRT.getDefaultState().withProperty(BlockDirts.VARIANT, EnumHandlerWood.EnumTypeWood.MYSTIC);

        this.decorator.generateFalls = true;
        this.decorator.treesPerChunk = 2;
        this.decorator.bigMushroomsPerChunk = 3;

        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableMonsterList.add(new SpawnListEntry(EntityCultist.class, 6, 1, 3));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 10, 2, 6));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 4, 2, 8));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 2, 1, 2));

    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return TREE;
    }
}
