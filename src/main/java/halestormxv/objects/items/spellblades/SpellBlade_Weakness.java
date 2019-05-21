package halestormxv.objects.items.spellblades;

import halestormxv.objects.items.ItemSpellBlade;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class SpellBlade_Weakness extends ItemSpellBlade
{

    public SpellBlade_Weakness(String name, ToolMaterial material)
    {
        super(name, material);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        String colorName = super.getItemStackDisplayName(stack);
        return "\u00A72" + colorName;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add("");
        tooltip.add("\u00A76" + "Power over others is");
        tooltip.add("\u00A76" + "weakness disguised as");
        tooltip.add("\u00A76" + "strength. My strength");
        tooltip.add("\u00A76" + "is power over weakness.");
    }
}
