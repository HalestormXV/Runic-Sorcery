package halestormxv.network.packets;

import halestormxv.capabilities.learnedspells.ILearnedSpells;
import halestormxv.capabilities.learnedspells.LearnedSpellsMain;
import halestormxv.capabilities.runecrafting.IRuneCraftLevel;
import halestormxv.capabilities.runecrafting.rcLvl_Provider;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Arrays;

public class FetchKnownSpells implements IMessage
{
    @Override
    public void fromBytes(ByteBuf buf) { }

    @Override
    public void toBytes(ByteBuf buf) { }

    public static class Handler implements IMessageHandler<FetchKnownSpells, IMessage> {
        @Override
        public IMessage onMessage(FetchKnownSpells message, MessageContext ctx)
        {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(FetchKnownSpells message, MessageContext ctx)
        {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            NBTTagCompound nbt = playerEntity.getEntityData().getCompoundTag("LearnedSpells");
            ILearnedSpells learnedSpells = playerEntity.getCapability(LearnedSpellsMain.LearnedSpellsProvider.LEARNED_SPELLS_CAPABILITY, null);
            if (!nbt.hasKey("LearnedSpells")) {
                playerEntity.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "Spells Known: " + Arrays.toString(learnedSpells.getSpellList())), true);
            }else{
                playerEntity.sendStatusMessage(new TextComponentString(TextFormatting.DARK_AQUA + "You haven't learned any spells."), false);
            }
        }
    }
}
