package halestormxv.capabilities.runecrafting;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class rcLvl_Storage implements Capability.IStorage<IRuneCraftLevel>
{

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IRuneCraftLevel> capability, IRuneCraftLevel instance, EnumFacing side)
    {
        return new NBTTagInt(instance.getRuneLevel());
    }

    @Override
    public void readNBT(Capability<IRuneCraftLevel> capability, IRuneCraftLevel instance, EnumFacing side, NBTBase nbt)
    {
        instance.setRuneLevel(((NBTPrimitive) nbt).getInt());
    }
}
