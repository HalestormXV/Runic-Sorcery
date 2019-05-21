package halestormxv.objects.blocks;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import halestormxv.RunicSorcery;
import halestormxv.init.BlockInit;
import halestormxv.init.ItemInit;
import halestormxv.objects.blocks.item.ItemBlockVariants;
import halestormxv.utility.handlers.EnumHandlerWood;
import halestormxv.utility.interfaces.IHasModel;
import halestormxv.utility.interfaces.IMetaName;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockLogs extends BlockLog implements IMetaName, IHasModel
{
    public static final PropertyEnum<EnumHandlerWood.EnumTypeWood> VARIANT = PropertyEnum.<EnumHandlerWood.EnumTypeWood>create("variant", EnumHandlerWood.EnumTypeWood.class, new Predicate<EnumHandlerWood.EnumTypeWood>()
    {
        public boolean apply(@Nullable EnumHandlerWood.EnumTypeWood apply)
        {
            return apply.getMeta() < 2;
        }
    });

    private String name;

    public BlockLogs(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.WOOD);
        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandlerWood.EnumTypeWood.LUPRESIUM).withProperty(LOG_AXIS, EnumAxis.Y));
        setCreativeTab(RunicSorcery.RUNICSORCERY);
        setHarvestLevel("axe", 3);

        this.name = name;

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(EnumHandlerWood.EnumTypeWood customblockplanks$enumtypewood : EnumHandlerWood.EnumTypeWood.values())
        {
            items.add(new ItemStack(this, 1, customblockplanks$enumtypewood.getMeta()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState state = this.getDefaultState().withProperty(VARIANT, EnumHandlerWood.EnumTypeWood.byMetadata((meta & 1) % 2));

        switch(meta & 6)
        {
            case 0:
                state = state.withProperty(LOG_AXIS, EnumAxis.Y);
                break;

            case 2:
                state = state.withProperty(LOG_AXIS, EnumAxis.X);
                break;

            case 4:
                state = state.withProperty(LOG_AXIS, EnumAxis.Z);
                break;

            default:
                state = state.withProperty(LOG_AXIS, EnumAxis.NONE);
        }

        return state;
    }

    @SuppressWarnings("incomplete-switch")
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumHandlerWood.EnumTypeWood)state.getValue(VARIANT)).getMeta();

        switch((BlockLog.EnumAxis)state.getValue(LOG_AXIS))
        {
            case X:
                i |= 2;
                break;

            case Y:
                i |= 4;
                break;

            case Z:
                i |= 6;
        }

        return i;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT,LOG_AXIS});
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, ((EnumHandlerWood.EnumTypeWood)state.getValue(VARIANT)).getMeta());
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return ((EnumHandlerWood.EnumTypeWood)state.getValue(VARIANT)).getMeta();
    }

    @Override
    public String getSpecialName(ItemStack stack)
    {
        return EnumHandlerWood.EnumTypeWood.values()[stack.getItemDamage()].getName();
    }

    @Override
    public void registerModels()
    {
        for(int i = 0; i < EnumHandlerWood.EnumTypeWood.values().length; i++)
        {
            RunicSorcery.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "log_" + EnumHandlerWood.EnumTypeWood.values()[i].getName(), "inventory");
        }
    }
}