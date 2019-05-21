package halestormxv.commands;

import com.google.common.collect.Lists;
import halestormxv.capabilities.learnedspells.ILearnedSpells;
import halestormxv.capabilities.learnedspells.LearnedSpellsMain;
import halestormxv.utility.Reference;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class LearnSpell extends CommandBase
{
    private final List<String> aliases;

    public LearnSpell()
    {
        aliases = Lists.newArrayList(Reference.MODID, "rs learn_spell");
    }

    @Override
    public String getName()
    {
        return "rs learn_spell";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "rs learn_spell <Value>";
    }

    @Override
    @Nonnull
    public List<String> getAliases()
    {
        return aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 1) {
            return;
        }
        String s = args[0];
        int spellID;
        try {
            spellID = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Error parsing value!"));
            return;
        }
        if (spellID != MathHelper.clamp(spellID, 0, 100)) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "The Minimum value is 0 and the Maximum value is 100!"));
        } else {
            if (sender instanceof EntityPlayer && sender.canUseCommand(4, ""))
            {
                ILearnedSpells learnedSpells = ((EntityPlayer) sender).getCapability(LearnedSpellsMain.LearnedSpellsProvider.LEARNED_SPELLS_CAPABILITY, null);
                learnedSpells.learnedSpell(spellID);
                sender.sendMessage(new TextComponentString(TextFormatting.DARK_AQUA + "You have learned spell "+ spellID));
            }
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    @Nonnull
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return Collections.emptyList();
    }
}
