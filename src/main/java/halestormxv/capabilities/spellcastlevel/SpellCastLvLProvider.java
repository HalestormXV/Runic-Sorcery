package halestormxv.capabilities.spellcastlevel;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SpellCastLvLProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(ISpellCastLevel.class)
    public static final Capability<ISpellCastLevel> SPELL_CAST_LEVEL_CAP = null;
    private ISpellCastLevel instance = SPELL_CAST_LEVEL_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == SPELL_CAST_LEVEL_CAP;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        return capability == SPELL_CAST_LEVEL_CAP ? SPELL_CAST_LEVEL_CAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return SPELL_CAST_LEVEL_CAP.getStorage().writeNBT(SPELL_CAST_LEVEL_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        SPELL_CAST_LEVEL_CAP.getStorage().readNBT(SPELL_CAST_LEVEL_CAP, this.instance, null, nbt);
    }
}
