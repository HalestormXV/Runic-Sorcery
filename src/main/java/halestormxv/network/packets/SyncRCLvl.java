package halestormxv.network.packets;

import halestormxv.RunicSorcery;
import halestormxv.capabilities.runecrafting.rcLvl_Provider;
import halestormxv.capabilities.runecrafting.IRuneCraftLevel;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SyncRCLvl implements IMessage {
    private int rcLvL;

    public SyncRCLvl() {
    }

    public SyncRCLvl(int rcLvL) {
        this.rcLvL = rcLvL;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.rcLvL = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(rcLvL);
    }

    public static class Handler implements IMessageHandler<SyncRCLvl, IMessage>
    {
        @Override
        public IMessage onMessage(SyncRCLvl message, MessageContext ctx)
        {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
                EntityPlayer player = RunicSorcery.proxy.getMyPlayer(ctx);
                if (player != null)
                {
                    IRuneCraftLevel runeCraftLevel = player.getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
                    runeCraftLevel.setRuneLevel(message.rcLvL);
                }
                //System.out.println("The Rune Craft Level has been synced to: " + message.rcLvL);
            });
            return null;
        }
    }
}
