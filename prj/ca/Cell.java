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
    protected PApplet parent;

    public Cell(CellularAutomata ca, int row, int col){
        this.ca=ca;
        this.row=row;
        this.col=col;
        this.state = 0;
        this.neighbors=null;
    }

    public Cell(PApplet parent, CellularAutomata ca, int row, int col){
        this.parent = parent;
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

    public void setImg(PApplet parent){
        if(parent != null) {
            if (WorldConstants.TERRAIN_PATHS[state] == null) img = null;
            else img = parent.loadImage(WorldConstants.TERRAIN_PATHS[state]);
        }
    }

    public void display(PApplet p) {
        p.pushStyle();
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