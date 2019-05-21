package halestormxv.potion.potions;

import halestormxv.potion.PotionBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class PotionRuneCraftMastery extends PotionBase
{
    public PotionRuneCraftMastery(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn, "runecraftMastery");
        this.setIconIndex(6, 1);
    }

    @Override
    public void performEffect(EntityLivingBase e, int amp)
    {
        if (e instanceof EntityPlayer)
            super.performEffect(e, amp);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
