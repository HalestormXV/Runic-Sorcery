package halestormxv.network.packets;

import halestormxv.capabilities.runecrafting.rcLvl_Provider;
import halestormxv.capabilities.runecrafting.IRuneCraftLevel;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class FetchRunecraftLvl implements IMessage
{
    @Override
    public void fromBytes(ByteBuf buf) { }

    @Override
    public void toBytes(ByteBuf buf) { }

    public static class Handler implements IMessageHandler<FetchRunecraftLvl, IMessage> {
        @Override
        public IMessage onMessage(FetchRunecraftLvl message, MessageContext ctx)
        {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(FetchRunecraftLvl message, MessageContext ctx)
        {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            IRuneCraftLevel runeCraftLevel = playerEntity.getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
            playerEntity.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "Rune Craft Level: " + runeCraftLevel.getRuneLevel()), false);
        }
    }
}
