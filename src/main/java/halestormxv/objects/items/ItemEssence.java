package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.init.ItemInit;
import halestormxv.utility.interfaces.IHasModel;
import halestormxv.utility.interfaces.IMetaName;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;

public class ItemEssence extends Item implements IMetaName, IHasModel
{
    public static final PropertyEnum<EnumHandlerEssences.EnumTypeEssences> VARIANT = PropertyEnum.create("variant", EnumHandlerEssences.EnumTypeEssences.class);
    private String name;

    public ItemEssence(String name)
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
        for (EnumHandlerEssences.EnumTypeEssences variant : EnumHandlerEssences.EnumTypeEssences.values())
        {
            items.add(new ItemStack(this, 1, variant.getMeta()));
        }
    }

    @Override
    public void registerModels()
    {
        for(int i = 0; i < EnumHandlerEssences.EnumTypeEssences.values().length; i++)
        {
            RunicSorcery.proxy.registerVariantRenderer(this, i, "runeessence/essence_" + EnumHandlerEssences.EnumTypeEssences.values()[i].getName(), "inventory");
        }
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return EnumHandlerEssences.EnumTypeEssences.values()[stack.getItemDamage()].getName();
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

    /**
     * PLACE ENUM HANDLER IN SAME CLASS
     * FOR CONVENIENCE SAKE
     * IT'S ANNOYING HAVING SEPARATE CLASSES
     * EACH TIME
     */
    public static class EnumHandlerEssences
    {
        public enum EnumTypeEssences implements IStringSerializable
        {
            BASIC_ESSENCE(0, "basic"),
            AIR_ESSENCE(1, "air"),
            WATER_ESSENCE(2, "water"),
            FIRE_ESSENCE(3, "fire"),
            EARTH_ESSENCE(4, "earth"),
            DEATH_ESSENCE(5, "death"),
            GRAVITY_ESSENCE(6, "gravity"),
            BARRIER_ESSENCE(7, "barrier"),
            LIGHT_ESSENCE(8, "light"),
            VOID_ESSENCE(9, "void"),
            NIGHTMARE_ESSENCE(10, "nightmare"),
            POISON_ESSENCE(11, "poison");

            private static final EnumTypeEssences[] META_LOOKUP = new EnumTypeEssences[values().length];
            private final int meta;
            private final String name, unlocalizedName;

            EnumTypeEssences(int meta, String name)
            {
                this(meta, name, name);
            }

            EnumTypeEssences(int meta, String name, String unlocalizedName)
            {
                this.meta = meta;
                this.name = name;
                this.unlocalizedName = unlocalizedName;
            }

            @Override
            public String getName()
            {
                return this.name;
            }

            public int getMeta()
            {
                return this.meta;
            }

            public String getUnlocalizedName()
            {
                return this.unlocalizedName;
            }

            @Override
            public String toString()
            {
                return this.name;
            }

            public static EnumTypeEssences byMetadata(int meta)
            {
                return META_LOOKUP[meta];
            }

            static
            {
                for(EnumTypeEssences enumTypeEssences : values())
                {
                    META_LOOKUP[enumTypeEssences.getMeta()] = enumTypeEssences;
                }
            }
        }
    }
}
