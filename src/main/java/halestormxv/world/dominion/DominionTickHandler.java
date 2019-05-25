package halestormxv.world.dominion;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class DominionTickHandler
{
    public static DominionTickHandler instance = new DominionTickHandler();

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event)
    {
        if (event.phase == TickEvent.Phase.START)
        {
            return;
        }
        World world = event.world;
        WorldDominion.get(world).tick(world);
    }
}
