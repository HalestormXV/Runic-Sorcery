package halestormxv.capabilities.learnedspells;

import com.google.common.primitives.Ints;
import halestormxv.network.PacketHandler;
import halestormxv.network.packets.PacketChatUtils;
import halestormxv.network.packets.SyncLearnedSpellsData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.*;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LearnedSpellsMain
{
    /***********************************
     * This class is the Functions Class
     * The actions take place in this
     * code segment.
     **********************************/
    public static class LearnedSpellsFunctions implements ILearnedSpells
    {
        private List<Integer> knownSpells = new ArrayList<>();
        private EntityPlayer thePlayer;

        @Override
        public EntityPlayer getMyPlayer(EntityPlayer player)
        {
            return this.thePlayer = player;
        }

        @Override
        public void learnedSpell(int spellLearned)
        {
            if(alreadyLearned(spellLearned))
            {
                this.knownSpells.add(spellLearned);
                PacketChatUtils.sendNoSpamClient("\u00A76You have learned the spell: "+spellLearned);
            }else{
                PacketChatUtils.sendNoSpamClient( "\u00A7cYou have already learned this ability.");
            }
        }

        @Override
        public boolean alreadyLearned(int spellID)
        {
            return !this.knownSpells.contains(spellID);
        }

        @Override
        public int[] getSpellList() {
            return Ints.toArray(this.knownSpells);
        }

        @Override
        public void setSpellList(int[] spellList)
        {
            int[] arr = new int[10];
            int count = 0;
            for (Integer i : this.knownSpells)
            {
                int i1 = i;
                if (arr.length == count) arr = Arrays.copyOf(arr, count * 2);
                arr[count++] = i1;
            }
            arr = Arrays.copyOfRange(arr, 0, count);
            arr = spellList;
        }

        @Override
        public void syncToClient(NBTTagCompound nbt, EntityPlayer entityPlayer)
        {
            if (!getMyPlayer(entityPlayer).world.isRemote) { PacketHandler.sendTo(new SyncLearnedSpellsData(nbt), (EntityPlayerMP) getMyPlayer(entityPlayer)); }
        }
    }

    /***********************************
     * This class is the Storage Class
     * It gives us the Capability Storage
     * like a good little egg.
     **********************************/
    public static class LearnedSpellsStorage implements Capability.IStorage<ILearnedSpells>
    {
        public NBTBase writeNBT(Capability<ILearnedSpells> capability, ILearnedSpells instance, EnumFacing side)
        {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setIntArray("LearnedSpells", instance.getSpellList());
            return nbt;
        }

        @Override
        public void readNBT(Capability<ILearnedSpells> capability, ILearnedSpells instance, EnumFacing side, NBTBase base)
        {
            NBTTagCompound nbt = (NBTTagCompound) base;
            int[] intArray = nbt.getIntArray("LearnedSpells");
            for (int anIntArray : intArray) {
                instance.learnedSpell(anIntArray);
            }
            //instance.setSpellList(nbt.getIntArray("LearnedSpells"));
        }
    }

    /***********************************
     * This class is the Provider Class
     * It gives us the Capability and
     * looks for it
     **********************************/
    public static class LearnedSpellsProvider implements ICapabilitySerializable<NBTBase>
    {
        @CapabilityInject(ILearnedSpells.class)
        public static final Capability<ILearnedSpells> LEARNED_SPELLS_CAPABILITY = null;

        private ILearnedSpells instance = LEARNED_SPELLS_CAPABILITY.getDefaultInstance();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == LEARNED_SPELLS_CAPABILITY;
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            return capability == LEARNED_SPELLS_CAPABILITY ? LEARNED_SPELLS_CAPABILITY.<T>cast(this.instance) : null;
        }

        @Override
        public NBTBase serializeNBT() {
            return LEARNED_SPELLS_CAPABILITY.getStorage().writeNBT(LEARNED_SPELLS_CAPABILITY, this.instance, null);
        }

        @Override
        public void deserializeNBT(NBTBase nbt) {
            LEARNED_SPELLS_CAPABILITY.getStorage().readNBT(LEARNED_SPELLS_CAPABILITY, this.instance, null, nbt);
        }
    }
}


