package halestormxv.utility.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IRenderHandler;

public class RS_SkyRender extends IRenderHandler
{

    private ResourceLocation texture;
    private int r, g, b;

    public RS_SkyRender(ResourceLocation texture, int r, int g, int b)
    {
        this.texture = texture;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc)
    {

        GlStateManager.disableFog();
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.depthMask(false);
        mc.renderEngine.bindTexture(texture);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();

        for (int i = 0; i < 6; ++i)
        {
            GlStateManager.pushMatrix();

            if (i == 1)
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);

            if (i == 2)
                GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);

            if (i == 3)
                GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);

            if (i == 4)
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);

            if (i == 5)
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);

            vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            vertexbuffer.pos(-100.0D, -100.0D, -100.0D).tex(0.0D, 0.0D).color(r, g, b, 255).endVertex();
            vertexbuffer.pos(-100.0D, -100.0D, 100.0D).tex(0.0D, 16.0D).color(r, g, b, 255).endVertex();
            vertexbuffer.pos(100.0D, -100.0D, 100.0D).tex(16.0D, 16.0D).color(r, g, b, 255).endVertex();
            vertexbuffer.pos(100.0D, -100.0D, -100.0D).tex(16.0D, 0.0D).color(r, g, b, 255).endVertex();
            tessellator.draw();
            GlStateManager.popMatrix();
        }

        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
    }
}

