package halestormxv.entity.model;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHarbinger extends ModelBiped
{
    private ModelRenderer archangelhead;
    private ModelRenderer archangelbody;
    private ModelRenderer archangelrightarm;
    private ModelRenderer archangelleftarm;
    private ModelRenderer archangelrightleg;
    private ModelRenderer archangelleftleg;
    private ModelRenderer archangelwing1;
    private ModelRenderer archangelwing2;
    private ModelRenderer archangelwing3;
    private ModelRenderer archangelwing4;
    private ModelRenderer archangelwing5;
    private ModelRenderer archangelwing6;
    private ModelRenderer archangelwing7;
    private ModelRenderer archangelwing8;
    private ModelRenderer archangelorbiter1;
    private ModelRenderer archangelorbiter2;
    private ModelRenderer archangelorbiter3;
    private ModelRenderer archangelorbiter4;
    private ModelRenderer archangelorbiter5;
    private ModelRenderer archangelorbiter6;
    private ModelRenderer archangelorbiter7;
    private ModelRenderer archangelorbiter8;

    public ModelHarbinger()
    {
        this.textureWidth = 64;
        this.textureHeight = 128;
        this.archangelwing5 = new ModelRenderer(this, 42, 54);
        this.archangelwing5.setRotationPoint(0.0F, -10.0F, 6.0F);
        this.archangelwing5.addBox(12.0F, -1.5F, 0.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelwing5, -0.024687695778751656F, -0.024680176152938663F, -2.355889816877285F);
        this.archangelorbiter2 = new ModelRenderer(this, 42, 72);
        this.archangelorbiter2.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.archangelorbiter2.addBox(7.0F, -1.5F, -1.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelorbiter2, 1.7427300861196855F, 0.7703190549759398F, 0.24437999062287266F);
        this.archangelrightarm = new ModelRenderer(this, 40, 16);
        this.archangelrightarm.setRotationPoint(-5.0F, -7.0F, 0.0F);
        this.archangelrightarm.addBox(-3.0F, -1.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(archangelrightarm, 0.0F, -0.0F, 0.2602502703666687F);
        this.archangelleftleg = new ModelRenderer(this, 0, 33);
        this.archangelleftleg.mirror = true;
        this.archangelleftleg.setRotationPoint(2.0F, 4.0F, 0.0F);
        this.archangelleftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.archangelwing7 = new ModelRenderer(this, 42, 62);
        this.archangelwing7.mirror = true;
        this.archangelwing7.setRotationPoint(0.0F, -10.0F, 6.0F);
        this.archangelwing7.addBox(12.0F, -1.5F, 0.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelwing7, 0.024687695778751656F, -0.024680176152938663F, 2.355889816877285F);
        this.archangelorbiter8 = new ModelRenderer(this, 0, 80);
        this.archangelorbiter8.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.archangelorbiter8.addBox(7.0F, -1.5F, -1.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelorbiter8, -1.7427299893185604F, 0.7703190279843499F, 2.897212669497665F);
        this.archangelwing8 = new ModelRenderer(this, 21, 62);
        this.archangelwing8.setRotationPoint(0.0F, -10.0F, 6.0F);
        this.archangelwing8.addBox(12.0F, -1.5F, 0.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelwing8, -0.034906584769487346F, 1.525505499419406E-9F, 1.5707963704796588F);
        this.archangelwing1 = new ModelRenderer(this, 0, 62);
        this.archangelwing1.setRotationPoint(0.0F, -10.0F, 6.0F);
        this.archangelwing1.addBox(12.0F, -1.5F, 0.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelwing1, -0.0246876964652383F, -0.02468017546624276F, 0.7857028645306386F);
        this.archangelwing3 = new ModelRenderer(this, 0, 58);
        this.archangelwing3.setRotationPoint(0.0F, -10.0F, 6.0F);
        this.archangelwing3.addBox(12.0F, -1.5F, 0.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelwing3, 0.0F, -0.03490658476948738F, 0.0F);
        this.archangelbody = new ModelRenderer(this, 16, 16);
        this.archangelbody.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.archangelbody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.archangelwing2 = new ModelRenderer(this, 0, 54);
        this.archangelwing2.setRotationPoint(0.0F, -10.0F, 6.0F);
        this.archangelwing2.addBox(12.0F, -1.5F, 0.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelwing2, 0.0246876964652383F, -0.02468017546624276F, -0.7857028645306386F);
        this.archangelorbiter5 = new ModelRenderer(this, 21, 80);
        this.archangelorbiter5.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.archangelorbiter5.addBox(7.0F, -1.5F, -1.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelorbiter5, -2.0801247976635628E-7F, -1.3962634066727442F, 1.5707965746945156F);
        this.archangelwing6 = new ModelRenderer(this, 42, 58);
        this.archangelwing6.setRotationPoint(0.0F, -10.0F, 6.0F);
        this.archangelwing6.addBox(12.0F, -1.5F, 0.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelwing6, 3.0528707251699673E-9F, -0.03490658476948725F, 3.141592566113726F);
        this.archangelwing4 = new ModelRenderer(this, 21, 54);
        this.archangelwing4.setRotationPoint(0.0F, -10.0F, 6.0F);
        this.archangelwing4.addBox(12.0F, -1.5F, 0.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelwing4, 0.034906584769487346F, 1.525505499419406E-9F, -1.5707963704796588F);
        this.archangelhead = new ModelRenderer(this, 0, 0);
        this.archangelhead.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.archangelhead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(archangelhead, 0.08726646006107329F, -0.0F, 0.0F);
        this.archangelrightleg = new ModelRenderer(this, 0, 16);
        this.archangelrightleg.setRotationPoint(-2.0F, 4.0F, 0.0F);
        this.archangelrightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.archangelorbiter6 = new ModelRenderer(this, 0, 72);
        this.archangelorbiter6.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.archangelorbiter6.addBox(7.0F, -1.5F, -1.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelorbiter6, -1.3988625768484533F, -0.7703190279843499F, 2.897212669497665F);
        this.archangelleftarm = new ModelRenderer(this, 40, 0);
        this.archangelleftarm.mirror = true;
        this.archangelleftarm.setRotationPoint(5.0F, -7.0F, 0.0F);
        this.archangelleftarm.addBox(-1.0F, -1.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(archangelleftarm, 0.0F, -0.0F, -0.2602502703666687F);
        this.archangelorbiter7 = new ModelRenderer(this, 0, 76);
        this.archangelorbiter7.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.archangelorbiter7.addBox(7.0F, -1.5F, -1.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelorbiter7, -1.5707962982643127F, 8.609463161462617E-8F, 2.967059733467646F);
        this.archangelorbiter1 = new ModelRenderer(this, 21, 72);
        this.archangelorbiter1.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.archangelorbiter1.addBox(7.0F, -1.5F, -1.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelorbiter1, -3.1415923581545333F, 1.3962634066727442F, 1.5707965746945156F);
        this.archangelorbiter4 = new ModelRenderer(this, 42, 80);
        this.archangelorbiter4.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.archangelorbiter4.addBox(7.0F, -1.5F, -1.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelorbiter4, 1.398862654892888F, -0.7703190549759398F, 0.24437999062287266F);
        this.archangelorbiter3 = new ModelRenderer(this, 42, 76);
        this.archangelorbiter3.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.archangelorbiter3.addBox(7.0F, -1.5F, -1.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(archangelorbiter3, 1.5707963705062866F, -0.0F, 0.17453292012214658F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5);
        this.archangelwing5.render(f5);
        this.archangelorbiter2.render(f5);
        this.archangelrightarm.render(f5);
        this.archangelleftleg.render(f5);
        this.archangelwing7.render(f5);
        this.archangelorbiter8.render(f5);
        this.archangelwing8.render(f5);
        this.archangelwing1.render(f5);
        this.archangelwing3.render(f5);
        this.archangelbody.render(f5);
        this.archangelwing2.render(f5);
        this.archangelorbiter5.render(f5);
        this.archangelwing6.render(f5);
        this.archangelwing4.render(f5);
        this.archangelhead.render(f5);
        this.archangelrightleg.render(f5);
        this.archangelorbiter6.render(f5);
        this.archangelleftarm.render(f5);
        this.archangelorbiter7.render(f5);
        this.archangelorbiter1.render(f5);
        this.archangelorbiter4.render(f5);
        this.archangelorbiter3.render(f5);
    }

/**
 * This is a helper function from Tabula to set the rotation of model parts
 */
    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        //super.setRotationAngles(f, f1, f2, f3, f4, f5, EntityCultist);
        this.archangelhead.rotateAngleY = f3 / (180F / (float)Math.PI);
        this.archangelhead.rotateAngleX = f4 / (180F / (float)Math.PI);
        this.archangelrightarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
        this.archangelleftarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        this.archangelrightarm.rotateAngleZ = 0.0F;
        this.archangelleftarm.rotateAngleZ = 0.0F;
    }
}