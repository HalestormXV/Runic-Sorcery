package halestormxv.utility.handlers;

import halestormxv.utility.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler
{
    public static SoundEvent ENTITY_PHANTOM_AMBIENT, ENTITY_PHANTOM_HURT, ENTITY_PHANTOM_DEATH;
    public static SoundEvent  ENTITY_CULTIST_AMBIENT, ENTITY_CULTIST_HURT, ENTITY_CULTIST_DEATH, ENTITY_CULTIST_SPECIAL;
    public static SoundEvent EFFECT_RUNIC_INSCRIBER, EFFECT_SPELL_FIZZLE, EFFECT_SPELL_FIRE_SFX, EFFECT_ESSENCE_CONVERT, WIND_SFX;

    public static void registerSounds()
    {
        ENTITY_PHANTOM_AMBIENT = registerSound("entity.phantom.ambient");
        ENTITY_PHANTOM_HURT = registerSound("entity.phantom.hurt");
        ENTITY_PHANTOM_DEATH = registerSound("entity.phantom.death");

        ENTITY_CULTIST_AMBIENT = registerSound("entity.cultist.ambient");
        ENTITY_CULTIST_HURT = registerSound("entity.cultist.hurt");
        ENTITY_CULTIST_DEATH = registerSound("entity.cultist.death");
        ENTITY_CULTIST_SPECIAL = registerSound("entity.cultist.special");

        EFFECT_RUNIC_INSCRIBER = registerSound("effect.runic_inscriber.active");
        EFFECT_SPELL_FIZZLE = registerSound("effect.spell_cast_fizzle");
        EFFECT_SPELL_FIRE_SFX = registerSound("effect.spell_fire_sfx");
        EFFECT_ESSENCE_CONVERT = registerSound("effect.essence_conversion");
        WIND_SFX = registerSound("effect.wind_sfx");
    }

    private static SoundEvent registerSound(String name)
    {
        ResourceLocation location = new ResourceLocation(Reference.MODID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }

}
