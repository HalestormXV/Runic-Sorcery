package halestormxv.capabilities.spellcastlevel;

import halestormxv.network.PacketHandler;
import halestormxv.network.packets.SyncSpellCastLvl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;

public class SpellCastLvLFunctions implements ISpellCastLevel
{
    //Level Up Formula = nextLevel(level)
    //     return ( 0.03 * (level ^ 3) + 0.6 * (level ^ 2) + 2 * level)

    private int spellCastLevel = 0;
    private int min_spellCastLevel = 0;
    private int max_spellCastLevel = 100;
    private EntityPlayer entityPlayer;

    @Override
    public void gainSpellCastLevel(int value)
    {
        int clampValue = this.spellCastLevel + value;
        this.spellCastLevel = MathHelper.clamp(clampValue, this.min_spellCastLevel, this.max_spellCastLevel);
    }

    @Override
    public void setSpellCastLevel(int value)
    {
        this.spellCastLevel = value;
    }

    @Override
    public int getSpellCastLevel() {
        return this.spellCastLevel;
    }

    @Override
    public void syncSpellCastLevel(EntityPlayer thePlayer)
    {
        if (!thePlayer.world.isRemote)
        {
            PacketHandler.sendTo(new SyncSpellCastLvl(this.spellCastLevel), (EntityPlayerMP) thePlayer);
        }

    }
}
