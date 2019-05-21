package halestormxv.objects.blocks;

import java.util.Random;

import halestormxv.RunicSorcery;
import halestormxv.init.BlockInit;
import halestormxv.init.ItemInit;
import halestormxv.world.HSRSTeleporter;
import halestormxv.world.dimensions.ModDimensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SuppressWarnings("deprecation")
public class BlockMysteriumPortal extends BlockBreakable
{

    public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis>create("axis", EnumFacing.Axis.class, new EnumFacing.Axis[] {EnumFacing.Axis.X, EnumFacing.Axis.Z});
    protected static final AxisAlignedBB field_185683_b = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
    protected static final AxisAlignedBB field_185684_c = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
    protected static final AxisAlignedBB field_185685_d = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
    private int portalIsOnCooldown = 600;

    public BlockMysteriumPortal(String name)
    {
        super(Material.PORTAL , false);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(RunicSorcery.RUNICSORCERY);
        setDefaultState(blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.X));
        setTickRandomly(true);
        setHardness(-1.0F);
        setLightLevel(0.75F);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (state.getValue(AXIS))
        {
            case X:
                return field_185683_b;
            case Y:
            default:
                return field_185685_d;
            case Z:
                return field_185684_c;
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    public static int getMetaForAxis(EnumFacing.Axis axis)
    {
        return axis == EnumFacing.Axis.X ? 1 : axis == EnumFacing.Axis.Z ? 2 : 0;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    public static boolean tryToCreatePortal(World par1World, BlockPos pos)
    {
        BlockMysteriumPortal.Size blockportal$size = new BlockMysteriumPortal.Size(par1World, pos, EnumFacing.Axis.X);

        if (blockportal$size.isValid() && blockportal$size.portalBlockCount == 0)
        {
            blockportal$size.func_150859_c();
            return true;
        }
        else
        {
            BlockMysteriumPortal.Size blockportal$size1 = new BlockMysteriumPortal.Size(par1World, pos, EnumFacing.Axis.Z);

            if (blockportal$size1.isValid() && blockportal$size1.portalBlockCount == 0)
            {
                blockportal$size1.func_150859_c();
                return true;
            } else
                return false;
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block neighborBlock, BlockPos pos2)
    {
        EnumFacing.Axis enumfacing$axis = state.getValue(AXIS);

        if (enumfacing$axis == EnumFacing.Axis.X)
        {
            BlockMysteriumPortal.Size blockportal$size = new BlockMysteriumPortal.Size(worldIn, pos, EnumFacing.Axis.X);

            if (!blockportal$size.isValid() || blockportal$size.portalBlockCount < blockportal$size.field_150868_h * blockportal$size.field_150862_g)
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
        else if (enumfacing$axis == EnumFacing.Axis.Z)
        {
            BlockMysteriumPortal.Size blockportal$size1 = new BlockMysteriumPortal.Size(worldIn, pos, EnumFacing.Axis.Z);

            if (!blockportal$size1.isValid() || blockportal$size1.portalBlockCount < blockportal$size1.field_150868_h * blockportal$size1.field_150862_g)
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        EnumFacing.Axis enumfacing$axis = null;

        if (state.getBlock() == this)
        {
            enumfacing$axis = state.getValue(AXIS);

            if (enumfacing$axis == null)
                return false;

            if (enumfacing$axis == EnumFacing.Axis.Z && side != EnumFacing.EAST && side != EnumFacing.WEST)
                return false;

            if (enumfacing$axis == EnumFacing.Axis.X && side != EnumFacing.SOUTH && side != EnumFacing.NORTH)
                return false;
        }

        boolean flag = worldIn.getBlockState(pos.west()).getBlock() == this && worldIn.getBlockState(pos.west(2)).getBlock() != this;
        boolean flag1 = worldIn.getBlockState(pos.east()).getBlock() == this && worldIn.getBlockState(pos.east(2)).getBlock() != this;
        boolean flag2 = worldIn.getBlockState(pos.north()).getBlock() == this && worldIn.getBlockState(pos.north(2)).getBlock() != this;
        boolean flag3 = worldIn.getBlockState(pos.south()).getBlock() == this && worldIn.getBlockState(pos.south(2)).getBlock() != this;
        boolean flag4 = flag || flag1 || enumfacing$axis == EnumFacing.Axis.X;
        boolean flag5 = flag2 || flag3 || enumfacing$axis == EnumFacing.Axis.Z;
        return flag4 && side == EnumFacing.WEST ?
                true : flag4 && side == EnumFacing.EAST ?
                true : flag5 && side == EnumFacing.NORTH ?
                true : flag5 && side == EnumFacing.SOUTH;
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, BlockPos pos, IBlockState state, Entity par5Entity)
    {

        if (!par5Entity.isRiding() && !par5Entity.isBeingRidden() && !par1World.isRemote)
            if(par5Entity.timeUntilPortal <= 0){
                if(par5Entity instanceof EntityPlayerMP)
                {
                    EntityPlayerMP thePlayer = (EntityPlayerMP)par5Entity;

                    thePlayer.timeUntilPortal = portalIsOnCooldown;
                    if (thePlayer.dimension != ModDimensions.mysteriumDimensionID)
                    {
                        if(!ForgeHooks.onTravelToDimension(thePlayer, ModDimensions.mysteriumDimensionID)) return;
                        thePlayer.mcServer.getPlayerList().transferPlayerToDimension(thePlayer, ModDimensions.mysteriumDimensionID, new HSRSTeleporter(thePlayer.mcServer.getWorld(ModDimensions.mysteriumDimensionID), this, BlockInit.BLOCK_STONES.getStateFromMeta(7)));
                    }
                    else {
                        if(!ForgeHooks.onTravelToDimension(thePlayer, 0)) return;
                        thePlayer.mcServer.getPlayerList().transferPlayerToDimension(thePlayer, 0, new HSRSTeleporter(thePlayer.mcServer.getWorld(0), this, BlockInit.BLOCK_STONES.getStateFromMeta(7)));
                    }
                } else {
                    MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                    par5Entity.timeUntilPortal = par5Entity.getPortalCooldown();

                    if(par5Entity.dimension != ModDimensions.mysteriumDimensionID){
                        if(!ForgeHooks.onTravelToDimension(par5Entity, ModDimensions.mysteriumDimensionID)) return;

                        int i = par5Entity.dimension;

                        par5Entity.dimension = ModDimensions.mysteriumDimensionID;
                        par1World.removeEntityDangerously(par5Entity);

                        par5Entity.isDead = false;

                        server.getPlayerList().transferEntityToWorld(par5Entity, i, server.getWorld(i), server.getWorld(ModDimensions.mysteriumDimensionID), new HSRSTeleporter(server.getWorld(ModDimensions.mysteriumDimensionID), this, BlockInit.BLOCK_STONES.getStateFromMeta(7)));
                    } else {
                        if(!ForgeHooks.onTravelToDimension(par5Entity, 0)) return;

                        par5Entity.dimension = 0;
                        par1World.removeEntityDangerously(par5Entity);

                        par5Entity.isDead = false;

                        server.getPlayerList().transferEntityToWorld(par5Entity, ModDimensions.mysteriumDimensionID, server.getWorld(ModDimensions.mysteriumDimensionID), server.getWorld(0), new HSRSTeleporter(server.getWorld(0), this, BlockInit.BLOCK_STONES.getStateFromMeta(7)));
                    }
                }
            } else par5Entity.timeUntilPortal = par5Entity.getPortalCooldown();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getItem(World par1World, BlockPos pos, IBlockState state)
    {
        return ItemStack.EMPTY;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(AXIS, (meta & 3) == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return getMetaForAxis(state.getValue(AXIS));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer.Builder(this).add(AXIS).build();
    }

    public static class Size
    {
        private final World world;
        private final EnumFacing.Axis axis;
        private final EnumFacing rightDirection;
        private final EnumFacing leftDirection;
        private int portalBlockCount = 0;
        private BlockPos blockPos;
        private int field_150862_g;
        private int field_150868_h;

        public Size(World worldIn, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_)
        {
            world = worldIn;
            axis = p_i45694_3_;

            if (p_i45694_3_ == EnumFacing.Axis.X)
            {
                leftDirection = EnumFacing.EAST;
                rightDirection = EnumFacing.WEST;
            }
            else
            {
                leftDirection = EnumFacing.NORTH;
                rightDirection = EnumFacing.SOUTH;
            }

            for (BlockPos blockpos = p_i45694_2_; p_i45694_2_.getY() > blockpos.getY() - 21 && p_i45694_2_.getY() > 0 && isEmptyBlock(worldIn.getBlockState(p_i45694_2_.down()).getBlock()); p_i45694_2_ = p_i45694_2_.down())
                ;

            int i = func_180120_a(p_i45694_2_, leftDirection) - 1;

            if (i >= 0)
            {
                blockPos = p_i45694_2_.offset(leftDirection, i);
                field_150868_h = func_180120_a(blockPos, rightDirection);

                if (field_150868_h < 2 || field_150868_h > 21)
                {
                    blockPos = null;
                    field_150868_h = 0;
                }
            }

            if (blockPos != null)
                field_150862_g = func_150858_a();
        }

        protected int func_180120_a(BlockPos p_180120_1_, EnumFacing p_180120_2_)
        {
            int i;

            for (i = 0; i < 22; ++i)
            {
                BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);

                if (!isEmptyBlock(world.getBlockState(blockpos).getBlock()) || world.getBlockState(blockpos.down()) != BlockInit.BLOCK_STONES.getStateFromMeta(7))
                    break;
            }

            IBlockState state = world.getBlockState(p_180120_1_.offset(p_180120_2_, i));
            return state == BlockInit.BLOCK_STONES.getStateFromMeta(7) ? i : 0;
        }

        public int func_181100_a()
        {
            return field_150862_g;
        }

        public int func_181101_b()
        {
            return field_150868_h;
        }

        protected int func_150858_a()
        {
            label24:

            for (field_150862_g = 0; field_150862_g < 21; ++field_150862_g)
                for (int i = 0; i < field_150868_h; ++i)
                {
                    BlockPos blockpos = blockPos.offset(rightDirection, i).up(field_150862_g);
                    IBlockState state = world.getBlockState(blockpos);

                    if (!isEmptyBlock(state.getBlock()))
                        break label24;

                    if (state.getBlock() == BlockInit.BLOCK_MYSTERIUM_PORTAL)
                        ++portalBlockCount;

                    if (i == 0)
                    {
                        state = world.getBlockState(blockpos.offset(leftDirection));

                        if (state != BlockInit.BLOCK_STONES.getStateFromMeta(7))
                            break label24;
                    }
                    else if (i == field_150868_h - 1)
                    {
                        state = world.getBlockState(blockpos.offset(rightDirection));

                        if (state != BlockInit.BLOCK_STONES.getStateFromMeta(7))
                            break label24;
                    }
                }

            for (int j = 0; j < field_150868_h; ++j)
                if (world.getBlockState(blockPos.offset(rightDirection, j).up(field_150862_g)) != BlockInit.BLOCK_STONES.getStateFromMeta(7))
                {
                    field_150862_g = 0;
                    break;
                }

            if (field_150862_g <= 21 && field_150862_g >= 3)
                return field_150862_g;
            else
            {
                blockPos = null;
                field_150868_h = 0;
                field_150862_g = 0;
                return 0;
            }
        }

        protected boolean isEmptyBlock(Block block)
        {
            return block.getMaterial(block.getDefaultState()) == Material.AIR || block == Blocks.FIRE || block == BlockInit.BLOCK_MYSTERIUM_PORTAL;
        }

        public boolean isValid()
        {
            return blockPos != null && field_150868_h >= 2 && field_150868_h <= 21 && field_150862_g >= 3 && field_150862_g <= 21;
        }

        public void func_150859_c()
        {
            for (int i = 0; i < field_150868_h; ++i)
            {
                BlockPos blockpos = blockPos.offset(rightDirection, i);

                for (int j = 0; j < field_150862_g; ++j)
                    world.setBlockState(blockpos.up(j), BlockInit.BLOCK_MYSTERIUM_PORTAL.getDefaultState().withProperty(BlockPortal.AXIS, axis), 2);
            }
        }
    }
}
