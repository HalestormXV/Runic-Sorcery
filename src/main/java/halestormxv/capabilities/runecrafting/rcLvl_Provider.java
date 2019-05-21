package halestormxv.capabilities.runecrafting;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class rcLvl_Provider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IRuneCraftLevel.class)
    public static final Capability<IRuneCraftLevel> RUNECRAFT_LEVEL = null;

    private IRuneCraftLevel instance = RUNECRAFT_LEVEL.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == RUNECRAFT_LEVEL;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == RUNECRAFT_LEVEL ? RUNECRAFT_LEVEL.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return RUNECRAFT_LEVEL.getStorage().writeNBT(RUNECRAFT_LEVEL, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        RUNECRAFT_LEVEL.getStorage().readNBT(RUNECRAFT_LEVEL, this.instance, null, nbt);
    }
}
