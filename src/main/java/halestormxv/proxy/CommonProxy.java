package halestormxv.proxy;

import halestormxv.abilities.RuneBlade_Abilities;
import halestormxv.capabilities.learnedspells.ILearnedSpells;
import halestormxv.capabilities.learnedspells.LearnedSpellsMain;
import halestormxv.capabilities.runebag.RuneBagFunctions;
import halestormxv.capabilities.runebag.RuneBagStorage;
import halestormxv.capabilities.spellcastlevel.SpellCastLvLFunctions;
import halestormxv.capabilities.spellcastlevel.SpellCastLvLStorage;
import halestormxv.capabilities.runebag.IRuneBagProvider;
import halestormxv.capabilities.runecrafting.rcLvl_Functions;
import halestormxv.capabilities.runecrafting.rcLvl_Storage;
import halestormxv.gui.handlers.GeneralGuiHandler;
import halestormxv.init.BiomeInit;
import halestormxv.init.BlockInit;
import halestormxv.init.EntityInit;
import halestormxv.init.ItemInit;
import halestormxv.objects.items.spellblades.SpellBlade_Abilities;
import halestormxv.utility.Reference;
import halestormxv.capabilities.CapabilityHandler;
import halestormxv.utility.handlers.EventHandler;
import halestormxv.utility.handlers.LootTableHandler;
import halestormxv.utility.handlers.SoundsHandler;
import halestormxv.utility.handlers.TileEntityHandler;
import halestormxv.capabilities.runecrafting.IRuneCraftLevel;
import halestormxv.capabilities.spellcastlevel.ISpellCastLevel;
import halestormxv.world.dimensions.ModDimensions;
import halestormxv.world.gen.WorldGenAdvanced;
import halestormxv.world.gen.WorldGenCustomOres;
import halestormxv.world.gen.WorldGenCustomStuffs;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CommonProxy
{
    @Nullable
    public EntityPlayer getMyPlayer(MessageContext ctx)
    {
        return ctx.getServerHandler().player;
    }

    public IRuneBagProvider getClientBagProps() { return null; }
    public ILearnedSpells getClientData() { return null; }

    public void preInit(FMLPreInitializationEvent e)
    {
        GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
        GameRegistry.registerWorldGenerator(new WorldGenCustomStuffs(), 0);
        GameRegistry.registerWorldGenerator(new WorldGenAdvanced(), 1);
        BiomeInit.registerBiomes();
        EntityInit.registerEntities();
        CapabilityManager.INSTANCE.register(IRuneCraftLevel.class, new rcLvl_Storage(), rcLvl_Functions.class);
        CapabilityManager.INSTANCE.register(IRuneBagProvider.class, new RuneBagStorage(), RuneBagFunctions.class);
        CapabilityManager.INSTANCE.register(ISpellCastLevel.class, new SpellCastLvLStorage(), SpellCastLvLFunctions.class);
        CapabilityManager.INSTANCE.register(ILearnedSpells.class, new LearnedSpellsMain.LearnedSpellsStorage(), LearnedSpellsMain.LearnedSpellsFunctions.class);
        ModDimensions.init();
    }

    public void init(FMLInitializationEvent e)
    {
        SoundsHandler.registerSounds();
        NetworkRegistry.INSTANCE.registerGuiHandler(Reference.MODID, new GeneralGuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e)
    {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new LootTableHandler());
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        MinecraftForge.EVENT_BUS.register(new RuneBlade_Abilities());
        MinecraftForge.EVENT_BUS.register(new SpellBlade_Abilities());
    }

    public void registerItemRenderer(Item item, int meta, String id) {}

    public void registerVariantRenderer(Item item, int meta, String filename, String id) {}


    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
        TileEntityHandler.registerTileEntities();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    public void updatePlayerPotion(EntityPlayer e, PotionEffect fx) {
        ((EntityPlayerMP)e).connection.sendPacket(new SPacketEntityEffect(e.getEntityId(), fx));
    }

    public void playDispelSound() {}

}
