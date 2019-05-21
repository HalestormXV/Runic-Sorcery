package halestormxv.capabilities.spellcastlevel;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class SpellCastLvLStorage implements Capability.IStorage<ISpellCastLevel>
{
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ISpellCastLevel> capability, ISpellCastLevel instance, EnumFacing side)
    {
        return new NBTTagInt(instance.getSpellCastLevel());
    }

    @Override
    public void readNBT(Capability<ISpellCastLevel> capability, ISpellCastLevel instance, EnumFacing side, NBTBase nbt)
    {
        instance.setSpellCastLevel(((NBTPrimitive) nbt).getInt());
    }
}
