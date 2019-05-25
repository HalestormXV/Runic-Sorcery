package halestormxv.world.dominion;

import halestormxv.network.PacketHandler;
import halestormxv.utility.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

import java.util.HashMap;
import java.util.Map;

public class WorldDominion extends WorldSavedData
{

    private static final String NAME = Reference.MODID+"_DominionData";
    private Map<ChunkPos, DominionOrb> orbs = new HashMap<>();
    private int ticker = 10;

    public WorldDominion() {super(NAME); }
    public WorldDominion(String name) {super(name); }

    public static WorldDominion get(World world)
    {
        MapStorage storage = world.getPerWorldStorage();
        WorldDominion instance = (WorldDominion) storage.getOrLoadData(WorldDominion.class, NAME);

        if (instance == null)
        {
            instance = new WorldDominion();
            storage.setData(NAME, instance);
        }
        return instance;
    }

    public float getDominionInfluence(World world, BlockPos pos)
    {
        ChunkPos chunkPos = new ChunkPos(pos);
        float dominion = 0.0f;
        for (int dx = -4 ; dx <= 4 ; dx++) {
            for (int dz = -4 ; dz <= 4 ; dz++) {
                ChunkPos cp = new ChunkPos(chunkPos.x + dx, chunkPos.z + dz);
                DominionOrb orb = getOrCreateOrbAt(world, cp);
                if (orb.getRadius() > 0) {
                    double distanceSq = pos.distanceSq(orb.getCenter());
                    if (distanceSq < orb.getRadius() * orb.getRadius()) {
                        double distance = Math.sqrt(distanceSq);
                        dominion += (orb.getRadius() - distance) / orb.getRadius();
                    }
                }
            }
        }
        return dominion;
    }

    public float getDominionStrength(World world, BlockPos pos)
    {
        ChunkPos chunkPos = new ChunkPos(pos);
        float dominion = 0.0f;
        for (int dx = -4 ; dx <= 4 ; dx++) {
            for (int dz = -4 ; dz <= 4 ; dz++) {
                ChunkPos cp = new ChunkPos(chunkPos.x + dx, chunkPos.z + dz);
                DominionOrb orb = getOrCreateOrbAt(world, cp);
                if (orb.getRadius() > 0) {
                    double distanceSq = pos.distanceSq(orb.getCenter());
                    if (distanceSq < orb.getRadius() * orb.getRadius()) {
                        double distance = Math.sqrt(distanceSq);
                        double influence = (orb.getRadius() - distance) / orb.getRadius();
                        dominion += influence * orb.getCurrentDominion();
                    }
                }
            }
        }
        return dominion;
    }

    public float extractDominion(World world, BlockPos pos) {
        float dominionInfluence = getDominionInfluence(world, pos);
        if (dominionInfluence <= 0) {
            return 0;
        }
        ChunkPos chunkPos = new ChunkPos(pos);
        float extracted = 0.0f;
        for (int dx = -4 ; dx <= 4 ; dx++) {
            for (int dz = -4 ; dz <= 4 ; dz++) {
                ChunkPos cp = new ChunkPos(chunkPos.x + dx, chunkPos.z + dz);
                DominionOrb orb = getOrCreateOrbAt(world, cp);
                if (orb.getRadius() > 0) {
                    double distanceSq = pos.distanceSq(orb.getCenter());
                    if (distanceSq < orb.getRadius() * orb.getRadius()) {
                        double distance = Math.sqrt(distanceSq);
                        double influence = (orb.getRadius() - distance) / orb.getRadius();
                        float currentDominion = orb.getCurrentDominion();
                        if (influence > currentDominion) {
                            influence = currentDominion;
                        }
                        currentDominion -= influence;
                        extracted += influence;
                        orb.setCurrentDominion(currentDominion);
                        markDirty();
                    }
                }
            }
        }
        return extracted;
    }

    public void tick(World world) {
        ticker--;
        if (ticker > 0) {
            return;
        }
        ticker = 10;
        growDominion(world);
        sendDominion(world);
    }

    private void growDominion(World world) {
        for (Map.Entry<ChunkPos, DominionOrb> entry : orbs.entrySet()) {
            DominionOrb orb = entry.getValue();
            if (orb.getRadius() > 0) {
                if (world.isBlockLoaded(orb.getCenter())) {
                    float currentDominion = orb.getCurrentDominion();
                    currentDominion += .01f;
                    if (currentDominion >= 5) {
                        currentDominion = 5;
                    }
                    orb.setCurrentDominion(currentDominion);
                    markDirty();
                }
            }
        }
    }

    private void sendDominion(World world) {
        for (EntityPlayer player : world.playerEntities) {
            float dominionStrength = getDominionStrength(world, player.getPosition());
            float maxInfluence = getDominionInfluence(world, player.getPosition());
            PlayerDominion playerDominion = PlayerProperties.getPlayerMana(player);
            PacketHandler.INSTANCE.sendTo(new PacketSendDominion(dominionStrength, maxInfluence, playerDominion.getDominion()), (EntityPlayerMP) player);
        }
    }

    private DominionOrb getOrCreateOrbAt(World world, ChunkPos cp) {
        DominionOrb orb = orbs.get(cp);
        if (orb == null) {
            BlockPos center = cp.getBlock(8, DominionOrb.getRandomYOffset(world.getSeed(), cp.x, cp.z), 8);
            float radius = 0;
            if (DominionOrb.isCenterChunk(world.getSeed(), cp.x, cp.z)) {
                radius = DominionOrb.getRadius(world.getSeed(), cp.x, cp.z);
            }
            orb = new DominionOrb(center, radius);
            orbs.put(cp, orb);
            markDirty();
        }
        return orb;
    }


    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        return null;
    }
}
