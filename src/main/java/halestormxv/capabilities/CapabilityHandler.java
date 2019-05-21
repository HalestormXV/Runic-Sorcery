package halestormxv.capabilities;

import halestormxv.capabilities.learnedspells.LearnedSpellsMain;
import halestormxv.capabilities.runebag.RuneBagProvider;
import halestormxv.capabilities.runecrafting.rcLvl_Provider;
import halestormxv.capabilities.spellcastlevel.SpellCastLvLProvider;
import halestormxv.utility.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler
{
    private static final ResourceLocation RUNECRAFT_LEVEL = new ResourceLocation(Reference.MODID, "runeCraftLevel");
    private static final ResourceLocation RUNEBAG_CAP = new ResourceLocation(Reference.MODID, "runeBagCapability");
    private static final ResourceLocation SPELL_CAST_LEVEL_CAP = new ResourceLocation(Reference.MODID, "spellCastLevel");
    private static final ResourceLocation LEARNED_SPELLS_CAPABILITY = new ResourceLocation(Reference.MODID, "learnedSpellList");
    private static final ResourceLocation RUNE_BLADE_CAPABILITY = new ResourceLocation(Reference.MODID, "runeBladeCapability");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityPlayer)) return;
        event.addCapability(RUNECRAFT_LEVEL, new rcLvl_Provider());
        event.addCapability(RUNEBAG_CAP, new RuneBagProvider());
        event.addCapability(SPELL_CAST_LEVEL_CAP, new SpellCastLvLProvider());
        event.addCapability(LEARNED_SPELLS_CAPABILITY, new LearnedSpellsMain.LearnedSpellsProvider());
    }
}