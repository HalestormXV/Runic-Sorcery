package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.init.ItemInit;
import halestormxv.abilities.AbilityCosts;
import halestormxv.abilities.Empower;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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

public class ItemBaseStaff extends Item implements IHasModel
{
    private List<ItemStack> getEmpowerCosts = new AbilityCosts().getEmpowerReagents();
    public ItemBaseStaff(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(RunicSorcery.RUNICSORCERY);
        setMaxStackSize(1);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        RunicSorcery.proxy.registerItemRenderer(this, 0 , "inventory");
    }

    @Override
    public boolean isDamageable() { return false; }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if (!worldIn.isRemote) {
            ItemStack theStaff = playerIn.getHeldItem(handIn);
            NBTTagCompound activeSpell = theStaff.getTagCompound();
            if (activeSpell != null) {
                int spellSelected = activeSpell.getInteger("SpellSelected");
                switch (spellSelected)
                {
                    case 1:
                        Empower.applyEmpower(playerIn, handIn);
                        break;

                    default:
                        break;
                }
            } else {
                NBTTagCompound nbt;
                nbt = new NBTTagCompound();
                nbt.setInteger("SpellSelected", 1);
                for (int i = 0; i < getEmpowerCosts.size(); i++) {
                    nbt.setString("RuneName" + (i), getEmpowerCosts.get(i).getDisplayName());
                    nbt.setInteger("RuneCost" + (i), getEmpowerCosts.get(i).getCount());
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
        tooltip.add("\u00A76" + "The most basic staff.");
        tooltip.add("\u00A76" + "It is only capable of empowering");
        tooltip.add("\u00A76" + "Magician Cores.");
        tooltip.add("");
        if (stack.getTagCompound() != null)
        {
            NBTTagCompound nbt = stack.getTagCompound();
            int currentSpell = nbt.getInteger("SpellSelected");
            if (currentSpell == 1)
            {
                tooltip.add("\u00A73" + "Current Spell: Empower");
                for (int i = 0; i < getEmpowerCosts.size(); i++) {
                    tooltip.add("\u00A7dReagents: " + nbt.getString("RuneName" + (i)) + " x" + nbt.getInteger("RuneCost" + (i)));
                }
            }
        }
    }
}
