package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.init.ItemInit;
import halestormxv.utility.handlers.EnumHandlerRunes;
import halestormxv.utility.interfaces.IHasModel;
import halestormxv.utility.interfaces.IMetaName;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemRunes extends Item implements IMetaName, IHasModel
{
    public static final PropertyEnum<EnumHandlerRunes.EnumTypeRunes> VARIANT = PropertyEnum.<EnumHandlerRunes.EnumTypeRunes>create("variant", EnumHandlerRunes.EnumTypeRunes.class);
    private String name;

    public ItemRunes(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(RunicSorcery.RUNICSORCERY);
        setHasSubtypes(true);

        this.name = name;

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        for (EnumHandlerRunes.EnumTypeRunes variant : EnumHandlerRunes.EnumTypeRunes.values())
        {
            items.add(new ItemStack(this, 1, variant.getMeta()));
        }
    }

    @Override
    public void registerModels()
    {
        for(int i = 0; i < EnumHandlerRunes.EnumTypeRunes.values().length; i++)
        {
            RunicSorcery.proxy.registerVariantRenderer(this, i, "runestones/rune_" + EnumHandlerRunes.EnumTypeRunes.values()[i].getName(), "inventory");
        }
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return EnumHandlerRunes.EnumTypeRunes.values()[stack.getItemDamage()].getName();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName()+ "_" + this.getSpecialName(stack);
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack)
    {
        switch(itemStack.getItemDamage())
        {
            case 5:
                return EnumRarity.EPIC;
            default:
                return EnumRarity.COMMON;
        }
    }
}
