package halestormxv.init;

import halestormxv.utility.Logging;
import halestormxv.world.biomes.BiomeLupresiumForest;
import halestormxv.world.biomes.BiomeMysticLands;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit
{
    public static final Biome LUPRESIUM_FOREST = new BiomeLupresiumForest();
    public static final Biome MYSTIC_LANDS = new BiomeMysticLands();

    public static void registerBiomes()
    {
        initBiomeNoOverworld(LUPRESIUM_FOREST,"Lupresium Touched", Type.HILLS, Type.MAGICAL, Type.DRY);
        initBiomeNoOverworld(MYSTIC_LANDS, "Mystic Lands", Type.MAGICAL, Type.SPOOKY, Type.LUSH );
    }

    private static Biome initBiome(Biome biome, int weight, String name, BiomeType biomeType, Type... types)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(biomeType, new BiomeEntry(biome, weight));
        BiomeManager.addSpawnBiome(biome);
        return biome;
    }

    private static Biome initBiomeNoOverworld(Biome biome, String name, Type... types)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, types);
        return biome;
    }
}
