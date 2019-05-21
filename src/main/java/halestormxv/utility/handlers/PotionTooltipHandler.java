package halestormxv.utility.handlers;

import com.mojang.realmsclient.gui.ChatFormatting;
import halestormxv.potion.PotionTypeBase;
import halestormxv.utility.Reference;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class PotionTooltipHandler
{
    @SubscribeEvent
    public void onTooltipEvent(ItemTooltipEvent evt)
    {
        List<String> toolTip = evt.getToolTip();
        if (evt.getItemStack().hasTagCompound())
        {
            if (evt.getItemStack().getTagCompound().hasKey("splitresult"))
            {
                toolTip.add(I18n.format("item.byproduct").replace("%", ""+(evt.getItemStack().getTagCompound().getInteger("splitresult"))));
            }
        }

        PotionType pt = PotionUtils.getPotionFromItem(evt.getItemStack());
        if (pt instanceof PotionTypeBase)
        {
            String potName = ((PotionTypeBase)pt).getPotion().getName();
            String textRaw = I18n.format("description."+potName);
            toolTip.add("");
            toolTip.add(textRaw);
            toolTip.add("");
            toolTip.add(ChatFormatting.GOLD+I18n.format("tooltip.credit", Reference.NAME));
        }

        else if (evt.getItemStack().hasTagCompound() && evt.getItemStack().getTagCompound().hasKey("brewdata"))
        {
            NBTTagCompound tag = evt.getItemStack().getTagCompound().getCompoundTag("brewdata");
            if (!tag.getBoolean("spoiled") && tag.hasKey("pot0")) {
                toolTip.add("");
                if (!tag.hasKey("pot1")) {
                    String prn = tag.getCompoundTag("pot0").getString("potion");
                }
            }
        }
    }
}
