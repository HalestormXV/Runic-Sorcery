package halestormxv.api;

import halestormxv.network.packets.PacketChatUtils;
import halestormxv.objects.items.staffs.ApprenticeStaff;
import halestormxv.abilities.AbilityCosts;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;

import java.util.List;

public class CycleSpells
{
    public static void cycleSpells(EntityPlayer playerIn, EnumHand handIn)
    {
        List<ItemStack> getResistanceCost = new AbilityCosts().getResistanceReagents();
        List<ItemStack> getFireResistanceCost = new AbilityCosts().getFireResistanceReagents();

        if (!playerIn.world.isRemote) {
            ItemStack theStaff = playerIn.getHeldItem(handIn);
            //Apprentice Staff
            if (theStaff.getItem() instanceof ApprenticeStaff) {
                NBTTagCompound activeSpell = theStaff.getTagCompound();
                if (activeSpell != null) {
                    String runeName = activeSpell.getString("RuneName");
                    int runeCost = activeSpell.getInteger("RuneCost");
                    int currentSpell = activeSpell.getInteger("SpellSelected");
                    switch (currentSpell) {
                        case 1:
                            activeSpell.setInteger("SpellSelected", ++currentSpell);
                            for (int i = 0; i < getResistanceCost.size(); i++)
                            {
                                activeSpell.removeTag("RuneName"+(i));
                                activeSpell.removeTag("RuneCost"+(i));
                                activeSpell.setString("RuneName"+(i), getFireResistanceCost.get(i).getDisplayName());
                                activeSpell.setInteger("RuneCost"+(i), getFireResistanceCost.get(i).getCount());
                            }
                            theStaff.setTagCompound(activeSpell);
                            PacketChatUtils.sendNoSpam(playerIn, "\u00A7dSet spell to Boost Fire Resistance");
                            break;

                        case 2:
                            activeSpell.setInteger("SpellSelected", --currentSpell);
                            for (int i = 0; i < getFireResistanceCost.size(); i++)
                            {
                                activeSpell.removeTag("RuneName"+(i));
                                activeSpell.removeTag("RuneCost"+(i));
                                activeSpell.setString("RuneName"+(i), getResistanceCost.get(i).getDisplayName());
                                activeSpell.setInteger("RuneCost"+(i), getResistanceCost.get(i).getCount());
                            }
                            theStaff.setTagCompound(activeSpell);
                            PacketChatUtils.sendNoSpam(playerIn, "\u00A7dSet spell to Boost Resistance");
                            break;

                        default:
                            break;
                    }
                }
            }
        }
    }
}
