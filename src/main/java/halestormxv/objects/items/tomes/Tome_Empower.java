package halestormxv.objects.items.tomes;

import halestormxv.RunicSorcery;
import halestormxv.abilities.Empower;
import halestormxv.capabilities.learnedspells.ILearnedSpells;
import halestormxv.capabilities.learnedspells.LearnedSpellsMain;
import halestormxv.network.packets.PacketChatUtils;
import halestormxv.objects.items.SpellTomeBase;
import halestormxv.utility.Reference;
import halestormxv.utility.handlers.SoundsHandler;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class Tome_Empower extends SpellTomeBase implements IHasModel {
    public Tome_Empower(String name) {
        super(name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        if (!playerIn.world.isRemote) {
            ItemStack theTome = playerIn.getHeldItem(handIn);
            ILearnedSpells iLearnedSpells = playerIn.getCapability(LearnedSpellsMain.LearnedSpellsProvider.LEARNED_SPELLS_CAPABILITY, null);
            boolean knownSpell = iLearnedSpells.alreadyLearned(Reference.SPELL_EMPOWER);
            if (knownSpell)
            {
                iLearnedSpells.learnedSpell(Reference.SPELL_EMPOWER);
                PacketChatUtils.sendNoSpam(playerIn, "\u00A7eYou have learned the Empower spell. Press the \"Cast Spell Key\" to use.");
                theTome.shrink(1);
            } else {
                DamageSource backFire = RunicSorcery.aetherChaos;
                backFire.setDamageBypassesArmor().isUnblockable();
                playerIn.getEntityWorld().playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundsHandler.EFFECT_SPELL_FIZZLE, SoundCategory.MASTER, 1.0F, 1.0F);
                PacketChatUtils.sendNoSpam(playerIn, "\u00A7eYou already learned this ability, the spell power has back-fired.");
                playerIn.attackEntityFrom(backFire, 6.0f);
            }
        }
        return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String colorName = super.getItemStackDisplayName(stack);
        return "\u00A7c" + colorName;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("");
        tooltip.add("\u00A76" + "A Spell Tome that");
        tooltip.add("\u00A76" + "will teach the player");
        tooltip.add("\u00A74Empower" + "\u00A76. Right Click to learn.");
        tooltip.add("");
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }
}
