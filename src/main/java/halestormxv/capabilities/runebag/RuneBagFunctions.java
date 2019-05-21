package halestormxv.capabilities.runebag;

import halestormxv.network.PacketHandler;
import halestormxv.network.packets.SyncBagData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.Map;

public class RuneBagFunctions implements IRuneBagProvider
{
    private final Map<EnumDyeColor, IItemHandler> inventories = new EnumMap<>(EnumDyeColor.class);

    @Nonnull
    @Override
    public IItemHandler getBag(@Nonnull EnumDyeColor color)
    {
        if (!inventories.containsKey(color))
        {
            inventories.put(color, new ItemStackHandler(16));
        }

        return inventories.get(color);
    }

    @Override
    public void sync(@Nonnull EnumDyeColor color, @Nonnull EntityPlayerMP player)
    {
        PacketHandler.sendTo(new SyncBagData(writeNBT(color)), player);
    }

    private NBTTagCompound writeNBT(EnumDyeColor color)
    {
        NBTTagCompound ret = new NBTTagCompound();
        EnumDyeColor[] colors = color == null ? EnumDyeColor.values() : new EnumDyeColor[] { color };
        for (EnumDyeColor c : colors)
        {
            if (inventories.containsKey(c))
            {
                NBTBase inv = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage()
                        .writeNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventories.get(c), null);
                ret.setTag(c.getName(), inv);
            }
        }
        return ret;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        return writeNBT(null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        for (EnumDyeColor e : EnumDyeColor.values())
        {
            if (nbt.hasKey(e.getName()))
            {
                IItemHandler inv = new ItemStackHandler(16);
                CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage()
                        .readNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inv, null, nbt.getTag(e.getName()));
                inventories.put(e, inv);
            }
        }
    }
}
