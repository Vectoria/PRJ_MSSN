package prj.ca;

import processing.core.PApplet;
import prj.processing.IProcessingApp;
import prj.tools.SubPlot;

public class TestCA implements IProcessingApp {
    private int nLinhas = 15;
    private int nColunas = 20;
    private int nStates = 4;
    private int radiusNeigh = 2;
    private CellularAutomata ca;
    private SubPlot plt;
    private double[] window= {0,10,0,10};
    private float[] vieport={0.3f,0.3f,0.5f,0.6f};

    @Override
    public void setup(PApplet p) {
        plt= new SubPlot(window,vieport,p.width,p.height);
        ca = new CellularAutomata(p, plt, nLinhas, nColunas, nStates, radiusNeigh);
        ca.initRandom();
    }

    @Override
    public void draw(PApplet p, float dt) {
        ca.display(p);
    }

    @Override
    public void mousePressed(PApplet p) {
        Cell cell = ca.pixel2Cell(p.mouseX, p.mouseY);
        cell.setState(nStates-1);

        Cell[] neigh = cell.getNeighbors();
        for(int i=0; i < neigh.length; i++) {
            neigh[i].setState(nStates-1);
        }
    }

    @Override
    public void keyPressed(PApplet p) {

    }

    @Override
    public void mouseReleased(PApplet p) {

    }

    @Override
    public void mouseDragged(PApplet p) {

    }
}
