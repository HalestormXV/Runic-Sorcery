package halestormxv.utility.handlers;

import halestormxv.init.ItemInit;
import halestormxv.utility.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LootTableHandler
{
    public static final ResourceLocation CULTIST = LootTableList.register(new ResourceLocation(Reference.MODID, "cultist"));


    private static LootEntry scryingOrb = new LootEntryItem(ItemInit.ITEM_SCRYING_ORB, 30, 15, new LootFunction[0], new LootCondition[0], Reference.MODID+":loot_scrying_orb");
    private static LootEntry basicEssence = new LootEntryItem(ItemInit.RUNE_ESSENCE, 20, 10, new LootFunction[0], new LootCondition[0], Reference.MODID+":loot_basic_essence");

    @SubscribeEvent
    public void onLootTableLoad(final LootTableLoadEvent event)
    {
        if(event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) { event.getTable().getPool("main").addEntry(scryingOrb); }
        if(event.getName().equals(LootTableList.GAMEPLAY_FISHING_TREASURE)) { event.getTable().getPool("main").addEntry(basicEssence); }
    }
}
