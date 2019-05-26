package halestormxv.capabilities.dominion;
import halestormxv.utility.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerPropertyEvents
{

    public static PlayerPropertyEvents instance = new PlayerPropertyEvents();

    @SubscribeEvent
    public void onEntityConstructing(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof EntityPlayer) {
            if (!event.getObject().hasCapability(PlayerProperties.PLAYER_DOMINION, null))
            {
                event.addCapability(new ResourceLocation(Reference.MODID, "Dominion"), new PropertiesDispatcher());
            }
        }
    }

    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            // We need to copyFrom the capabilities
            if (event.getOriginal().hasCapability(PlayerProperties.PLAYER_DOMINION, null)) {
                PlayerDominion oldStore = event.getOriginal().getCapability(PlayerProperties.PLAYER_DOMINION, null);
                PlayerDominion newStore = PlayerProperties.getPlayerDominion(event.getEntityPlayer());
                newStore.copyFrom(oldStore);
            }
        }
    }
}
