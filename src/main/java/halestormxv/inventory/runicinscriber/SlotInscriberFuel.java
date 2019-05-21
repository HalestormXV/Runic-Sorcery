package halestormxv.inventory.runicinscriber;

import halestormxv.objects.blocks.devices.inscriber.TileEntityRunicInscriber;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotInscriberFuel extends Slot
{

    public SlotInscriberFuel(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {

        return TileEntityRunicInscriber.isItemFuel(itemStack);
    }

    @Override
    public int getItemStackLimit(ItemStack stack)
    {
        return super.getItemStackLimit(stack);
    }
}
