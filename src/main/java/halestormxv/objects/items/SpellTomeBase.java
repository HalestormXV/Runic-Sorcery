package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.init.ItemInit;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.item.Item;

public class SpellTomeBase extends Item implements IHasModel
{

    public SpellTomeBase(String name)
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
}
