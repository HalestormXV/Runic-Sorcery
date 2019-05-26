package halestormxv.capabilities.dominion;

import net.minecraft.nbt.NBTTagCompound;

public class PlayerDominion
{
    private float dominion = 0.0f;

    public PlayerDominion() {
    }

    public float getDominion() {
        return dominion;
    }

    public void setDominion(float mana) {
        this.dominion = mana;
    }

    public void copyFrom(PlayerDominion source) {
        dominion = source.dominion;
    }


    public void saveNBTData(NBTTagCompound compound) {
        compound.setFloat("dominion", dominion);
    }

    public void loadNBTData(NBTTagCompound compound) {
        dominion = compound.getFloat("dominion");
    }

}
