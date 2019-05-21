package halestormxv.objects.fluids.types;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class LiquidMysterium extends BlockFluidClassic
{
    public LiquidMysterium(Fluid fluid, Material material)
    {
        super(fluid, Material.WATER);
        displacements.put(Blocks.PORTAL, true);
        displacements.put(Blocks.END_PORTAL, true);
        displacements.put(Blocks.LAVA, true);
        displacements.put(Blocks.WATER, true);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
        //int i = pos.getX();
        //int j = pos.getY();
        //int k = pos.getZ();
        if (true)
        {
            if (entityIn instanceof EntityLivingBase)
                ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 500, 3));
        }
    }
}
