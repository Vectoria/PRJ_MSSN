package prj.ecosytem;

public class WorldConstants {

    //World
    public final static double[] WINDOW = {-10, 10, -10, 10};

    // Terrain

    public final static int NROWS = 45;
    public final static int NCOLS = 60;

    public static enum PatchType {
        EMPTY, LAVA, FERTILE, WATER, FOOD  //lama
    }


    public final static double[] PATCH_TYPE_PROB = {0.f, 0.2f, 0.f, 0.2f, 0.6f};
    public final static int NSTATES = PatchType.values().length;
    public static int[][] TERRAIN_COLORS = {
            {250, 200, 60}, {160, 30, 70}, {200, 200, 60}, {101, 210, 244}, {40, 200, 20}
    };

    // mais imagens = mais lag
    public static String[] TERRAIN_PATHS = {
            "data/small_empty.png", "data/small_lava.jpg", "data/small_fertile.png", "data/small_water.jpg",
            "data/small_food.png"

    };
    public final static float[] REGENERATION_TIME = {10.f, 20.f}; //seconds

    // Ovelha
    public final static float OVELHA_SIZE = .2f;
    public final static float OVELHA_MASS = 1f;
    public final static int INI_OVELHA_POPULATION = 20;
    public final static float INI_OVELHA_ENERGY = 10f;
    public final static float ENERGY_FROM_PLANT = 4f;
    public final static float OVELHA_ENERGY_TO_REPRODUCE = 25f;

    public static int[] OVELHA_COLOR = {255, 255, 255};
    public static String OVELHA_PATH = "data/small_sheep.png";


    //Lobo

    public final static float LOBO_SIZE = .3f;
    public final static float LOBO_MASS = 1f;
    public final static int INI_LOBO_POPULATION = 20;
    public final static float INI_LOBO_ENERGY = 14f;
    public final static float LOBO_ENERGY_TO_REPRODUCE = 25f;
    public final static float ENERGY_FROM_HUNT = 12f;

    public static int[] LOBO_COLOR = {0, 0, 0};
    public static String LOBO_PATH = "data/small_wolf.png";

    // Ourico
    public final static float OURICO_SIZE = .2f;
    public final static float OURICO_MASS = .7f;
    public final static int INI_OURICO_POPULATION = 10;
    public final static float INI_OURICO_ENERGY = 8f;
    public final static float OURICO_ENERGY_TO_REPRODUCE = 19f;
    public final static float OVERDOSE_ENERGY = 22f;
    public final static float DEFENSE_HUNT = -5f;
    public static int[] OURICO_COLOR = {255, 255, 255};
    public static String OURICO_PATH = "data/small_hedgehog.png";

}