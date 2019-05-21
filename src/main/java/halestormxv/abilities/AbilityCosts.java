package halestormxv.abilities;

import static halestormxv.utility.handlers.EnumHandlerRunes.EnumTypeRunes.*;

import halestormxv.init.ItemInit;
import net.minecraft.item.ItemStack;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AbilityCosts
{
    private List<ItemStack> resistanceReagents = Stream
            .of(
                    new ItemStack(ItemInit.ITEM_RUNE, 3, EARTH_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, BARRIER_RUNE.getMeta())

            ).collect(Collectors.toList());


    private List<ItemStack> fireResistanceReagents = Stream
            .of(
                    new ItemStack(ItemInit.ITEM_RUNE, 3, FIRE_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, BARRIER_RUNE.getMeta())

            ).collect(Collectors.toList());

    private List<ItemStack> empowerReagents = Stream
            .of(
                    new ItemStack(ItemInit.ITEM_RUNE, 1, FIRE_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, AIR_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, WATER_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, EARTH_RUNE.getMeta())

            ).collect(Collectors.toList());


    public List<ItemStack> getResistanceReagents()
    {
        return resistanceReagents;
    }

    public List<ItemStack> getFireResistanceReagents() {
        return fireResistanceReagents;
    }

    public List<ItemStack> getEmpowerReagents() {
        return empowerReagents;
    }
}
