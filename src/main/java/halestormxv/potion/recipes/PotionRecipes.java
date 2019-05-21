package halestormxv.potion.recipes;


import halestormxv.init.ItemInit;
import halestormxv.potion.PotionReference;
import halestormxv.potion.PotionTypeBase;
import halestormxv.potion.recipes.brew.RecipeManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class PotionRecipes
{
    public static void registerRecipes()
    {
        //Potions with 3 Variants Go Here
        RecipeManager.registerRecipeWithVariant(PotionTypes.THICK, new ItemStack(Item.getItemFromBlock(Blocks.LAPIS_BLOCK)),
                PotionReference.INSTANCE.TYPE_LEARNING_NORMAL,
                PotionReference.INSTANCE.TYPE_LEARNING_LONG,
                PotionReference.INSTANCE.TYPE_LEARNING_STRONG);

        RecipeManager.registerRecipeWithVariant(PotionTypes.LONG_SLOWNESS, new ItemStack(Items.ENDER_EYE),
                PotionReference.INSTANCE.TYPE_RECALL_NORMAL,
                PotionReference.INSTANCE.TYPE_RECALL_LONG,
                PotionReference.INSTANCE.TYPE_RECALL_STRONG);

        RecipeManager.registerRecipeWithVariant(PotionReference.INSTANCE.TYPE_LEARNING_STRONG, new ItemStack(ItemInit.DUST_SIEGRE),
                PotionReference.INSTANCE.TYPE_RUNECRAFT_MASTERY_NORMAL,
                PotionReference.INSTANCE.TYPE_RUNECRAFT_MASTERY_LONG,
                PotionReference.INSTANCE.TYPE_RUNECRAFT_MASTERY_STRONG);

        RecipeManager.registerRecipeWithVariant(PotionTypes.AWKWARD, new ItemStack(ItemInit.RUNE_ESSENCE, 1, 3),
                PotionReference.INSTANCE.TYPE_FUSE_NORMAL,
                PotionReference.INSTANCE.TYPE_FUSE_QUICK,
                PotionReference.INSTANCE.TYPE_FUSE_STRONG);

        //Potions with 1 Variant Goes Here
        RecipeManager.registerRecipe(PotionReference.INSTANCE.TYPE_LEARNING_STRONG, PotionReference.INSTANCE.TYPE_DISPEL, Items.POISONOUS_POTATO);
        RecipeManager.registerRecipe(PotionTypes.LEAPING, PotionReference.INSTANCE.TYPE_RETURN, new ItemStack(ItemInit.DUST_MYSTERIUM));
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> evt) {
        IForgeRegistry<IRecipe> reg = evt.getRegistry();
        //reg.register(new StickyPotionRecipeHandler());
        //reg.register(new SplitPotionRecipeHandler());
        //reg.register(new QuickVialRecipeHandler());
    }

}
