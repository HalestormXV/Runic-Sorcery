package halestormxv.capabilities.dominion;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class PropertiesDispatcher implements ICapabilitySerializable<NBTTagCompound> {

    private PlayerDominion playerDominion = new PlayerDominion();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == PlayerProperties.PLAYER_DOMINION;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == PlayerProperties.PLAYER_DOMINION) {
            return (T) playerDominion;
        }
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        playerDominion.saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        playerDominion.loadNBTData(nbt);
    }
}
