package halestormxv.objects.blocks.devices.inscriber;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import halestormxv.init.ItemInit;
import net.minecraft.item.ItemStack;

import static halestormxv.objects.items.ItemEssence.EnumHandlerEssences.EnumTypeEssences.*;
import static halestormxv.utility.handlers.EnumHandlerRunes.EnumTypeRunes.*;


public class RunicInscriberRecipes
{
    private List<ItemStack> essenceRequired = Stream
            .of(
                    new ItemStack(ItemInit.RUNE_ESSENCE, 1, BASIC_ESSENCE.getMeta()),
                    new ItemStack(ItemInit.RUNE_ESSENCE, 1, AIR_ESSENCE.getMeta()),
                    new ItemStack(ItemInit.RUNE_ESSENCE, 1, WATER_ESSENCE.getMeta()),
                    new ItemStack(ItemInit.RUNE_ESSENCE, 1, FIRE_ESSENCE.getMeta()),
                    new ItemStack(ItemInit.RUNE_ESSENCE, 1, EARTH_ESSENCE.getMeta()),
                    new ItemStack(ItemInit.RUNE_ESSENCE, 1, DEATH_ESSENCE.getMeta()),
                    new ItemStack(ItemInit.RUNE_ESSENCE, 1, GRAVITY_ESSENCE.getMeta()),
                    new ItemStack(ItemInit.RUNE_ESSENCE, 1, BARRIER_ESSENCE.getMeta()),
                    new ItemStack(ItemInit.RUNE_ESSENCE, 1, LIGHT_ESSENCE.getMeta()),
                    new ItemStack(ItemInit.RUNE_ESSENCE, 1, VOID_ESSENCE.getMeta()),
                    new ItemStack(ItemInit.RUNE_ESSENCE, 1, POISON_ESSENCE.getMeta()),
                    new ItemStack(ItemInit.RUNE_ESSENCE, 1, NIGHTMARE_ESSENCE.getMeta())
            ).collect(Collectors.toList());

    private List<ItemStack> runeStoneResults = Stream
            .of(
                    new ItemStack(ItemInit.ITEM_RUNE, 1, BASIC_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, AIR_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, WATER_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, FIRE_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, EARTH_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, DEATH_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, GRAVITY_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, BARRIER_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, LIGHT_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, VOID_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, POISON_RUNE.getMeta()),
                    new ItemStack(ItemInit.ITEM_RUNE, 1, NIGHTMARE_RUNE.getMeta())
            ).collect(Collectors.toList());

    private List<Float> runeStoneEXP = Stream
            .of(
                    1.4f,   //Basic Rune
                    3.4f,   //Air Rune
                    3.4f,   //Water Rune
                    3.4f,   //Fire Rune
                    3.4f,   //Earth Rune
                    6.2f,   //Death Rune
                    6.2f,   //Gravity Rune
                    8.2f,   //Barrier Rune
                    8.2f,   //Light Rune
                    10.2f,  //Void Rune
                    10.2f,  //Poison Rune
                    14.6f   //Nightmare Rune
            ).collect(Collectors.toList());

    private static final RunicInscriberRecipes INSTANCE = new RunicInscriberRecipes();
    private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    public static RunicInscriberRecipes getInstance()
    {
        return INSTANCE;
    }

    private RunicInscriberRecipes()
    {
        List<ItemStack> getEssence = getEssenceRequired();
        List<ItemStack> getResults = getRuneStoneResults();
        for (int i = 0; i < essenceRequired.size(); i++)
        {
            addInscriberRecipe(getEssence.get(i), new ItemStack(ItemInit.ITEM_RUNE, 1, 0), getResults.get(i), getRuneStoneEXP().get(i));
        }
    }


    public void addInscriberRecipe(ItemStack input2, ItemStack input1, ItemStack result, float experience)
    {
        if(getInscriberResult(input2, input1) != ItemStack.EMPTY) return;
        this.smeltingList.put(input2, input1, result);
        this.experienceList.put(result, Float.valueOf(experience));
    }

    public ItemStack getInscriberResult(ItemStack input1, ItemStack input2)
    {
        for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet())
        {
            if(this.compareItemStacks(input1, (ItemStack)entry.getKey()))
            {
                for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                {
                    if(this.compareItemStacks(input2, (ItemStack)ent.getKey()))
                    {
                        return (ItemStack)ent.getValue();
                    }
                }
            }
        }
        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList()
    {
        return this.smeltingList;
    }

    public float getInscriberExperience(ItemStack stack)
    {
        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if(this.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }
        return 0.0F;
    }

    public List<ItemStack> getEssenceRequired()
    {
        return essenceRequired;
    }
    public List<ItemStack> getRuneStoneResults() { return runeStoneResults; }
    private List<Float> getRuneStoneEXP(){ return runeStoneEXP; }
}