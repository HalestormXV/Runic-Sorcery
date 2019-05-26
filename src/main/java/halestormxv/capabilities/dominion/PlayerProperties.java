package halestormxv.capabilities.dominion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PlayerProperties
{

    @CapabilityInject(PlayerDominion.class)
    public static Capability<PlayerDominion> PLAYER_DOMINION;

    public static PlayerDominion getPlayerDominion(EntityPlayer player) {
        return player.getCapability(PLAYER_DOMINION, null);
    }

}
