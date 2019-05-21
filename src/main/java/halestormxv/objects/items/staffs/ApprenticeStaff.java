package halestormxv.objects.items.staffs;

import halestormxv.abilities.*;
import halestormxv.objects.items.ItemBaseStaff;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ApprenticeStaff extends ItemBaseStaff
{
    private List<ItemStack> getResistanceCost = new AbilityCosts().getResistanceReagents();
    private List<ItemStack> getFireResistanceCost = new AbilityCosts().getFireResistanceReagents();

    public ApprenticeStaff(String name) {
        super(name);
        setMaxDamage(20);
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        if (!worldIn.isRemote) {
            NBTTagCompound nbt;
            nbt = new NBTTagCompound();
            nbt.setInteger("SpellSelected", 1);
            for (int i = 0; i < getResistanceCost.size(); i++) {
                nbt.setString("RuneName" + (i), getResistanceCost.get(i).getDisplayName());
                nbt.setInteger("RuneCost" + (i), getResistanceCost.get(i).getCount());
            }
            stack.setTagCompound(nbt);
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote) {
            ItemStack theStaff = playerIn.getHeldItem(handIn);
            NBTTagCompound activeSpell = theStaff.getTagCompound();
            if (activeSpell != null) {
                int spellSelected = activeSpell.getInteger("SpellSelected");
                switch (spellSelected) {
                    case 1:
                        Buff_Resistance.applyResistanceSelf(playerIn, handIn);
                        break;

                    case 2:
                        Buff_Resistance.applyFireResistance(playerIn, handIn);
                        break;

                    default:
                        break;
                }
            } else {
                NBTTagCompound nbt;
                nbt = new NBTTagCompound();
                nbt.setInteger("SpellSelected", 1);
                for (int i = 0; i < getResistanceCost.size(); i++) {
                    nbt.setString("RuneName" + (i), getResistanceCost.get(i).getDisplayName());
                    nbt.setInteger("RuneCost" + (i), getResistanceCost.get(i).getCount());
                }
                theStaff.setTagCompound(nbt);
                return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
        }
        return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("");
        tooltip.add("\u00A76" + "A basic staff used for minor");
        tooltip.add("\u00A76" + "spell casting to wet your feet.");
        tooltip.add("\u00A76" + "Press the " + "\u00A74Cycle Spells");
        tooltip.add("\u00A76" + "key to change spells.");
        tooltip.add("");
        if (stack.getTagCompound() != null) {
            NBTTagCompound nbt = stack.getTagCompound();
            int currentSpell = nbt.getInteger("SpellSelected");
            if (currentSpell == 1) {
                tooltip.add("\u00A73" + "Current Spell: Boost Resistance");
                for (int i = 0; i < getResistanceCost.size(); i++) {
                    tooltip.add("\u00A7dReagents: " + nbt.getString("RuneName" + (i)) + " x" + nbt.getInteger("RuneCost" + (i)));
                }
                //tooltip.add("\u00A73" + "Spell Cost: "+runeName+);
            } else if (currentSpell == 2) {
                tooltip.add("\u00A73" + "Current Spell: Boost Fire Resistance");
                for (int i = 0; i < getFireResistanceCost.size(); i++) {
                    tooltip.add("\u00A7dReagents: " + nbt.getString("RuneName" + (i)) + " x" + nbt.getInteger("RuneCost" + (i)));
                }
            }
            tooltip.add("");
        }
    }
}

//SHIFT + RIGHT CLICK ITEM CODE\\
/*Handled all the spell switching
 * in this section of the code like a good egg.
 */
            /*if(playerIn.isSneaking() && activeSpell != null)
            {
                int currentSpell = activeSpell.getInteger("SpellSelected");
                if (currentSpell == 1)
                {
                    activeSpell.setInteger("SpellSelected", ++currentSpell);
                    theStaff.setTagCompound(activeSpell);
                    PacketChatUtils.sendNoSpam(playerIn, "\u00A7dSet spell to Boost Resistance");
                }
                else if (currentSpell == 2)
                {
                    activeSpell.setInteger("SpellSelected", --currentSpell);
                    theStaff.setTagCompound(activeSpell);
                    PacketChatUtils.sendNoSpam(playerIn, "\u00A7dSet spell to Boost Fire Resistance");
                }

                return  new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
            else if (playerIn.isSneaking() && activeSpell == null)
            {
                NBTTagCompound nbt;
                nbt = new NBTTagCompound();
                nbt.setInteger("SpellSelected", 1);
                theStaff.setTagCompound(nbt);
                return  new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }*/

/*Handled all the good old item stuff
 * in this section of the code like a good egg.
 */
