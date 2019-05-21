package halestormxv.entity.render;

import halestormxv.entity.EntityCultist;
import halestormxv.entity.model.ModelCultist;
import halestormxv.utility.Reference;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderCultist extends RenderBiped<EntityCultist>
{
    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/mob_cultist.png");

    public RenderCultist(RenderManager manager)
    {
        super(manager, new ModelCultist(), 0.5F);

        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelCultist();
                this.modelArmor = new ModelCultist();
            }
        });
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityCultist entity)
    {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityCultist entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
