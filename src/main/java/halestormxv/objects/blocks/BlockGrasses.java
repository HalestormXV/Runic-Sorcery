package halestormxv.objects.blocks;

import halestormxv.RunicSorcery;
import halestormxv.init.BlockInit;
import halestormxv.init.ItemInit;
import halestormxv.utility.handlers.EnumHandlerWood;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockGrasses extends Block implements IGrowable, IHasModel
{
    public BlockGrasses(String name, Material material)
    {
        super(material);
        this.setTickRandomly(true);
        setSoundType(SoundType.GROUND);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(RunicSorcery.RUNICSORCERY);
        setHarvestLevel("shovel", 1);
        setHardness(0.8f);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels()
    {
        RunicSorcery.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0 , "inventory");
    }

    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return true;
    }

    public int getMetaFromState(IBlockState state)
    {
        if (this == BlockInit.MYSTIC_GRASS) { return 1; }else{ return 0; }
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        if (this == BlockInit.MYSTIC_GRASS) { return 1; }else{ return 0; }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        Block block = state.getBlock();
        Block lupresiumDirt = BlockInit.DIRT.getDefaultState().withProperty(BlockDirts.VARIANT, EnumHandlerWood.EnumTypeWood.LUPRESIUM).getBlock();
        Block mysticDirt = BlockInit.DIRT.getDefaultState().withProperty(BlockDirts.VARIANT, EnumHandlerWood.EnumTypeWood.MYSTIC).getBlock();

        if (block == BlockInit.LUPRESIUM_GRASS) return Item.getItemFromBlock(lupresiumDirt);
        if (block == BlockInit.MYSTIC_GRASS) return Item.getItemFromBlock(mysticDirt);
        else return Item.getItemFromBlock(this);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    /**GROW METHOD FOR GRASS AND GROWABLE BLOCKS
     * This is required for growable blocks.
     */
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        BlockPos blockpos = pos.up();

        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            while (true)
            {
                if (j >= i / 16)
                {
                    if (worldIn.isAirBlock(blockpos1))
                    {
                        if (rand.nextInt(8) == 0)
                        {
                            worldIn.getBiome(blockpos1).plantFlower(worldIn, rand, blockpos1);
                        }
                        else
                        {
                            IBlockState iblockstate1 = Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS);

                            if (Blocks.TALLGRASS.canBlockStay(worldIn, blockpos1, iblockstate1))
                            {
                                worldIn.setBlockState(blockpos1, iblockstate1, 3);
                            }
                        }
                    }

                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                if (worldIn.getBlockState(blockpos1.down()).getBlock() != Blocks.GRASS || worldIn.getBlockState(blockpos1).isNormalCube())
                {
                    break;
                }

                ++j;
            }
        }
    }
}
