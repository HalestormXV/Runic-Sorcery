package halestormxv.capabilities.runebag;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public interface IRuneBagProvider extends INBTSerializable<NBTTagCompound>
{

    /**
     * Note: modifying this clientside is not advised
     * @param color The bag color to acquire
     * @return The inventory representing this alchemical bag
     */
    @Nonnull
    IItemHandler getBag(@Nonnull EnumDyeColor color);

    /**
     * Syncs the bag inventory associated with this color to the player provided (usually the owner of this capability instance)
     * @param color The bag color to sync. If null, syncs every color.
     * @param player The player to sync the bags to.
     */
    void sync(EnumDyeColor color, @Nonnull EntityPlayerMP player);
}
