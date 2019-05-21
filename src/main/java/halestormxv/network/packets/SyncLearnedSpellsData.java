package halestormxv.network.packets;

import halestormxv.RunicSorcery;
import halestormxv.capabilities.learnedspells.ILearnedSpells;
import halestormxv.capabilities.learnedspells.LearnedSpellsMain;
import halestormxv.capabilities.spellcastlevel.ISpellCastLevel;
import halestormxv.capabilities.spellcastlevel.SpellCastLvLProvider;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SyncLearnedSpellsData implements IMessage
{
    private NBTTagCompound nbt;

    public SyncLearnedSpellsData() {}

    public SyncLearnedSpellsData(NBTTagCompound nbt)
    {
        this.nbt = nbt;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        nbt = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeTag(buf, nbt);
    }

    public static class Handler implements IMessageHandler<SyncLearnedSpellsData, IMessage>
    {
        @Override
        public IMessage onMessage(final SyncLearnedSpellsData message, MessageContext ctx)
        {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
                EntityPlayer player = RunicSorcery.proxy.getMyPlayer(ctx);
                if (player != null)
                {
                    player.deserializeNBT(message.nbt);
                    RunicSorcery.logger.info("** DATA SHOULD HAVE BEEN SYNCED **");
                }
            });
            return null;
        }
    }
}