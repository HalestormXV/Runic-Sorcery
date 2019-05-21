package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.init.ItemInit;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.item.ItemSword;

public class ItemSpellBlade extends ItemSword implements IHasModel
{
    public ItemSpellBlade(String name, ToolMaterial material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(RunicSorcery.RUNICSORCERY_SPECIAL);
        setMaxStackSize(1);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        RunicSorcery.proxy.registerItemRenderer(this, 0 , "inventory");
    }
}
