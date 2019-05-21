package halestormxv.utility.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandlerWood
{
    public static enum EnumTypeWood implements IStringSerializable
    {
        LUPRESIUM(0, "lupresium"),
        MYSTIC(1, "mystic");

        private static final EnumTypeWood[] META_LOOKUP = new EnumTypeWood[values().length];
        private final int meta;
        private final String name, unlocalizedName;

        private EnumTypeWood(int meta, String name)
        {
            this(meta, name, name);
        }

        private EnumTypeWood(int meta, String name, String unlocalizedName)
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

        public static EnumTypeWood byMetadata(int meta)
        {
            return META_LOOKUP[meta];
        }

        static
        {
            for(EnumTypeWood enumTypeWood : values())
            {
                META_LOOKUP[enumTypeWood.getMeta()] = enumTypeWood;
            }
        }
    }
}
