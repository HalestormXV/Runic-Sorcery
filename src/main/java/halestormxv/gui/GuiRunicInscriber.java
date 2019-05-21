package halestormxv.gui;

import halestormxv.inventory.runicinscriber.ContainerRunicInscriber;
import halestormxv.objects.blocks.devices.inscriber.TileEntityRunicInscriber;
import halestormxv.utility.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiRunicInscriber extends GuiContainer
{
    private static final ResourceLocation RUNIC_INSCRIBER_TEXTURE = new ResourceLocation(Reference.MODID + ":textures/gui/container/runic_inscriber.png");
    private final InventoryPlayer player;
    private final TileEntityRunicInscriber tileEntity;

    public GuiRunicInscriber(InventoryPlayer player, TileEntityRunicInscriber tileEntity)
    {
        super(new ContainerRunicInscriber(player, tileEntity));
        this.player = player;
        this.tileEntity = tileEntity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.tileEntity.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 4, 16777215);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 16777215);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(RUNIC_INSCRIBER_TEXTURE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        if(TileEntityRunicInscriber.isBurning(this.tileEntity))
        {
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 42 + 12 - k, 176, 12 - k, 14, k + 1 );
        }

        int l = this.getcookProgressScaled(24);
        int r = this.getcookProgressScaled(24);
        this.drawTexturedModalRect(this.guiLeft + 43, this.guiTop + 18, 176, 14, l + 1, 16);
        this.drawTexturedModalRect(this.guiLeft + 109, this.guiTop + 18, 176, 31, 23 - r, 16);
    }

    private int getBurnLeftScaled(int pixels)
    {
        int i = this.tileEntity.getField(1);
        if(i == 0)
            i = 200;
        return this.tileEntity.getField(0) * pixels / i;
    }

    private int getcookProgressScaled(int pixels)
    {
        int i = this.tileEntity.getField(2);
        int j = this.tileEntity.getField(3);
        return j != 0 && i !=0 ? i * pixels / j : 0;
    }
}