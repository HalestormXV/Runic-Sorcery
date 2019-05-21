package halestormxv.intergration.jei;

import halestormxv.gui.GuiRunicInscriber;
import halestormxv.init.ItemInit;
import halestormxv.intergration.jei.RunicInscriber.RunicInscriberCraftingCategory;
import halestormxv.intergration.jei.RunicInscriber.RunicInscriberRecipeWrapper;
import halestormxv.objects.blocks.devices.inscriber.RunicInscriberRecipes;
import halestormxv.utility.Logging;
import halestormxv.utility.Reference;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class RunicSorceryPlugin implements IModPlugin
{
    public static IJeiHelpers jeiHelper;

    @Override
    public void registerCategories(@Nonnull IRecipeCategoryRegistration registry)
    {
        jeiHelper = registry.getJeiHelpers();

        registry.addRecipeCategories(new RunicInscriberCraftingCategory(jeiHelper.getGuiHelper()));
    }

    @Override
    public void register(@Nonnull IModRegistry registry)
    {
        registry.handleRecipes(RunicInscriberRecipes.class, RunicInscriberRecipeWrapper.FACTORY, Reference.JEI.INSCRIBER_CRAFTING_UID);
        //registry.addRecipes(, Reference.JEI.INSCRIBER_CRAFTING_UID);
        registry.addRecipeClickArea(GuiRunicInscriber.class, 111, 69, 26, 19, Reference.JEI.INSCRIBER_CRAFTING_UID);
        registry.addRecipeCatalyst(new ItemStack(ItemInit.ITEM_RUNE, 1, 0), Reference.JEI.INSCRIBER_CRAFTING_UID);
    }
}
