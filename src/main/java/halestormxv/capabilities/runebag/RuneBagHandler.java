package halestormxv.capabilities.runebag;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RuneBagHandler implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IRuneBagProvider.class)
    public static final Capability<IRuneBagProvider> RUNE_BAG = null;

    private IRuneBagProvider instance = RUNE_BAG.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == RUNE_BAG;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == RUNE_BAG ? RUNE_BAG.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return RUNE_BAG.getStorage().writeNBT(RUNE_BAG, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        RUNE_BAG.getStorage().readNBT(RUNE_BAG, this.instance, null, nbt);
    }
}
