package halestormxv.capabilities.runecrafting;

import halestormxv.network.PacketHandler;
import halestormxv.network.packets.SyncRCLvl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;

public class rcLvl_Functions implements IRuneCraftLevel
{
    //Level Up Formula = nextLevel(level)
    //     return ( 0.04 * (level ^ 3) + 0.8 * (level ^ 2) + 2 * level)

    private int runeLevel = 0;
    private int currentLevel = 0;
    private int min_runeLevel = 0;
    private int max_runeLevel = 100;
    private EntityPlayer entityPlayer;


    @Override
    public void gainRuneLevel(int value)
    {
        int clampValue = this.runeLevel + value;
        this.runeLevel = MathHelper.clamp(clampValue, this.min_runeLevel, this.max_runeLevel);
    }

    public void runeCraftLevelCalc(int lvl)
    {
        double points = 0;
        double output = 0;
        int minlevel = 1; // first level to display
        int maxlevel = 100; // last level to display
        this.currentLevel = lvl;
        for (lvl = this.currentLevel; lvl <= maxlevel; lvl++)
        {
            points += Math.floor(lvl + 175 * Math.pow(2, lvl / 7.));
            if (lvl >= minlevel)
                output = Math.floor(points / 4);
            System.out.println("Level " + (lvl) + " - " + output + " xp");
        }
    }

    @Override
    public void setRuneLevel(int value)
    {
        this.runeLevel = value;
    }

    @Override
    public int getRuneLevel() { return this.runeLevel; }

    @Override
    public void syncToClient(EntityPlayer thePlayer)
    {
        if (!thePlayer.world.isRemote)
        {
            PacketHandler.sendTo(new SyncRCLvl(this.runeLevel), (EntityPlayerMP) thePlayer);
        }
    }
}
