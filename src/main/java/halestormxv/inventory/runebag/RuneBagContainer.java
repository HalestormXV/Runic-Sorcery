package halestormxv.inventory.runebag;

import halestormxv.init.ItemInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class RuneBagContainer extends Container
{
    public final EnumHand hand;
    private final int blocked;

    public RuneBagContainer(InventoryPlayer invPlayer, EnumHand hand, IItemHandlerModifiable invBag)
    {
        /**
         * The X Position First Number is always the pixel of the Top Left Corner Box of your GUI Image
         * The Y Position First Number is always the pixel of the Top Left Corner Box of your GUI Image
         * Use Paint.net to check you pixel locations.
         * 18 is the usual buffer between columns.
         */
        this.hand = hand;

        //Bag Inventory
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                //I just made a static class within this class because all I am doing is locking other items except Runestones out.
                //There is really no need for an entire separate SlotHandler as nothing else is being overridden.
                this.addSlotToContainer(new RuneBagSlotHandler(invBag, j + i * 4, 53 + j * 18, 8 + i * 18));

        //Player Inventory
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 9; j++)
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

        //Player Hotbar
        for (int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));

        blocked = hand == EnumHand.MAIN_HAND ? (inventorySlots.size() - 1) - (8 - invPlayer.currentItem) : -1;
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer player)
    {
        return true;
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        Slot slot = this.getSlot(slotIndex);

        if (slot == null || !slot.getHasStack())
        {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getStack();
        ItemStack newStack = stack.copy();

        /**
         * The slotIndex is how many actual slots your GUI
         * contains. This will enable Shift+Clicking and such.
         * Remember in code we start our count from 0.
         */
        if (slotIndex < 16)
        {
            if (!this.mergeItemStack(stack, 16, this.inventorySlots.size(), true))
                return ItemStack.EMPTY;
            slot.onSlotChanged();
        }
        else if (!this.mergeItemStack(stack, 0, 16, false))
        {
            return ItemStack.EMPTY;
        }
        if (stack.isEmpty())
        {
            slot.putStack(ItemStack.EMPTY);
        }
        else
        {
            slot.onSlotChanged();
        }

        return slot.onTake(player, newStack);
    }

    @Nonnull
    @Override
    public ItemStack slotClick(int slot, int button, ClickType flag, EntityPlayer player)
    {
        if (slot == blocked)
        {
            return ItemStack.EMPTY;
        }

        return super.slotClick(slot, button, flag, player);
    }


    /**
     * I just made this Static Class Within this class because all I am doing
     * is locking all items except Runestones out of the bag. No need for an
     * entire separate class just to achieve this.
     */
    public static class RuneBagSlotHandler extends SlotItemHandler
    {
        private RuneBagSlotHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition)
        {
            super(itemHandler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return (stack.getItem() == ItemInit.ITEM_RUNE);
        }
    }
}
