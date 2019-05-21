package halestormxv.gui.handlers;

import com.google.common.collect.ImmutableSet;
import halestormxv.capabilities.runebag.RuneBagProvider;
import halestormxv.gui.GuiRuneBag;
import halestormxv.gui.GuiRuneBlade;
import halestormxv.init.ItemInit;
import halestormxv.inventory.runebag.RuneBagContainer;
import halestormxv.gui.GuiRunicInscriber;
import halestormxv.inventory.runeblade.ContainerRuneBlade;
import halestormxv.inventory.runicinscriber.ContainerRunicInscriber;
import halestormxv.objects.blocks.devices.inscriber.TileEntityRunicInscriber;
import halestormxv.objects.items.ItemRuneBlade;
import halestormxv.utility.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.Set;

public class GeneralGuiHandler implements IGuiHandler {
    private static final Set<Integer> ITEM_IDS = ImmutableSet.of(Reference.GUI_RUNE_BAG);
    private static final Set<Integer> RUNE_BLADE_ID = ImmutableSet.of(Reference.GUI_RUNE_BLADE);

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = !ITEM_IDS.contains(ID) ? world.getTileEntity(new BlockPos(x, y, z)) : null;
        EnumHand hand = ITEM_IDS.contains(ID) ? (x == 1 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND) : null;
        EnumHand bladeHand = RUNE_BLADE_ID.contains(ID) ? (x == 2 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND) : null;
        switch (ID) {
            case Reference.GUI_RUNIC_FURNACE:
                if (tile != null && tile instanceof TileEntityRunicInscriber)
                    return new ContainerRunicInscriber(player.inventory, (TileEntityRunicInscriber) tile);
                break;

            case Reference.GUI_RUNE_BAG: {
                EnumDyeColor color = EnumDyeColor.byMetadata(player.getHeldItem(hand).getItemDamage());
                IItemHandlerModifiable inventory = (IItemHandlerModifiable) player.getCapability(RuneBagProvider.RUNEBAG_CAP, null).getBag(color);
                return new RuneBagContainer(player.inventory, hand, inventory);
            }

            case Reference.GUI_RUNE_BLADE: {
                ItemStack stack = player.getHeldItem(bladeHand);
                IItemHandlerModifiable inventory = (IItemHandlerModifiable) stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
                return new ContainerRuneBlade(player.inventory, bladeHand, inventory);
            }
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = !ITEM_IDS.contains(ID) ? world.getTileEntity(new BlockPos(x, y, z)) : null;
        EnumHand hand = ITEM_IDS.contains(ID) ? (x == 1 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND) : null;
        EnumHand bladeHand = RUNE_BLADE_ID.contains(ID) ? (x == 2 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND) : null;
        switch (ID)
        {
            case Reference.GUI_RUNIC_FURNACE:
                if (tile != null && tile instanceof TileEntityRunicInscriber)
                    return new GuiRunicInscriber(player.inventory, (TileEntityRunicInscriber) tile);
                break;

            case Reference.GUI_RUNE_BAG: {
                EnumDyeColor color = EnumDyeColor.byMetadata(player.getHeldItem(hand).getItemDamage());
                IItemHandlerModifiable inventory = (IItemHandlerModifiable) player.getCapability(RuneBagProvider.RUNEBAG_CAP, null).getBag(color);
                return new GuiRuneBag(player.inventory, hand, inventory);
            }

            case Reference.GUI_RUNE_BLADE: {
                ItemStack stack = player.getHeldItem(bladeHand);
                IItemHandlerModifiable inventory = (IItemHandlerModifiable) stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
                return new GuiRuneBlade(player.inventory, bladeHand, inventory);
            }
        }
        return null;
    }
}

