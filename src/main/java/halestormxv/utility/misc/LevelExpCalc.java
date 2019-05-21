package halestormxv.utility.misc;

public class LevelExpCalc
{
    public static void runeCraftLevelCalc()
    {
        double points = 0;
        double output = 0;
        int minlevel = 1; // first level to display
        int maxlevel = 100; // last level to display

        for (int lvl = 1; lvl <= maxlevel; lvl++)
        {
            points += Math.floor(lvl + 175 * Math.pow(2, lvl / 7.));
            if (lvl >= minlevel)
                output = Math.floor(points / 4);
                System.out.println("Level " + (lvl) + " - " + output + " xp");

        }
    }

    public static void runeCraftLevelGiveXP()
    {
        double points = 0;
        double output = 0;
        int minlevel = 1; // first level to display
        int maxlevel = 100; // last level to display

        for (int lvl = 1; lvl <= maxlevel; lvl++)
        {
            points += Math.floor(lvl + 175 * Math.pow(2, lvl / 7.));
            if (lvl >= minlevel)
                output = Math.floor(points / 4);
            System.out.println("Level " + (lvl) + " - " + output + " xp");

        }

    }
}
