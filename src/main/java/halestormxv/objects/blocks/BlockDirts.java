package halestormxv.objects.blocks;

import halestormxv.RunicSorcery;
import halestormxv.init.BlockInit;
import halestormxv.init.ItemInit;
import halestormxv.objects.blocks.item.ItemBlockVariants;
import halestormxv.utility.handlers.EnumHandlerWood;
import halestormxv.utility.interfaces.IHasModel;
import halestormxv.utility.interfaces.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class BlockDirts extends Block implements IMetaName, IHasModel
{
    public static final PropertyEnum<EnumHandlerWood.EnumTypeWood> VARIANT = PropertyEnum.<EnumHandlerWood.EnumTypeWood>create("variant", EnumHandlerWood.EnumTypeWood.class);

    private String name;

    public BlockDirts(String name)
    {
        super(Material.GROUND);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.GROUND);
        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandlerWood.EnumTypeWood.LUPRESIUM));
        setCreativeTab(RunicSorcery.RUNICSORCERY);
        setHardness(2.0f);

        this.name = name;

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));

    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return ((EnumHandlerWood.EnumTypeWood)state.getValue(VARIANT)).getMeta();
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
        return this.getDefaultState().withProperty(VARIANT, EnumHandlerWood.EnumTypeWood.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumHandlerWood.EnumTypeWood)state.getValue(VARIANT)).getMeta();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
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
            RunicSorcery.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "dirt_" + EnumHandlerWood.EnumTypeWood.values()[i].getName(), "inventory");
        }
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable)
    {
        return true;
    }
}
