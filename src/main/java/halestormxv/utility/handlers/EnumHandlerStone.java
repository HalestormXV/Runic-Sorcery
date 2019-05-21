package halestormxv.utility.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandlerStone
{
    public static enum EnumTypeStone implements IStringSerializable
    {
        LUPRESIUM_SMOOTHSTONE(0, "lupresium_smoothstone"),
        LUPRESIUM_RUNESTONE(1, "lupresium_runestone"),
        LUPRESIUM_BRICK(2, "lupresium_brick"),
        LUPRESIUM_CHISELED(3, "lupresium_chiseled"),

        MYSTIC_SMOOTHSTONE(4, "mystic_smoothstone"),
        MYSTIC_RUNESTONE(5, "mystic_runestone"),
        MYSTIC_BRICK(6, "mystic_brick"),
        MYSTIC_CHISELED(7, "mystic_chiseled");

        private static final EnumTypeStone[] META_LOOKUP = new EnumTypeStone[values().length];
        private final int meta;
        private final String name, unlocalizedName;

        private EnumTypeStone(int meta, String name)
        {
            this(meta, name, name);
        }

        private EnumTypeStone(int meta, String name, String unlocalizedName)
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

        public static EnumTypeStone byMetadata(int meta)
        {
            return META_LOOKUP[meta];
        }

        static
        {
            for(EnumTypeStone enumTypeStone: values())
            {
                META_LOOKUP[enumTypeStone.getMeta()] = enumTypeStone;
            }
        }
    }
}
