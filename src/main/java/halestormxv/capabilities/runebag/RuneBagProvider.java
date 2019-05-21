package halestormxv.capabilities.runebag;

import halestormxv.utility.Reference;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class RuneBagProvider implements ICapabilitySerializable<NBTTagCompound>
{
    @CapabilityInject(IRuneBagProvider.class)
    public static final Capability<IRuneBagProvider> RUNEBAG_CAP = null;
    public IRuneBagProvider instance = RUNEBAG_CAP.getDefaultInstance();
    public static final ResourceLocation NAME = new ResourceLocation(Reference.MODID, "rune_bags");
    private final IRuneBagProvider cap = new RuneBagFunctions();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == RUNEBAG_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (capability == RUNEBAG_CAP)
        {
            return RUNEBAG_CAP.cast(cap);
        }

        return null;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        return cap.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        cap.deserializeNBT(nbt);
    }
}