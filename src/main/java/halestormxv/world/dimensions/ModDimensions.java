package halestormxv.world.dimensions;

import halestormxv.world.dimensions.providers.ModWorldProviders;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions
{
    public static DimensionType mysteriumDimension;
    public static int mysteriumDimensionID = 12;

    public static void init()
    {
        registerDimensionTypes();
        registerDimensions();
    }

    private static void registerDimensionTypes()
    {
        mysteriumDimension = DimensionType.register("the_mysterium", "_my", mysteriumDimensionID, ModWorldProviders.class, false);
    }

    private static void registerDimensions()
    {
        DimensionManager.registerDimension(mysteriumDimensionID, mysteriumDimension);
    }
}
