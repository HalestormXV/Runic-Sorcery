package halestormxv.entity.render;

import halestormxv.entity.EntityHarbinger;
import halestormxv.entity.model.ModelHarbinger;
import halestormxv.utility.Reference;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderHarbinger extends RenderBiped<EntityHarbinger>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/mob_harbinger.png");

    public RenderHarbinger(RenderManager manager)
    {
        super(manager, new ModelHarbinger(), 0.5F);

        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelHarbinger();
                this.modelArmor = new ModelHarbinger();
            }
        });
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityHarbinger entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityHarbinger entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
