package halestormxv.objects.items.spellblades;

import halestormxv.objects.items.ItemSpellBlade;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class SpellBlade_Fire extends ItemSpellBlade
{

    public SpellBlade_Fire(String name, ToolMaterial material)
    {
        super(name, material);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        String colorName = super.getItemStackDisplayName(stack);
        return "\u00A7c" + colorName;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add("");
        tooltip.add("\u00A76" + "You fall into my arms. You are the");
        tooltip.add("\u00A76" + "good gift of destruction's path.");
        tooltip.add("\u00A76" + "When life sickens more than disease.");
        tooltip.add("\u00A76" + "Flames shall guide the way...");
    }
}
