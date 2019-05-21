package halestormxv.objects.items;

import halestormxv.RunicSorcery;
import halestormxv.capabilities.runecrafting.IRuneCraftLevel;
import halestormxv.capabilities.runecrafting.rcLvl_Provider;
import halestormxv.init.ItemInit;
import halestormxv.utility.Logging;
import halestormxv.utility.interfaces.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class RuneCraftTalisman extends Item implements IHasModel
{
    //private HashMap<Integer, Double> xpList = new HashMap<>();
    //Reference: Constants.NBT;

    public RuneCraftTalisman(String name)
    {
        super();
        setUnlocalizedName(name);
        setCreativeTab(RunicSorcery.RUNICSORCERY);
        setRegistryName(name);
        setMaxStackSize(1);
        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        RunicSorcery.proxy.registerItemRenderer(this, 0, "inventory");
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        if (!world.isRemote)
        {
            ItemStack talisman = player.getHeldItem(hand);
            NBTTagCompound nbt = talisman.getTagCompound();
            IRuneCraftLevel runeCraftLevel = player.getCapability(rcLvl_Provider.RUNECRAFT_LEVEL, null);
            if (player.isSneaking())
            {
                if (nbt == null)
                {
                    nbt = new NBTTagCompound();
                    player.sendMessage(new TextComponentString("The Talisman has attuned to you."));
                    nbt.setFloat("Xp", 0);
                    talisman.setTagCompound(nbt);
                    runeCraftLevelUp(0, talisman);
                    nbt.setInteger("RCLvL", runeCraftLevel.getRuneLevel());
                }else{
                    int currentRuneLevel = runeCraftLevel.getRuneLevel();
                    float currentXP = nbt.getFloat("Xp");
                    NBTTagList xpTable = talisman.getTagCompound().getTagList("XpTable", 10);
                    int index = xpTable.tagCount();
                    NBTTagCompound theTag = xpTable.getCompoundTagAt(currentRuneLevel);
                    double xpRequired = theTag.getDouble(String.valueOf(currentRuneLevel));
                    if(currentXP >= xpRequired)
                    {
                        runeCraftLevel.gainRuneLevel(1);
                    }
                    nbt.setInteger("RCLvL", runeCraftLevel.getRuneLevel());
                    talisman.setTagCompound(nbt);
                }
            }
        }
        return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    private void runeCraftLevelUp(int lvl, ItemStack stack)
    {
        //if (xpList.isEmpty())//xpList.put(lvl, output);
        if (!stack.getTagCompound().hasKey("XpTable"))
        {
            NBTTagList xpTable = new NBTTagList();
            double points = 0;
            double output = 0;
            int minlevel = 0;
            int maxlevel = 100;
            int currentLevel = lvl;
            for (lvl = currentLevel; lvl <= maxlevel; lvl++)
            {
                NBTTagCompound levelStorage = new NBTTagCompound();
                points += Math.floor(lvl + 175 * Math.pow(2, lvl / 7.));
                if (lvl >= minlevel)
                    output = Math.floor(points / 4);
                levelStorage.setDouble(String.valueOf(lvl), output);
                xpTable.appendTag(levelStorage);
            }
            stack.setTagInfo("XpTable", xpTable);
        }else{
            Logging.getLogger().info("Already found the XP Table, no need generate one.");
        }
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {

        tooltip.add("");
        tooltip.add("\u00A76" + "This item will store all RCXP gained");
        tooltip.add("\u00A76" + "from Rune Crafting. It must be in");
        tooltip.add("\u00A76" + "your inventory. Only one at a time is allowed.");
        tooltip.add("\u00A74" + "Be Warned, this can be stolen or destroyed.");
        tooltip.add("");
        if (stack.getTagCompound() != null && stack.getTagCompound().hasKey("Xp"))
        {
            double currentXP = stack.getTagCompound().getFloat("Xp");
            NBTTagList xpTable = stack.getTagCompound().getTagList("XpTable", 10);
            NBTTagCompound theTag = xpTable.getCompoundTagAt(stack.getTagCompound().getInteger("RCLvL"));
            double xpRequired = theTag.getDouble(String.valueOf(stack.getTagCompound().getInteger("RCLvL")));
            tooltip.add("\u00A7d" +round(currentXP, 2) + " / " + xpRequired+" RCXP");
            if (currentXP >= xpRequired)
                tooltip.add("\u00A72" + "You are ready for Ascension. Shift+Right Click!");
        }
    }

    private static double round(double value, int places)
    {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}