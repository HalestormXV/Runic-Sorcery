package halestormxv.objects.items.spellblades;

import halestormxv.init.ItemInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpellBlade_Abilities
{
    @SubscribeEvent
    public void strikeEnemy(LivingHurtEvent event)
    {
        if (event.getSource().getTrueSource() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            if (player.getHeldItemMainhand() != ItemStack.EMPTY)
            {
                /**
                 * Add the different types of Spell Blades
                 * in this location. For clarity sake,
                 * this one handler will take call of all Spell Blade
                 * Abilities.
                 */
                if (player.getHeldItemMainhand().getItem() == ItemInit.SPELL_BLADE_FIRE)
                {
                    double d = Math.random();
                    if (d < 0.7)
                    {
                        event.getEntity().setFire(8);
                    }
                }

                if (player.getHeldItemMainhand().getItem() == ItemInit.SPELL_BLADE_WEAKNESS)
                {
                    double d = Math.random();
                    if (d < 0.7)
                    {
                        event.getEntityLiving().addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("weakness"), 200, 1));
                    }
                }


                /**
                 * END SPELL BLADE ABILITIES CONFIGURATION
                 */
            }
        }
    }
}
