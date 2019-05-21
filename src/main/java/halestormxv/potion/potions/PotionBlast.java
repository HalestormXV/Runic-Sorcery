package halestormxv.potion.potions;

import halestormxv.potion.PotionBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class PotionBlast extends PotionBase
{

    public PotionBlast(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn, "fuse");
        this.setIconIndex(1, 1);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int amplifier)
    {
        if (entityLivingBase instanceof EntityPlayer && ((EntityPlayer)entityLivingBase).isSpectator()) return;
        if (!entityLivingBase.getEntityWorld().isRemote) {
            boolean damageEnvironment = entityLivingBase.getEntityWorld().getGameRules().getBoolean("mobGriefing");
            float f = 1.2f+((float)amplifier)*0.6F;
            entityLivingBase.getEntityWorld().createExplosion(null, entityLivingBase.posX, entityLivingBase.posY+1, entityLivingBase.posZ, f, damageEnvironment);
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return duration == 1;
    }
}
