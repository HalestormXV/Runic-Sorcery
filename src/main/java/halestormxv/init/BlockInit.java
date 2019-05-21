package halestormxv.init;

import halestormxv.objects.blocks.*;
import halestormxv.objects.blocks.devices.inscriber.BlockRunicInscriber;
import halestormxv.objects.blocks.runealtars.BlockRuneAltarAir;
import halestormxv.objects.blocks.runealtars.BlockRuneAltarEarth;
import halestormxv.objects.blocks.runealtars.BlockRuneAltarFire;
import halestormxv.objects.blocks.runealtars.BlockRuneAltarWater;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    //META BLOCKS
    public static final Block ORE_END = new BlockOres("ore_end", "end");
    public static final Block ORE_OVERWORLD = new BlockOres("ore_overworld", "overworld");
    public static final Block ORE_NETHER = new BlockOres("ore_nether", "nether");

    public static final Block LOGS = new BlockLogs("log");
    public static final Block LEAVES = new BlockLeaf("leaves");
    public static final Block SAPLINGS = new BlockSaplings("sapling");

    public static final Block BLOCK_STONES = new BlockStones("stone");
    public static final Block DIRT = new BlockDirts("dirt");
    public static final Block PLANKS = new BlockPlank("planks");


    //STANDARD BLOCKS
    public static final Block BLOCK_LUPRESIUM_COBBLE = new BlockBase("block_lupresium_cobblestone", Material.ROCK).setHardness(2.6f);
    public static final Block BLOCK_MYSTIC_COBBLE = new BlockBase("block_mystic_cobblestone", Material.ROCK).setHardness(2.6f);
    public static final Block ORE_SIEGRE = new BlockBase("ore_siegre", Material.ROCK).setHardness(5.0f).setLightLevel(5.0f);
    public static final Block BLOCK_SIEGRE = new BlockBase("block_siegre", Material.GLASS).setHardness(2.0f).setLightLevel(1.0f);
    public static final Block BLOCK_PHENET = new BlockBase("block_phenet", Material.IRON).setHardness(3.0f);
    public static final Block ORE_PHENET = new BlockBase("ore_phenet", Material.ROCK).setHardness(4.0f);

    //SPECIAL BLOCKS
    public static final Block RUNIC_INSCRIBER = new BlockRunicInscriber("runic_inscriber", Material.IRON);
    public static final Block LUPRESIUM_GRASS = new BlockGrasses("grass_lupresium", Material.GRASS);
    public static final Block MYSTIC_GRASS = new BlockGrasses("grass_mystic", Material.GRASS);
    public static final Block BLOCK_MYSTERIUM_PORTAL = new BlockMysteriumPortal("mysterium_portal");
    public static final Block BLOCK_OBELISK = new BlockObelisk("runic_obelisk", Material.ROCK);
    public static final Block BLOCK_ALTAR_FIRE = new BlockRuneAltarFire("runealtar_fire", Material.ROCK);
    public static final Block BLOCK_ALTAR_WATER = new BlockRuneAltarWater("runealtar_water", Material.ROCK);
    public static final Block BLOCK_ALTAR_EARTH = new BlockRuneAltarEarth("runealtar_earth", Material.ROCK);
    public static final Block BLOCK_ALTAR_AIR = new BlockRuneAltarAir("runealtar_air", Material.ROCK);
    public static final Block BLOCK_RCXP_ALTAR = new BlockRCXPAltar("rcxp_altar", Material.IRON);
}
