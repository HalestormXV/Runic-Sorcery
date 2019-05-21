package halestormxv.utility.handlers;

import halestormxv.objects.blocks.devices.inscriber.TileEntityRunicInscriber;
import halestormxv.utility.Reference;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler
{
    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityRunicInscriber.class, Reference.MODID +"TE_RunicInscriber");
    }
}
