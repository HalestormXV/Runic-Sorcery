package halestormxv.objects.blocks;

import halestormxv.RunicSorcery;
import halestormxv.init.BlockInit;
import halestormxv.init.ItemInit;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.Random;

public class BlockBase extends Block implements IHasModel
{
    public BlockBase(String name, Material material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(RunicSorcery.RUNICSORCERY);
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels()
    {
        RunicSorcery.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0 , "inventory");
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this == BlockInit.ORE_SIEGRE ? ItemInit.DUST_SIEGRE : Item.getItemFromBlock(this);
    }

    @Override
    public int quantityDropped(Random random)
    {
        return this == BlockInit.ORE_SIEGRE ? 1 + random.nextInt(4) : 1;
    }
}
