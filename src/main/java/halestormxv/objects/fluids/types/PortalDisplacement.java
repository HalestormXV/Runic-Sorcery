package halestormxv.objects.fluids.types;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class PortalDisplacement extends BlockFluidClassic
{
    public PortalDisplacement(Fluid fluid, Material material)
    {
        super(fluid, material);
        displacements.put(Blocks.PORTAL, true);
        displacements.put(Blocks.END_PORTAL, true);
    }
}
