package halestormxv.network.packets;

import halestormxv.RunicSorcery;
import halestormxv.capabilities.spellcastlevel.SpellCastLvLProvider;
import halestormxv.capabilities.spellcastlevel.ISpellCastLevel;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SyncSpellCastLvl implements IMessage
{
    private int scLvL;

    public SyncSpellCastLvl() { }

    public SyncSpellCastLvl(int scLvL) {
        this.scLvL = scLvL;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.scLvL = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(scLvL);
    }

    public static class Handler implements IMessageHandler<SyncSpellCastLvl, IMessage>
    {
        @Override
        public IMessage onMessage(SyncSpellCastLvl message, MessageContext ctx)
        {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
                EntityPlayer player = RunicSorcery.proxy.getMyPlayer(ctx);
                if (player != null)
                {
                    ISpellCastLevel spellCastLevel = player.getCapability(SpellCastLvLProvider.SPELL_CAST_LEVEL_CAP, null);
                    spellCastLevel.setSpellCastLevel(message.scLvL);
                }
            });
            return null;
        }
    }
}
