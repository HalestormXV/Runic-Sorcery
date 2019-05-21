package halestormxv.network.packets;

import halestormxv.RunicSorcery;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DispelSuccess implements IMessage
{

    public DispelSuccess() {
    }


    @Override
    public void fromBytes(ByteBuf buf) { }

    @Override
    public void toBytes(ByteBuf buf) { }

    public static class Handler implements IMessageHandler<DispelSuccess, IMessage> {

        @Override
        public IMessage onMessage(DispelSuccess message, MessageContext ctx) {
            RunicSorcery.proxy.playDispelSound();
            return null;
        }

    }
}
