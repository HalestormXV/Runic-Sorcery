package halestormxv.proxy;

import halestormxv.KeyBindings;
import halestormxv.capabilities.runebag.RuneBagProvider;
import halestormxv.utility.handlers.PotionTooltipHandler;
import halestormxv.capabilities.runebag.IRuneBagProvider;
import halestormxv.init.BlockInit;
import halestormxv.init.ItemInit;
import halestormxv.utility.Reference;
import halestormxv.utility.handlers.InputHandler;
import halestormxv.utility.handlers.RenderHandler;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Nullable
    @Override
    public EntityPlayer getMyPlayer(MessageContext ctx)
    {
        // Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
        // your packets will not work because you will be getting a client
        // player even when you are on the server! Sounds absurd, but it's true.

        // Solution is to double-check side before returning the player:
        return (ctx.side.isClient() ? Minecraft.getMinecraft().player : ctx.getServerHandler().player);
    }

    public IRuneBagProvider getClientBagProps()
    {
        return FMLClientHandler.instance().getClientPlayerEntity().getCapability(RuneBagProvider.RUNEBAG_CAP, null);
    }


    @Override
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    @Override
    public void registerVariantRenderer(Item item, int meta, String filename, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, filename), id));
    }

    @Override
    public void preInit(FMLPreInitializationEvent e)
    {
        super.preInit(e);
        RenderHandler.registerEntityRenders();
        OBJLoader.INSTANCE.addDomain(Reference.MODID);
    }

    @Override
    public void init(FMLInitializationEvent e)
    {
        super.init(e);
        MinecraftForge.EVENT_BUS.register(new InputHandler());
        KeyBindings.init();
        MinecraftForge.EVENT_BUS.register(new PotionTooltipHandler());
    }


    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        for(Item item : ItemInit.ITEMS)
        {
            if(item instanceof IHasModel)
            {
                ((IHasModel)item).registerModels();
            }
        }

        for(Block block : BlockInit.BLOCKS)
        {
            if(block instanceof IHasModel)
            {
                ((IHasModel)block).registerModels();
            }
        }

    }

    @Override
    public void playDispelSound() {
        EntityPlayer player = Minecraft.getMinecraft().player;
        player.getEntityWorld().playSound(player.posX, player.posY, player.posZ, SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.elder_guardian.curse")), SoundCategory.AMBIENT, 1.5F, 8F, false);
    }

    @Override
    public void updatePlayerPotion(EntityPlayer e, PotionEffect fx)
    {
        e.addPotionEffect(new PotionEffect(fx));
    }
}
