package halestormxv.objects.blocks;

import halestormxv.RunicSorcery;
import halestormxv.init.BlockInit;
import halestormxv.init.ItemInit;
import halestormxv.objects.blocks.item.ItemBlockVariants;
import halestormxv.utility.handlers.EnumHandlerStone;
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

import java.util.Random;

public class BlockStones extends Block implements IMetaName, IHasModel
{
    public static final PropertyEnum<EnumHandlerStone.EnumTypeStone> VARIANT = PropertyEnum.<EnumHandlerStone.EnumTypeStone>create("variant", EnumHandlerStone.EnumTypeStone.class);

    private String name;

    public BlockStones(String name)
    {
        super(Material.ROCK);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.STONE);
        setHardness(4.0f);
        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandlerStone.EnumTypeStone.LUPRESIUM_SMOOTHSTONE));
        setCreativeTab(RunicSorcery.RUNICSORCERY);
        setHarvestLevel("pickaxe", 1);

        this.name = name;

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));

    }

    @Override
    public int damageDropped(IBlockState state)
    {
        if (state.getValue(VARIANT) == EnumHandlerStone.EnumTypeStone.MYSTIC_SMOOTHSTONE) { return 0; }
        return ((EnumHandlerStone.EnumTypeStone)state.getValue(VARIANT)).getMeta();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(EnumHandlerStone.EnumTypeStone variant : EnumHandlerStone.EnumTypeStone.values())
        {
            items.add(new ItemStack(this, 1, variant.getMeta()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, EnumHandlerStone.EnumTypeStone.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumHandlerStone.EnumTypeStone)state.getValue(VARIANT)).getMeta();
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
        return EnumHandlerStone.EnumTypeStone.values()[stack.getItemDamage()].getName();
    }

    @Override
    public void registerModels()
    {
        for(int i = 0; i < EnumHandlerStone.EnumTypeStone.values().length; i++)
        {
            RunicSorcery.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "stone_" + EnumHandlerStone.EnumTypeStone.values()[i].getName(), "inventory");
        }
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable)
    {
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (state.getValue(VARIANT) == EnumHandlerStone.EnumTypeStone.LUPRESIUM_SMOOTHSTONE)
        {
            return Item.getItemFromBlock(BlockInit.BLOCK_LUPRESIUM_COBBLE);
        }
        else if (state.getValue(VARIANT) == EnumHandlerStone.EnumTypeStone.MYSTIC_SMOOTHSTONE)
        {
            return Item.getItemFromBlock(BlockInit.BLOCK_MYSTIC_COBBLE);
        }
        else { return Item.getItemFromBlock(this); }
    }
}
