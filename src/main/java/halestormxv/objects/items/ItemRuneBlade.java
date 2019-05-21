package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.init.ItemInit;
import halestormxv.utility.Reference;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;


public class ItemRuneBlade extends ItemSword implements IHasModel
{
    public ItemRuneBlade(String name, ToolMaterial material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(RunicSorcery.RUNICSORCERY_SPECIAL);
        setMaxStackSize(1);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        String colorName = super.getItemStackDisplayName(stack);
        return "\u00A75" + colorName;
    }

    @Override
    public void registerModels()
    {
        RunicSorcery.proxy.registerItemRenderer(this, 0 , "inventory");
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        ItemStack stackToSaveTo = player.getHeldItem(hand);
        NBTTagCompound nbt = new NBTTagCompound();

        if (!world.isRemote && player.isSneaking()) { player.openGui(RunicSorcery.instance, Reference.GUI_RUNE_BLADE, world, hand.ordinal(), -1, -1); }
        stackToSaveTo.deserializeNBT(nbt);
        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack item, NBTTagCompound nbt)
    {
        if(item.getItem() instanceof ItemRuneBlade) { return new RuneSlotHandler(); }
        return null;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add("");
        tooltip.add("\u00A7a" + "A person’s strength in this world is just an illusion.");
        tooltip.add("\u00A7a" + "I’d rather stay the way I am until the last moment.");
        tooltip.add("\u00A7a" + "The world is beautiful, even when it’s filled");
        tooltip.add("\u00A7a" + "with sadness and tears.");
    }


    /*@Override
    public boolean hasEffect(ItemStack stack)
    {
        IItemHandler rbInventory = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        for (int slot = 0; slot < rbInventory.getSlots(); slot++)
        {
            ItemStack hasItem = rbInventory.getStackInSlot(slot);
            if (!hasItem.isEmpty())
                return true;
        }
        return false;
    }*/

    /**Capability Handler for ItemStack
     * This is the handler to take care of storing
     * the different types of Runes within the Item.
     */
    public static class RuneSlotHandler implements ICapabilityProvider, INBTSerializable<NBTTagCompound>
    {
        private final ItemStackHandler handler;
        private RuneSlotHandler() { handler = new ItemStackHandler( 4 ); }

        @Override
        //Retrieve data here
        public NBTTagCompound serializeNBT()
        {
            NBTTagCompound ret = new NBTTagCompound();
            NBTBase inv = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().writeNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, handler, null);
            ret.setTag("RunesStored", inv);
            return ret;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt)
        {
            //Do your saving here
            if (nbt.hasKey("RunesStored"))
            {
                CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().readNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, handler, null, nbt.getTag("RunesStored"));
            }
        }

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing)
        {
            return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing)
        {
            if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            {
                return (T) handler;
            }
            return null;
        }
    }
}
