package prj.ecosytem;

import com.sun.org.apache.bcel.internal.generic.PUSH;

public class WorldConstants {

    //World
    public final static double[] WINDOW= {-10,10,-10,10};

    // Terrain

    public final static int NROWS = 45;
    public final static int NCOLS = 60;
    public static enum PatchType {
        EMPTY, OBSTACLE, FERTILE, FOOD  //lama
    }
    public final static double[] PATCH_TYPE_PROB={0.3f,0.1f,0.2f,0.4f};
    public final static int NSTATES = PatchType.values().length;
    public static int[][] TERRAIN_COLORS ={
            {250,200,60}, {160,30,70}, {200,200,60}, {40,200,20}
    };
    public final static float[] REGENERATION_TIME= {10.f, 20.f}; //seconds

    // Prey Population
    public final static float PREY_SIZE = .2f;
    public final static float PREY_MASS = 1f;
    public final static int INI_PREY_POPULATION = 200;
    public final static float INI_PREY_ENERGY = 10f;
    public final static float ENERGY_FROM_PLANT = 4f;
    public final static float PREY_ENERGY_TO_REPRODUCE = 25f;
    //public final static float PREY_LIFESPAN = 100f;
    //public final static float PREY_ADULT_AGE = PREY_LIFESPAN * 0.6f;
    public static int[] PREY_COLOR = {80, 100, 220};
}
