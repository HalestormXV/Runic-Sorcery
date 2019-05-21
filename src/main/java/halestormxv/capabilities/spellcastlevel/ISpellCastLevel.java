package halestormxv.capabilities.spellcastlevel;

import net.minecraft.entity.player.EntityPlayer;

public interface ISpellCastLevel
{
    public void gainSpellCastLevel(int runeLevel);

    public void setSpellCastLevel(int runeLevel);

    public int getSpellCastLevel();

    public void syncSpellCastLevel(EntityPlayer entityPlayer);
}
