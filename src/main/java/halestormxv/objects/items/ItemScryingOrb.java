package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.api.CooldownHandler;
import halestormxv.api.ReagentControl;
import halestormxv.init.ItemInit;
import halestormxv.network.packets.PacketChatUtils;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemScryingOrb extends Item implements IHasModel
{
    private int scryingOrbCooldown = 6000;

    public ItemScryingOrb(String name)
    {
        super();
        setUnlocalizedName(name);
        setCreativeTab(RunicSorcery.RUNICSORCERY_SPECIAL);
        setRegistryName(name);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        RunicSorcery.proxy.registerItemRenderer(this, 0 , "inventory");
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            ItemStack stack = player.getHeldItem(hand);
            NBTTagCompound nbt = stack.getTagCompound();


            if (player.isSneaking()) {
                if (nbt == null) {
                    nbt = new NBTTagCompound();
                    player.sendMessage(new TextComponentString("The Scrying Orb has stored your location data."));
                    nbt.setInteger("Dim", player.dimension);
                    nbt.setDouble("PosX", player.getPosition().getX());
                    nbt.setDouble("PosY", player.getPosition().getY());
                    nbt.setDouble("PosZ", player.getPosition().getZ());
                    stack.setTagCompound(nbt);
                } else {
                    long totWorldTime = CooldownHandler.getStoredWorldTime(stack);
                    long currentWorldTime = player.world.getTotalWorldTime();
                    //Check for Reagent
                    ItemStack reagentRequired = new ItemStack(ItemInit.DUST_SIEGRE, 3, 0);
                    boolean castRequirement = ReagentControl.checkAndConsumeReagent(player, reagentRequired);
                    if (castRequirement)
                    {
                        if ((stack.getTagCompound() != null) && (currentWorldTime > totWorldTime + scryingOrbCooldown))
                        {
                            if (player.isRiding()) {
                                player.dismountRidingEntity();
                            }
                            int fetchDim = nbt.getInteger("Dim");
                            double posX = nbt.getDouble("PosX");
                            double posY = nbt.getDouble("PosY");
                            double posZ = nbt.getDouble("PosZ");
                            if (player.dimension != fetchDim) {
                                player.changeDimension(fetchDim);
                            }
                            player.setPositionAndUpdate(posX + 0.6, posY, posZ + 0.6);
                            CooldownHandler.setNewWorldTime(stack, player);
                        } else {
                            PacketChatUtils.sendNoSpam(player, "\u00A74The Scrying Orb is on cooldown but still consumed the "+reagentRequired.getDisplayName()+". One cannot force magic, "+player.getDisplayNameString());
                        }
                    } else {
                        PacketChatUtils.sendNoSpam(player, "\u00A74The Scrying Orb requires "+reagentRequired.getCount()+" "+ reagentRequired.getDisplayName()+" for use.");
                    }
                }
            } else
                PacketChatUtils.sendNoSpam(player, "\u00A74You must sneak and right click to store Scrying data.");
        }
        return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.UNCOMMON;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("");
        tooltip.add("\u00A76" + "An orb used to store location data.");
        tooltip.add("\u00A76" + "After storing, it grants the ability");
        tooltip.add("\u00A76" + "to teleport to stored location.");
        tooltip.add("");
        if (stack.getTagCompound() != null) {
            NBTTagCompound nbt = stack.getTagCompound();
            int dimID = nbt.getInteger("Dim");
            double posX = nbt.getDouble("PosX");
            double posY = nbt.getDouble("PosY");
            double posZ = nbt.getDouble("PosZ");
            long storedWorldTime = nbt.getLong("totalWorldTime");
            long currentWorldTime = Minecraft.getMinecraft().world.getTotalWorldTime();
            tooltip.add("\u00A73" + "DIM: " + dimID);
            tooltip.add("\u00A7d" + "X: " + posX);
            tooltip.add("\u00A72" + "Y: " + posY);
            tooltip.add("\u00A7c" + "Z: " + posZ);
            tooltip.add("");
            if (CooldownHandler.getCooldownReal(scryingOrbCooldown, storedWorldTime, currentWorldTime) > 0)
            {
                tooltip.add("\u00A74" + "Cooldown: " + CooldownHandler.getCooldownReal(scryingOrbCooldown, storedWorldTime, currentWorldTime) + " Min");
            } else {
                tooltip.add("\u00A72" + "Scrying Orb is ready for use.");
            }

        }
    }
}