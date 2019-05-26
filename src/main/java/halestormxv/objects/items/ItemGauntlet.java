package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.capabilities.dominion.PlayerDominion;
import halestormxv.capabilities.dominion.PlayerProperties;
import halestormxv.init.ItemInit;
import halestormxv.utility.Reference;
import halestormxv.utility.interfaces.IHasModel;
import halestormxv.world.dominion.WorldDominion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemGauntlet extends Item implements IHasModel
{
    public ItemGauntlet(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(RunicSorcery.RUNICSORCERY);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        RunicSorcery.proxy.registerItemRenderer(this, 0 , "inventory");
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        if (player.isSneaking()) {

            if (!world.isRemote) {
                WorldDominion worldDominion = WorldDominion.get(world);
                float amount = worldDominion.extractDominion(world, player.getPosition());
                PlayerDominion playerDominion = PlayerProperties.getPlayerDominion(player);
                playerDominion.setDominion(playerDominion.getDominion() + amount);
                return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
            }
        }else{
            if (!world.isRemote)
            {
                PlayerDominion playerDominion = PlayerProperties.getPlayerDominion(player);
                if (playerDominion.getDominion() >= 3)
                {
                    world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                    playerDominion.setDominion(playerDominion.getDominion() - 3);
                }
                return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
            }
        }
        return  super.onItemRightClick(world, player, hand);
    }
}
