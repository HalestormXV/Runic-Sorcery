package halestormxv.abilities;

import halestormxv.init.ItemInit;
import halestormxv.potion.ModPotionHelper;
import halestormxv.potion.PotionReference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static halestormxv.utility.handlers.EnumHandlerRunes.EnumTypeRunes.*;

public class RuneBlade_Abilities
{
    @SubscribeEvent
    public void strikeFoe(LivingHurtEvent event)
    {
        if (event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            if (player.getHeldItemMainhand() != ItemStack.EMPTY) {
                if (player.getHeldItemMainhand().getItem() == ItemInit.RUNE_BLADE_BASIC)
                {
                    ItemStack theBlade = player.getHeldItemMainhand();
                    double d = Math.random();
                    if (d < 0.7)
                    {
                        List<ItemStack> getReagents = new RuneBlade_AbilityCosts().getBlastStrikeI();
                        /**THIS IS GOING TO BE THE LIST of LISTS CODE SO THAT WE DONT NEED TO CREATE A CHECK FOR EVERY COMBINATION
                        List<List<ItemStack>> masterList = Stream
                            .of(
                                    new RuneBlade_AbilityCosts().getBlastStrikeI(),
                                    new RuneBlade_AbilityCosts().getBlastStrikeII(),
                                    new RuneBlade_AbilityCosts().getWeaknessStrikeI(),
                                    new RuneBlade_AbilityCosts().getWeaknessStrikeII()
                            ).collect(Collectors.toList());
                        List<ItemStack> flat = masterList.stream().flatMap(List::stream).collect(Collectors.toList());
                         **/
                        if (checkRunebladeReagents(theBlade, getReagents))
                        {
                            EntityLiving target = (EntityLiving) event.getEntity();
                            target.addPotionEffect(new PotionEffect(PotionReference.INSTANCE.FUSE, 100, 1));

                        //for (int index = 0; index < flat.size(); index++)
                        // {
                            // WORK ON LIST OF LISTS CODE HERE
                        // }
                        }
                    }
                }
            }
        }
    }

    private static boolean checkRunebladeReagents(ItemStack theBlade, List<ItemStack> reagentsRequired)
    {
        int counter = reagentsRequired.size();
        for (int index = reagentsRequired.size() - 1; index >= 0; index--) {
            IItemHandler rbInventory = theBlade.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            for (int bladeSlots = 0; bladeSlots < rbInventory.getSlots(); bladeSlots++) {
                int total;
                ItemStack stackInBagSlot = rbInventory.getStackInSlot(bladeSlots);
                if (!stackInBagSlot.isEmpty() && stackInBagSlot.getItem().equals(reagentsRequired.get(index).getItem()) && stackInBagSlot.getMetadata() == reagentsRequired.get(index).getMetadata()) {
                    total = stackInBagSlot.getCount();
                    if (total >= reagentsRequired.get(index).getCount()) {
                        rbInventory.getStackInSlot(bladeSlots).shrink(reagentsRequired.get(index).getCount());
                        --counter;
                    }
                }
            }
            reagentsRequired.remove(index);
        }
        return counter == 0;
    }

    private static class RuneBlade_AbilityCosts
    {
        private List<ItemStack> weaknessStrikeI = Stream
                .of(
                        new ItemStack(ItemInit.ITEM_RUNE, 2, AIR_RUNE.getMeta()),
                        new ItemStack(ItemInit.ITEM_RUNE, 1, EARTH_RUNE.getMeta())
                ).collect(Collectors.toList());

        private List<ItemStack> weaknessStrikeII = Stream
                .of(
                        new ItemStack(ItemInit.ITEM_RUNE, 2, AIR_RUNE.getMeta()),
                        new ItemStack(ItemInit.ITEM_RUNE, 2, EARTH_RUNE.getMeta()),
                        new ItemStack(ItemInit.ITEM_RUNE, 1, WATER_RUNE.getMeta())
                ).collect(Collectors.toList());

        private List<ItemStack> blastStrikeI = Stream
                .of(
                        new ItemStack(ItemInit.ITEM_RUNE, 2, FIRE_RUNE.getMeta()),
                        new ItemStack(ItemInit.ITEM_RUNE, 1, EARTH_RUNE.getMeta()),
                        new ItemStack(ItemInit.ITEM_RUNE, 1, AIR_RUNE.getMeta())
                ).collect(Collectors.toList());

        private List<ItemStack> blastStrikeII = Stream
                .of(
                        new ItemStack(ItemInit.ITEM_RUNE, 3, FIRE_RUNE.getMeta()),
                        new ItemStack(ItemInit.ITEM_RUNE, 2, AIR_RUNE.getMeta()),
                        new ItemStack(ItemInit.ITEM_RUNE, 1, EARTH_RUNE.getMeta())
                ).collect(Collectors.toList());

        private List<ItemStack> getWeaknessStrikeI() { return weaknessStrikeI; }
        private List<ItemStack> getWeaknessStrikeII() { return weaknessStrikeII; }
        private List<ItemStack> getBlastStrikeI() { return blastStrikeI; }
        private List<ItemStack> getBlastStrikeII() { return blastStrikeII; }
    }
}
