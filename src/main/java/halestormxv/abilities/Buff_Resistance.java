package halestormxv.abilities;

import halestormxv.RunicSorcery;
import halestormxv.api.ReagentControl;
import halestormxv.network.packets.PacketChatUtils;
import halestormxv.utility.handlers.SoundsHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;

import java.util.List;

public class Buff_Resistance
{
    //IRuneCraftLevel runeCraftLevel = playerIn.getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
    //Make a Caster Level Perhaps?
    public static void applyResistanceSelf(EntityPlayer playerIn, EnumHand handIn)
    {

        ItemStack heldItem = playerIn.getHeldItem(handIn);
        List<ItemStack> getResistanceCost = new AbilityCosts().getResistanceReagents();
        boolean passed = ReagentControl.checkReagentListAndConsume(playerIn, getResistanceCost);
        if(passed)
        {
            playerIn.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("resistance"), 1800, 1));
            heldItem.damageItem(1, playerIn);
        }else{
            DamageSource backFire = RunicSorcery.aetherChaos;
            backFire.setDamageBypassesArmor().isUnblockable();
            playerIn.getEntityWorld().playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsHandler.EFFECT_SPELL_FIZZLE, SoundCategory.MASTER, 1.0F, 1.0F);
            PacketChatUtils.sendNoSpam(playerIn, "\u00A7cYour spell Fizzled out due to a lack of some reagent.");
            playerIn.attackEntityFrom(backFire, 6.0f);
        }
    }

    public static void applyFireResistance(EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        List<ItemStack> getFireResistanceCost = new AbilityCosts().getFireResistanceReagents();
        boolean passed = ReagentControl.checkReagentListAndConsume(playerIn, getFireResistanceCost);
        if(passed)
        {
            playerIn.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("fire_resistance"), 1800, 1));
            heldItem.damageItem(1, playerIn);
        }else{
            DamageSource backFire = RunicSorcery.aetherChaos;
            backFire.setDamageBypassesArmor().isUnblockable();
            playerIn.getEntityWorld().playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsHandler.EFFECT_SPELL_FIZZLE, SoundCategory.MASTER, 1.0F, 1.0F);
            PacketChatUtils.sendNoSpam(playerIn, "\u00A7cYour spell Fizzled out due to a lack of some reagent.");
            playerIn.attackEntityFrom(backFire, 6.0f);
        }
    }
}
