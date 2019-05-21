package halestormxv;

import halestormxv.commands.LearnSpell;
import halestormxv.commands.SetRuneCraftLevel;
import halestormxv.network.PacketHandler;
import halestormxv.potion.PotionReference;
import halestormxv.potion.recipes.PotionRecipes;
import halestormxv.proxy.CommonProxy;
import halestormxv.utility.Logging;
import halestormxv.utility.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, dependencies=Reference.DEPENDENCIES)
public class RunicSorcery
{
    @Mod.Instance
    public static RunicSorcery instance;
    public static Logger logger;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
    public static CommonProxy proxy;

    public static final CreativeTabs RUNICSORCERY = new ModTab("Runic Sorcery");
    public static final CreativeTabs RUNICSORCERY_SPECIAL = new ModTabEffects("Runic Sorcery Special");
    public static DamageSource aetherChaos = new DamageSource("aetherChaos");

    static { FluidRegistry.enableUniversalBucket(); }

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
        PotionReference.INSTANCE.getClass();
        Logging.getLogger().info("Pre Initialize");
        logger = event.getModLog();
        proxy.preInit(event);
        PacketHandler.setupMessages();
    }
    @Mod.EventHandler
    public static void init(FMLInitializationEvent event)
    {
        Logging.getLogger().info("Initialize");
        PotionRecipes.registerRecipes();
        proxy.init(event);
    }
    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event)
    {
        Logging.getLogger().info("Post Initialize");
        //LevelExpCalc.runeCraftLevelCalc();
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public static void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new SetRuneCraftLevel());
        event.registerServerCommand(new LearnSpell());
    }
}
