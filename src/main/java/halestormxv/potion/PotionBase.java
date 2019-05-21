package halestormxv.potion;

import halestormxv.utility.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public abstract class PotionBase extends Potion
{

    private static final ArrayList<Potion> recorder = new ArrayList<Potion>();
    public static final ResourceLocation RUNIC_SORCERY_FX = new ResourceLocation(Reference.MODID, "textures/icons/potions_alt.png");

    public PotionBase(boolean isBadEffectIn, int liquidColorIn, String name)
    {
        super(isBadEffectIn, liquidColorIn);
        this.setPotionName("effect."+name);
        if (!isBadEffectIn) this.setBeneficial();
        this.setRegistryName(new ResourceLocation(Reference.MODID, "effect."+name));
        recorder.add(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getStatusIconIndex()
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(RUNIC_SORCERY_FX);
        return super.getStatusIconIndex();
    }

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Potion> event)
    {
        IForgeRegistry<Potion> reg = event.getRegistry();
        for (Potion potion:recorder) reg.register(potion);
    }
}
