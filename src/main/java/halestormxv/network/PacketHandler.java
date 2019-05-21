package halestormxv.network;

import halestormxv.network.packets.*;
import halestormxv.utility.Reference;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler
{
    private static int packetId = 0;
    public static final ThreadedNetworkWrapper INSTANCE = new ThreadedNetworkWrapper(Reference.NETWORK_CHANNEL);

    public PacketHandler() {
    }

    private static int nextID() {
        return packetId++;
    }

    public static void setupMessages()
    {
        registerMessages();
    }

    private static void registerMessages()
    {
        //Server Packets
        INSTANCE.registerMessage(SendKey_PKT.Handler.class, SendKey_PKT.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(FetchRunecraftLvl.Handler.class, FetchRunecraftLvl.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(CycleSpells.Handler.class, CycleSpells.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(FetchKnownSpells.Handler.class, FetchKnownSpells.class, nextID(), Side.SERVER);

        //Client Packets
        INSTANCE.registerMessage(SyncRCLvl.Handler.class, SyncRCLvl.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(SyncBagData.Handler.class, SyncBagData.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(DispelSuccess.Handler.class, DispelSuccess.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(SyncSpellCastLvl.Handler.class, SyncSpellCastLvl.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(SyncLearnedSpellsData.Handler.class, SyncLearnedSpellsData.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(PacketChatUtils.PacketNoSpamChat.Handler.class, PacketChatUtils.PacketNoSpamChat.class, nextID(), Side.CLIENT);
    }

    public static void sendToAllAround(IMessage message, TileEntity te, int range)
    {
        BlockPos p = te.getPos();
        INSTANCE.sendToAllAround(message, new NetworkRegistry.TargetPoint(te.getWorld().provider.getDimension(), p.getX(), p.getY(), p.getZ(), range));
    }

    public static void sendToAll(IMessage message)
    {
        INSTANCE.sendToAll(message);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player) { INSTANCE.sendTo(message, player); }

    //Only use if you plan on sending the pack to the server. Has nothing to do with the player. There is only one server.
    public static void sendToServer(IMessage message)
    {
        INSTANCE.sendToServer(message);
    }
}
