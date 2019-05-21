package halestormxv.intergration.jei.RunicInscriber;

import halestormxv.init.ItemInit;
import halestormxv.intergration.jei.RunicSorceryPlugin;
import halestormxv.objects.blocks.devices.inscriber.RunicInscriberRecipes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.item.ItemStack;

import java.util.List;

public class RunicInscriberRecipeWrapper implements IRecipeWrapper
{
    public static final Factory FACTORY = new Factory();

    private List<List<ItemStack>> input;
    private ItemStack input2;
    private ItemStack fuelSlot;
    private List<List<ItemStack>> output;

    public RunicInscriberRecipeWrapper(RunicInscriberRecipes recipe)
    {
        input = RunicSorceryPlugin.jeiHelper.getStackHelper().expandRecipeItemStackInputs(recipe.getEssenceRequired());
        //input2 = RunicSorceryPlugin.jeiHelper.getStackHelper().isEquivalent(new ItemStack(ItemInit.ITEM_RUNE, 1, 0));
        //fuelSlot = RunicSorceryPlugin.jeiHelper.getStackHelper().isEquivalent(new ItemInit.DUST_MYSTERIUM);
        output = RunicSorceryPlugin.jeiHelper.getStackHelper().expandRecipeItemStackInputs(recipe.getRuneStoneResults());
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputLists(ItemStack.class, input);
        ingredients.setOutput(ItemStack.class, output);
    }

    private static class Factory implements IRecipeWrapperFactory<RunicInscriberRecipes>
    {
        @Override
        public IRecipeWrapper getRecipeWrapper(RunicInscriberRecipes recipe)
        {
            return new RunicInscriberRecipeWrapper(recipe);
        }
    }
}
