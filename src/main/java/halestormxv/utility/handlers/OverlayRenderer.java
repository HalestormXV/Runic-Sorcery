package halestormxv.utility.handlers;

import halestormxv.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OverlayRenderer
{

    public static OverlayRenderer instance = new OverlayRenderer();

    private float dominion = 0;
    private float dominionInfluence = 0;
    private float playerDominion = 0;

    public void setDominion(float dominion, float dominionInfluence, float playerDominion) {
        this.dominion = dominion;
        this.dominionInfluence = dominionInfluence;
        this.playerDominion = playerDominion;
    }


    @SubscribeEvent
    public void renderGameOverlayEvent(RenderGameOverlayEvent event) {
        if (event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }

        if (Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND).getItem() != ItemInit.DOMINION_GAUNTLET) {
            return;
        }

        GlStateManager.disableLighting();

        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;

        int x = 200;
        int y = 10;
        x = fontRenderer.drawString("Dominion ", x, y, 0xffffffff);
        x = fontRenderer.drawString("" + dominion, x, y, 0xffff0000);
        x = fontRenderer.drawString("  Influence ", x, y, 0xffffffff);
        x = fontRenderer.drawString("" + dominionInfluence, x, y, 0xffff0000);
        y += 10;
        x = 200;
        x = fontRenderer.drawString("Player ", x, y, 0xffffffff);
        x = fontRenderer.drawString("" + (playerDominion), x, y, 0xffff0000);
    }
}
