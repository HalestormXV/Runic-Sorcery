package halestormxv.utility.handlers;

import halestormxv.utility.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class RecordHandler
{
    public static final SoundEvent kishuuMusic = makeSoundEvent("music.kishuu");
    public static final SoundEvent motomiyaMusic = makeSoundEvent("music.motomiyaflute");

    private static SoundEvent makeSoundEvent(String name) {
        ResourceLocation loc = new ResourceLocation(Reference.MODID, name);
        return new SoundEvent(loc).setRegistryName(loc);
    }

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> evt) {
        IForgeRegistry<SoundEvent> r = evt.getRegistry();
        r.register(kishuuMusic);
        r.register(motomiyaMusic);
    }

    private RecordHandler() {}
}
