package halestormxv.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.Charset;
import java.util.UUID;

public class CycleSpells implements IMessage
{
    private EntityPlayer thePlayer;

    public CycleSpells() { }

    public CycleSpells(EntityPlayer thePlayer)
    {
        this.thePlayer = thePlayer;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        UUID id = UUID.fromString(buf.readCharSequence(buf.readableBytes(), Charset.defaultCharset()).toString());
        try {
            thePlayer = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(id);
        } catch (Throwable e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeCharSequence(thePlayer.getUniqueID().toString(), Charset.defaultCharset());
    }

    public static class Handler implements IMessageHandler<CycleSpells, IMessage>
    {
        @Override
        public IMessage onMessage(CycleSpells message, MessageContext ctx)
        {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(CycleSpells message, MessageContext ctx)
        {
            halestormxv.api.CycleSpells.cycleSpells(message.thePlayer, message.thePlayer.getActiveHand());
        }
    }
}
