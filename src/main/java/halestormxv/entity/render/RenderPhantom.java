package halestormxv.entity.render;

import halestormxv.entity.EntityPhantom;
import halestormxv.entity.model.ModelPhantom;
import halestormxv.utility.Reference;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderPhantom extends RenderLiving<EntityPhantom>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/mob_phantom.png");

    public RenderPhantom(RenderManager manager)
    {
        super(manager, new ModelPhantom(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityPhantom entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityPhantom entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
