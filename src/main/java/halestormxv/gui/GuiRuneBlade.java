package halestormxv.gui;

import halestormxv.inventory.runeblade.ContainerRuneBlade;
import halestormxv.utility.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;

public class GuiRuneBlade extends GuiContainer
{
    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID.toLowerCase(), "textures/gui/runeblade_slots.png");

    public GuiRuneBlade(InventoryPlayer invPlayer, EnumHand hand, IItemHandlerModifiable invBag)
    {
        /**
         * When applying the actual size of the GUI you take the Bottom Right Corner of the image.
         * This will allow you to obtain the proper X and Y coordinates for the actual GUI.
         * The image will still always be a 256x256.
         */
        super(new ContainerRuneBlade(invPlayer, hand, invBag));
        this.xSize = 175;
        this.ySize = 164;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GlStateManager.color(1, 1, 1, 1);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        this.drawTexturedModalRect((width - xSize) / 2, (height - ySize) / 2, 0, 0, xSize, ySize);
    }
}

