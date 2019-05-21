package halestormxv.utility.handlers;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import halestormxv.KeyBindings;
import halestormxv.capabilities.runecrafting.rcLvl_Provider;
import halestormxv.network.PacketHandler;
import halestormxv.network.packets.CycleSpells;
import halestormxv.network.packets.FetchKnownSpells;
import halestormxv.network.packets.FetchRunecraftLvl;
import halestormxv.network.packets.SendKey_PKT;
import halestormxv.capabilities.runecrafting.IRuneCraftLevel;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class InputHandler
{

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if (KeyBindings.runeBag.isPressed())
        {
            // Someone pressed our tutorialKey. We send a message
            RayTraceResult mouseOver = Minecraft.getMinecraft().objectMouseOver;
            BlockPos blockPos = mouseOver.getBlockPos();
            //PacketHandler.INSTANCE.sendToServer(new SendKey_PKT(blockPos));
            PacketHandler.INSTANCE.sendToServer(new FetchKnownSpells());
        }

        if(KeyBindings.getRuneCraftLevel.isPressed())
        {
            EntityPlayer player = Minecraft.getMinecraft().player;
            IRuneCraftLevel iRuneCraftLevel = player.getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
            PacketHandler.INSTANCE.sendToServer(new FetchRunecraftLvl());
        }

        if(KeyBindings.cycleMagicSpells.isPressed())
        {
            EntityPlayer player = Minecraft.getMinecraft().player;
            PacketHandler.INSTANCE.sendToServer(new CycleSpells(player));
        }
    }
}
