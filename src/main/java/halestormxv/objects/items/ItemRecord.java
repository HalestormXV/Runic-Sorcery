package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.init.ItemInit;
import halestormxv.utility.Reference;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import javax.annotation.Nonnull;

public class ItemRecord extends net.minecraft.item.ItemRecord implements IHasModel
{
    private final String file;

    public ItemRecord(String record, SoundEvent sound, String name)
    {
        super("hsrs:" + record, sound);
        setCreativeTab(RunicSorcery.RUNICSORCERY);
        setRegistryName(name);
        setUnlocalizedName(name);
        file = "hsrs:music." + record;

        ItemInit.ITEMS.add(this);
    }

    @Nonnull
    @Override
    public String getUnlocalizedNameInefficiently(@Nonnull ItemStack par1ItemStack) {
        return super.getUnlocalizedNameInefficiently(par1ItemStack).replaceAll("item\\.", "item." + Reference.MODID + ":");
    }

    @Override
    public void registerModels()
    {
        RunicSorcery.proxy.registerItemRenderer(this, 0 , "inventory");
    }
}
