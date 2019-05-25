package halestormxv.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSendDominion implements IMessage
{
    private float dominion;
    private float influence;
    private float playerMana;

    @Override
    public void fromBytes(ByteBuf buf) {
        dominion = buf.readFloat();
        influence = buf.readFloat();
        playerMana = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(dominion);
        buf.writeFloat(influence);
        buf.writeFloat(playerMana);
    }

    // You need this constructor!
    public PacketSendDominion() {
    }

    public PacketSendDominion(float mana, float influence, float playerMana) {
        this.dominion = mana;
        this.influence = influence;
        this.playerMana = playerMana;
    }

    public static class Handler implements IMessageHandler<PacketSendDominion, IMessage> {
        @Override
        public IMessage onMessage(PacketSendDominion message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketSendDominion message, MessageContext ctx) {
            OverlayRenderer.instance.setMana(message.dominion, message.influence, message.playerMana);
        }
    }
}

