package prj.ecosytem;

import prj.ca.MajorityCell;
import processing.core.PApplet;

public class Patch extends MajorityCell {
    private long eatenTime;
    private int timeToGrow;

    public Patch(Terrain terrain, int row, int col, int timeToGrow) {
        super(terrain, row, col);
        this.timeToGrow = timeToGrow;
        eatenTime = System.currentTimeMillis();
    }
    public void setFertile() {
        state = WorldConstants.PatchType.FERTILE.ordinal();
        eatenTime = System.currentTimeMillis();
        setImg(this.parent);
    }
    public void setFertile(PApplet p) {
        state = WorldConstants.PatchType.FERTILE.ordinal();
        eatenTime = System.currentTimeMillis();
        setImg(p);
    }

    public void regenerate() {
        if (state == WorldConstants.PatchType.FERTILE.ordinal()
                && System.currentTimeMillis() > (eatenTime + timeToGrow)) {
            state = WorldConstants.PatchType.FOOD.ordinal();
            setImg(this.parent);
        }
    }
    public void regenerate(PApplet p) {
        if (state == WorldConstants.PatchType.FERTILE.ordinal()
                && System.currentTimeMillis() > (eatenTime + timeToGrow)) {
            state = WorldConstants.PatchType.FOOD.ordinal();
            setImg(p);
        }
    }
}
