package halestormxv;

import halestormxv.utility.Reference;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyBindings
{

    public static KeyBinding runeBag, getRuneCraftLevel, cycleMagicSpells;

    public static void init()
    {
        runeBag = new KeyBinding(Reference.MODID+"_key.runebag", Keyboard.KEY_T, Reference.KEY_BINDINGS_CATEGORY);
        ClientRegistry.registerKeyBinding(runeBag);

        getRuneCraftLevel = new KeyBinding(Reference.MODID+"_key.runecraftlevel", Keyboard.KEY_O, Reference.KEY_BINDINGS_CATEGORY);
        ClientRegistry.registerKeyBinding(getRuneCraftLevel);

        cycleMagicSpells = new KeyBinding(Reference.MODID+"_key.cyclespells", Keyboard.KEY_Z, Reference.KEY_BINDINGS_CATEGORY);
        ClientRegistry.registerKeyBinding(cycleMagicSpells);
    }
}