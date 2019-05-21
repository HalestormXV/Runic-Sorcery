package halestormxv.utility;

public class Reference
{
    public static final String MODID = "hsrs";
    public static final String NAME = "Runic Sorcery";
    public static final String VERSION = "1.0.0";
    public static final String CLIENT = "halestormxv.proxy.ClientProxy";
    public static final String COMMON = "halestormxv.proxy.CommonProxy";
    public static final String SERVER = "halestormxv.proxy.ServerProxy";
    public static final String RESOURCE_PREFIX = MODID + ":";
    public static final String KEY_BINDINGS_CATEGORY = MODID+"_keybindings";
    public static final String DEPENDENCIES = "after:jei";

    //public static final String GUI_FACTORY = "halestormxv.hsrs.config.ConfigGuiFactory";
    public static final String GUI_TEXTURE_DIR = "textures/gui/";

    //ENTITIES
    public static final int ENTITY_PHANTOM = 120;
    public static final int ENTITY_CULTIST = 121;
    public static final int ENTITY_HARBINGER = 122;

    //SPELLS
    public static final int SPELL_EMPOWER = 1;


    public static final String NETWORK_CHANNEL = "hsrs_channel";

    public static final int GUI_RUNIC_FURNACE = 0;
    public static final int GUI_RUNE_BAG = 1;
    public static final int GUI_RUNE_BLADE = 2;

    public static class JEI
    {
        public static final String INSCRIBER_CRAFTING_UID = MODID + ":inscriberCrafting";
        public static final String INSCRIBER_CRAFTING_TITLE_UNLOC = "jei.recipe.runicInscriber";
    }
}
