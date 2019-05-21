package halestormxv.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CooldownHandler
{
    public static int getCooldownReal(int theCooldown, long storedWorldTime, long currentWorldTime)
    {
        long TimeLeftInTicks = theCooldown - (currentWorldTime - storedWorldTime);
        long TimeLeftInMinutes = TimeLeftInTicks / (20 * 60);
        if (TimeLeftInTicks > 0)
        {
            return (int) TimeLeftInMinutes + 1;
        }
        else
        {
            TimeLeftInMinutes = 0;
            return (int) TimeLeftInMinutes;
        }
    }

    public static void setNewWorldTime(ItemStack stack, EntityPlayer player)
    {
        if (stack.getTagCompound() != null)
        {
            stack.getTagCompound().removeTag("totalWorldTime");
            stack.getTagCompound().setLong("totalWorldTime", player.world.getTotalWorldTime());
        }
    }

    public static long getStoredWorldTime(ItemStack stack)
    {
        if ( (stack.getTagCompound() != null) && (stack.getTagCompound().hasKey("totalWorldTime")) )
        {
            return stack.getTagCompound().getLong("totalWorldTime");
        }
        return 0;
    }
}
