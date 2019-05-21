package halestormxv;

import halestormxv.init.ItemInit;
import halestormxv.potion.ModPotionHelper;
import halestormxv.potion.PotionTypeBase;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;

import java.util.ArrayList;

public class ModTab extends CreativeTabs
{
    //This is the Main Runic Sorcery Creative Tab
    public ModTab(String label) { super("RunicSorcery"); }
    public ItemStack getTabIconItem() { return new ItemStack(ItemInit.INGOT_PHENET); }
}
