package prj.ecosytem;

import com.sun.org.apache.bcel.internal.generic.PUSH;

public class WorldConstants {

    //World
    public final static double[] WINDOW= {-10,10,-10,10};

    // Terrain

    public final static int NROWS = 40;
    public final static int NCOLS = 75;
    public static enum PatchType {
        EMPTY, OBSTACLE, FERTILE, FOOD  //lama
    }
    public final static int NSTATES = PatchType.values().length;
    public static int[][] TERRAIN_COLORS ={
            {200,200,60}, {160,30,70}, {200,200,60}, {40,200,20}
    };
    public final static float[] REGENERATION_TIME= {3.f, 5.f}; //seconds
}
