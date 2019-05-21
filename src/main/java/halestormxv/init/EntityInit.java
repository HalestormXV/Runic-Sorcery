package halestormxv.init;

import halestormxv.RunicSorcery;
import halestormxv.entity.EntityCultist;
import halestormxv.entity.EntityHarbinger;
import halestormxv.entity.EntityPhantom;
import halestormxv.utility.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit
{
    private static final Biome[] spawnInAll = new Biome[]
            {
                    Biomes.BEACH, Biomes.MUSHROOM_ISLAND, Biomes.FOREST,
                    Biomes.MUTATED_SWAMPLAND, Biomes.DESERT, Biomes.PLAINS, Biomes.TAIGA, Biomes.BIRCH_FOREST,
                    Biomes.BIRCH_FOREST_HILLS, Biomes.COLD_BEACH, Biomes.SWAMPLAND, Biomes.JUNGLE, Biomes.DEFAULT,
                    Biomes.ICE_PLAINS, Biomes.ICE_MOUNTAINS, Biomes.EXTREME_HILLS
            };

    public static void registerEntities()
    {
        registerEntity("phantom", EntityPhantom.class, Reference.ENTITY_PHANTOM, 32, 13869624, 11797069);
        registerEntity("cultist", EntityCultist.class, Reference.ENTITY_CULTIST, 32, 3133855, 10375936);
        registerEntity("harbinger", EntityHarbinger.class, Reference.ENTITY_HARBINGER, 32, 10199483, 10217659);

        //Register the Spawns
        registerSpawnable(EntityPhantom.class, 8, 1, 3, EnumCreatureType.MONSTER, spawnInAll);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2 )
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, RunicSorcery.instance,
                range, 1, true, color1, color2);
    }

    private static void registerSpawnable(Class entityClass, int weightedProb, int min, int max, EnumCreatureType typeOfCreature, Biome... biomes)
    {
        EntityRegistry.addSpawn(entityClass, weightedProb, min, max, typeOfCreature, biomes);
    }
}
