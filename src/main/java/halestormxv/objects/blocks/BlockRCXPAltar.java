package halestormxv.objects.blocks;

import halestormxv.init.ItemInit;
import halestormxv.network.packets.PacketChatUtils;
import halestormxv.utility.handlers.SoundsHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRCXPAltar extends BlockBase
{

    public BlockRCXPAltar(String name, Material material)
    {
        super(name, material);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItem(hand);
        if (stack.getTagCompound() != null && stack.getItem() == ItemInit.RUNE_CRAFT_TALISMAN)
        {
            if (stack.getTagCompound().hasKey("Xp") && stack.getTagCompound().getFloat("Xp") != 0)
            {
                worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsHandler.EFFECT_ESSENCE_CONVERT, SoundCategory.BLOCKS, 2.0F, 1.0F);

                return true;
            } else {
                PacketChatUtils.sendNoSpam(playerIn, "\u00A73There is no XP stored in the talisman.");
            }
        }else{
            PacketChatUtils.sendNoSpam(playerIn, "\u00A73This altar will absorb Runecraft Experience from a Talisman and imbue it into you.");
        }
        return false;
    }
}
