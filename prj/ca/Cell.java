package prj.ca;

import prj.ecosytem.WorldConstants;
import processing.core.PApplet;
import processing.core.PImage;

public class Cell {
    private int row, col;
    protected int state;
    private Cell[] neighbors;
    protected CellularAutomata ca;
    private PImage img;

    public Cell(CellularAutomata ca, int row, int col){
        this.ca=ca;
        this.row=row;
        this.col=col;
        this.state = 0;
        this.neighbors=null;
    }
    public void setState(int state)
    {
        this.state = state;
    }

    public void setNeighbors(Cell[] neighbors) {
        this.neighbors = neighbors;
    }

    public Cell[] getNeighbors(){
        return neighbors;
    }
    public int getState(){
        return this.state;
    }

    public void display(PApplet p) {
        p.pushStyle();
        if (WorldConstants.TERRAIN_PATHS[state] == null) img = null;
        else img = p.loadImage(WorldConstants.TERRAIN_PATHS[state]);
        if (img != null) {
            // If there's an image, draw the image in the cell
            p.image(img, ca.xmin + col * ca.cellWidth, ca.ymin + row * ca.cellHeight, ca.cellWidth, ca.cellHeight);
        } else {
            // If no image, fill the cell with a color based on the state
            p.fill(ca.getStateColors()[state]);
            p.rect(ca.xmin + col * ca.cellWidth, ca.ymin + row * ca.cellHeight, ca.cellWidth, ca.cellHeight);
        }

        p.popStyle();
    }
}
