package halestormxv.commands;

import com.google.common.collect.Lists;
import halestormxv.capabilities.runecrafting.rcLvl_Provider;
import halestormxv.utility.Reference;
import halestormxv.capabilities.runecrafting.IRuneCraftLevel;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class SetRuneCraftLevel extends CommandBase
{
    private final List<String> aliases;

    public SetRuneCraftLevel()
    {
        aliases = Lists.newArrayList(Reference.MODID, "RC_Lvl", "rc_lvl", "RC_LvL", "rc_LvL");
    }

    @Override
    public String getName()
    {
        return "set_rc_level";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "set_rc_level <Value>";
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
        int rc_Lvl;
        try {
            rc_Lvl = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "Error parsing value!"));
            return;
        }
        if (rc_Lvl != MathHelper.clamp(rc_Lvl, 0, 100)) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + "The Minimum value is 0 and the Maximum value is 100!"));
        } else {
            if (sender instanceof EntityPlayer)
            {
                IRuneCraftLevel runeCraftLevel = ((EntityPlayer) sender).getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
                runeCraftLevel.setRuneLevel(rc_Lvl);
                sender.sendMessage(new TextComponentString(TextFormatting.DARK_AQUA + "Your Runecraft Level has been set to: "+ rc_Lvl));
                runeCraftLevel.syncToClient((EntityPlayer) sender);
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
