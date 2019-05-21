package halestormxv.objects.blocks.runealtars;


import halestormxv.init.ItemInit;
import halestormxv.network.packets.PacketChatUtils;
import halestormxv.objects.blocks.BlockRuneAltar;
import halestormxv.utility.handlers.SoundsHandler;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;


public class BlockRuneAltarAir extends BlockRuneAltar implements IHasModel {
    private static AxisAlignedBB RUNE_ALTAR_AIR_AABB = new AxisAlignedBB(0.0875D, 0, 0.0875D, 0.7125D, 0.625D, 0.7125D);

    public BlockRuneAltarAir(String name, Material material) {
        super(name, material);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItem(hand);
        if (stack.getItem() == ItemInit.RUNE_ESSENCE && stack.getMetadata() == 0) {
            worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsHandler.EFFECT_ESSENCE_CONVERT, SoundCategory.BLOCKS, 2.0F, 1.0F);
            playerIn.inventory.clearMatchingItems(ItemInit.RUNE_ESSENCE, 0, 1, null);
            ItemStack airEssence = new ItemStack(ItemInit.RUNE_ESSENCE, 1, 1);
            playerIn.inventory.addItemStackToInventory(airEssence);
            return true;
        } else {
            PacketChatUtils.sendNoSpam(playerIn, "\u00A73This alter will absorb basic essence and change it to an " + "\u00A76Essence of Air." +
                    "\u00A73The altar may resonate with a Runecraft Talsiman in your inventory, which may allow you to receive RCXP.");
        }
        return false;
    }

    //EFFECT FUNCTIONS - NO NEED TO EDIT UNLESS WANTING TO CHANGE EFFECT OF ALTAR\\
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return RUNE_ALTAR_AIR_AABB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double d0 = (double) pos.getX() + 0.2D;
        double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D + 1.3D;
        double d2 = (double) pos.getZ() + 0.2D;
        double d4 = rand.nextDouble() * 0.6D - 0.3D;
        if (rand.nextDouble() < 0.03D) {
            worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, SoundsHandler.WIND_SFX,
                    SoundCategory.BLOCKS, 0.5F, 1.0F, true);
        }
        worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
        worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, d0 - d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
    }
}
