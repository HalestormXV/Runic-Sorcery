package halestormxv.world.dimensions.providers;

import halestormxv.init.BiomeInit;
import halestormxv.utility.Reference;
import halestormxv.utility.misc.RS_SkyRender;
import halestormxv.world.dimensions.ModDimensions;
import halestormxv.world.dimensions.gen.MysteriumChunkGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ModWorldProviders extends WorldProvider
{
    private int mystID = ModDimensions.mysteriumDimensionID;

    @Override
    public DimensionType getDimensionType() { return ModDimensions.mysteriumDimension; }

    @Override
    public String getSaveFolder()
    {
        int dimensionSelection = ModDimensions.mysteriumDimension.getId();
            if (dimensionSelection == mystID) { return "DIM_Mysterium"; }

            return "TEST";
    }

    @Override
    public IChunkGenerator createChunkGenerator() { return new MysteriumChunkGenerator(world); }

    @Override
    public float calculateCelestialAngle(long par1, float par3) {
        int dimensionSelection = ModDimensions.mysteriumDimension.getId();
        if (dimensionSelection == mystID)
        {
            return 0.75F;
        }
        return 0;
    }

    @Override
    protected void init()
    {
        int dimensionSelection = ModDimensions.mysteriumDimension.getId();

        if (dimensionSelection == mystID)
            biomeProvider = new BiomeProviderSingle(BiomeInit.MYSTIC_LANDS);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float par1, float par2)
    {
        int i = 10518688;
        float f2 = MathHelper.cos(par1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F)
            f2 = 0.0F;

        if (f2 > 1.0F)
            f2 = 1.0F;

        float f3 = (i >> 16 & 255) / 255.0F;
        float f4 = (i >> 8 & 255) / 255.0F;
        float f5 = (i & 255) / 255.0F;
        f3 *= f2 * 0.0F + 0.15F;
        f4 *= f2 * 0.0F + 0.15F;
        f5 *= f2 * 0.0F + 0.15F;
        return new Vec3d(f3, f4, f5);
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk)
    {
        return false;
    }

    @Override
    public boolean canRespawnHere()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int par1, int par2) { return true; }

    @Override
    public float getStarBrightness(float par1) { return 1.3f; }

    @Override
    public boolean isSkyColored() { return true; }


    /*@SideOnly(Side.CLIENT)
    @Nullable
    @Override
    public IRenderHandler getSkyRenderer()
    {
        return new RS_SkyRender(new ResourceLocation(Reference.MODID,"textures/environment/mysterium_sky.png"), 221, 153, 255);
    }*/

}
