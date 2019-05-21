package halestormxv.objects.blocks.devices.inscriber;

import halestormxv.RunicSorcery;
import halestormxv.init.BlockInit;
import halestormxv.objects.blocks.BlockBase;
import halestormxv.utility.Reference;
import halestormxv.utility.handlers.SoundsHandler;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SuppressWarnings("deprecation")
public class BlockRunicInscriber extends BlockBase implements ITileEntityProvider, IHasModel
{
    private static final PropertyDirection FACING = BlockHorizontal.FACING;
    private static final PropertyBool ACTIVE = PropertyBool.create("active");

    public BlockRunicInscriber(String name, Material material)
    {
        super(name, Material.IRON);
        setSoundType(SoundType.METAL);
        setHardness(3.5f);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false));
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(BlockInit.RUNIC_INSCRIBER);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(BlockInit.RUNIC_INSCRIBER);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote)
        {
            playerIn.openGui(RunicSorcery.instance, Reference.GUI_RUNIC_FURNACE, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState west  = worldIn.getBlockState(pos.west());
            IBlockState east  = worldIn.getBlockState(pos.east());
            EnumFacing face = (EnumFacing)state.getValue(FACING);

            if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
            else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
            else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
            else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
        }
    }

    public static void setState(boolean active, World worldIn, BlockPos pos)
    {
        IBlockState state = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if(active) worldIn.setBlockState(pos, BlockInit.RUNIC_INSCRIBER.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(ACTIVE, true), 3);
        else worldIn.setBlockState(pos, BlockInit.RUNIC_INSCRIBER.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(ACTIVE, false), 3);

        if(tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityRunicInscriber();
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntityRunicInscriber tileentity = (TileEntityRunicInscriber)worldIn.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {ACTIVE,FACING});
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing facing = EnumFacing.getFront(meta);
        if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;

        boolean blockIsActive = (meta & 8) > 2;

        return this.getDefaultState().withProperty(FACING, facing).withProperty(ACTIVE, blockIsActive);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int blockIsActive = (state.getValue(ACTIVE) ? 1 : 0) << 3;
        int facingDirection = ((EnumFacing)state.getValue(FACING)).getIndex();

        //return ((EnumFacing)state.getValue(FACING)).getIndex();
        return facingDirection | blockIsActive;

    }

    public void registerModels()
    {
        RunicSorcery.proxy.registerVariantRenderer(Item.getItemFromBlock(this), 0, "runic_inscriber", "inventory");
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (stateIn.getValue(ACTIVE))
        {
            EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
            double d0 = (double)pos.getX() + 0.4D;
            double d1 = (double)pos.getY() + rand.nextDouble() * 6.0D / 16.0D + 1.3D;
            double d2 = (double)pos.getZ() + 0.4D;
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            if (rand.nextDouble() < 0.1D)
            {
                worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundsHandler.EFFECT_RUNIC_INSCRIBER, SoundCategory.BLOCKS, 0.7F, 1.0F, false);
            }

            switch (enumfacing)
            {
                case WEST:
                    worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case EAST:
                    worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case NORTH:
                    worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
                    break;
                case SOUTH:
                    worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
