package halestormxv.capabilities.runecrafting;

import net.minecraft.entity.player.EntityPlayer;

public interface IRuneCraftLevel
{
    public void gainRuneLevel(int runeLevel);

    public void setRuneLevel(int runeLevel);

    public int getRuneLevel();

    public void syncToClient(EntityPlayer entityPlayer);

    public void runeCraftLevelCalc(int lvl);

}
