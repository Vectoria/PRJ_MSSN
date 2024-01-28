package prj.ca;

import processing.core.PApplet;

public class Cell {
    private int row, col;
    protected int state;
    private Cell[] neighbors;
    protected CellularAutomata ca;

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

    public void display(PApplet p){
        p.pushStyle();
        p.fill(ca.getStateColors()[state]);
        p.rect(ca.xmin+col*ca.cellWidth, ca.ymin+row*ca.cellHeight, ca.cellWidth, ca.cellHeight);
        p.popStyle();
    }
}
