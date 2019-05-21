package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.init.ItemInit;
import halestormxv.utility.interfaces.IHasModel;
import halestormxv.utility.interfaces.IMetaName;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMagicianCore extends Item implements IMetaName, IHasModel {
    public static final PropertyEnum<EnumHandlerMagiciansCore.EnumTypeCore> VARIANT = PropertyEnum.create("variant", EnumHandlerMagiciansCore.EnumTypeCore.class);
    private String name;

    public ItemMagicianCore(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(RunicSorcery.RUNICSORCERY);
        setHasSubtypes(true);
        setMaxStackSize(1);

        this.name = name;

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (EnumHandlerMagiciansCore.EnumTypeCore variant : EnumHandlerMagiciansCore.EnumTypeCore.values()) {
            items.add(new ItemStack(this, 1, variant.getMeta()));
        }
    }

    @Override
    public void registerModels() {
        for (int i = 0; i < EnumHandlerMagiciansCore.EnumTypeCore.values().length; i++) {
            RunicSorcery.proxy.registerVariantRenderer(this, i, "cores/core_" + EnumHandlerMagiciansCore.EnumTypeCore.values()[i].getName(), "inventory");
        }
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumHandlerMagiciansCore.EnumTypeCore.values()[stack.getItemDamage()].getName();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "_" + this.getSpecialName(stack);
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack) {
        switch (itemStack.getItemDamage()) {
            case 1:
                return EnumRarity.RARE;
            default:
                return EnumRarity.COMMON;
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        switch (stack.getItemDamage()) {
            case 1:
                return true;

            default:
                return false;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if(stack.getItemDamage() == 0) {
            tooltip.add("");
            tooltip.add("\u00A76" + "A valuable core used for crafting a");
            tooltip.add("\u00A76" + "variety of items. Charge it by casting");
            tooltip.add("\u00A7c" + "Empower" + " \u00A76with the Core in your Inventory.");
            tooltip.add("");
        } else {
            tooltip.add("");
            tooltip.add("\u00A76" + "A valuable core used for crafting a");
            tooltip.add("\u00A76" + "variety of items. This is the charged");
            tooltip.add("\u00A76" + "variant of the item. It has different uses.");
            tooltip.add("");
        }
    }

    /**
     * PLACE ENUM HANDLER IN SAME CLASS
     * FOR CONVENIENCE SAKE
     * IT'S ANNOYING HAVING SEPARATE CLASSES
     * EACH TIME
     */
    public static class EnumHandlerMagiciansCore {
        public enum EnumTypeCore implements IStringSerializable {
            BASIC_UNCHARGED_CORE(0, "basic_uncharged"),
            BASIC_CHARGED_CORE(1, "basic_charged");

            private static final EnumTypeCore[] META_LOOKUP = new EnumTypeCore[values().length];
            private final int meta;
            private final String name, unlocalizedName;

            EnumTypeCore(int meta, String name) {
                this(meta, name, name);
            }

            EnumTypeCore(int meta, String name, String unlocalizedName) {
                this.meta = meta;
                this.name = name;
                this.unlocalizedName = unlocalizedName;
            }

            @Override
            public String getName() {
                return this.name;
            }

            public int getMeta() {
                return this.meta;
            }

            public String getUnlocalizedName() {
                return this.unlocalizedName;
            }

            @Override
            public String toString() {
                return this.name;
            }

            public static EnumTypeCore byMetadata(int meta) {
                return META_LOOKUP[meta];
            }

            static {
                for (EnumTypeCore enumTypeEssences : values()) {
                    META_LOOKUP[enumTypeEssences.getMeta()] = enumTypeEssences;
                }
            }
        }
    }
}
