package halestormxv.abilities;

import halestormxv.RunicSorcery;
import halestormxv.api.ReagentControl;
import halestormxv.init.ItemInit;
import halestormxv.network.packets.PacketChatUtils;
import halestormxv.utility.handlers.SoundsHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.List;

public class Empower
{
    public static void applyEmpower(EntityPlayer playerIn, EnumHand handIn)
    {
        IItemHandler playerInventory = playerIn.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        List<ItemStack> getEmpowerReagents = new AbilityCosts().getEmpowerReagents();
        ItemStack theCore = new ItemStack(ItemInit.MAGICIANS_CORE, 1, 0);
        boolean passed = ReagentControl.checkReagentListAndConsume(playerIn, getEmpowerReagents);
        boolean hasCore = playerIn.inventory.hasItemStack(new ItemStack(ItemInit.MAGICIANS_CORE, 1, 0));
        if(passed && hasCore)
        {
            for (int slot = 0; slot < playerInventory.getSlots(); slot++)
            {
                ItemStack stack = playerInventory.getStackInSlot(slot);
                Item theCoreItem = ItemInit.MAGICIANS_CORE;
                boolean hasTheCore = stack.getItem().equals(theCoreItem);
                boolean metaMatches = stack.getMetadata() == theCore.getMetadata();
                if (!stack.isEmpty() && hasTheCore && metaMatches)
                {
                    playerIn.getEntityWorld().spawnParticle(EnumParticleTypes.CRIT_MAGIC, playerIn.posX, playerIn.posY, playerIn.posZ, 1, 1, 1);
                    playerIn.getEntityWorld().playSound(playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.elder_guardian.curse")), SoundCategory.AMBIENT, 1.5F, 8F, false);
                    stack.shrink(1);
                    playerIn.addItemStackToInventory(new ItemStack(ItemInit.MAGICIANS_CORE, 1, 1));
                    heldItem.damageItem(1, playerIn);
                }
            }
        } else {
            DamageSource backFire = RunicSorcery.aetherChaos;
            backFire.setDamageBypassesArmor().isUnblockable();
            playerIn.getEntityWorld().playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsHandler.EFFECT_SPELL_FIZZLE, SoundCategory.MASTER, 1.0F, 1.0F);
            PacketChatUtils.sendNoSpam(playerIn, "\u00A7cYour spell Fizzled out due to a lack of some reagent.");
            playerIn.attackEntityFrom(backFire, 6.0f);
        }
    }
}
