package halestormxv.utility.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandlerRunes
{
    public static enum EnumTypeRunes implements IStringSerializable
    {
        BASIC_RUNE(0, "basic"),
        AIR_RUNE(1, "air"),
        WATER_RUNE(2, "water"),
        FIRE_RUNE(3, "fire"),
        EARTH_RUNE(4, "earth"),
        DEATH_RUNE(5, "death"),
        GRAVITY_RUNE(6, "gravity"),
        BARRIER_RUNE(7, "barrier"),
        LIGHT_RUNE(8, "light"),
        VOID_RUNE(9, "void"),
        NIGHTMARE_RUNE(10, "nightmare"),
        POISON_RUNE(11, "poison");

        private static final EnumTypeRunes[] META_LOOKUP = new EnumTypeRunes[values().length];
        private final int meta;
        private final String name, unlocalizedName;

        private EnumTypeRunes(int meta, String name)
        {
            this(meta, name, name);
        }

        private EnumTypeRunes(int meta, String name, String unlocalizedName)
        {
           this.meta = meta;
           this.name = name;
           this.unlocalizedName = unlocalizedName;
        }

        @Override
        public String getName()
        {
            return this.name;
        }

        public int getMeta()
        {
            return this.meta;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        @Override
        public String toString()
        {
            return this.name;
        }

        public static EnumTypeRunes byMetadata(int meta)
        {
            return META_LOOKUP[meta];
        }

        static
        {
            for(EnumTypeRunes enumTypeRunes : values())
            {
                META_LOOKUP[enumTypeRunes.getMeta()] = enumTypeRunes;
            }
        }
    }
}
