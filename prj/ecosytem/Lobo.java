package prj.ecosytem;

import prj.tools.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

public class Lobo extends Animal{

    PopulationOvelhas pO;
    private PApplet parent;
    private SubPlot plt;
    public Lobo(PVector pos, float mass, float radius, int color, PApplet parent, SubPlot plt, PopulationOvelhas pO) {
        super(pos,mass,radius,color,parent,plt);
        this.parent=parent;
        this.plt=plt;
        energy=WorldConstants.INI_LOBO_ENERGY;
        this.pO=pO;
    }
    public Lobo(Lobo prey,boolean mutate, PApplet parent, SubPlot plt,PopulationOvelhas pO){
        super(prey,mutate,parent,plt);
        this.parent=parent;
        this.plt=plt;
        this.pO=pO;
        energy=WorldConstants.INI_LOBO_ENERGY;
    }


    @Override
    public Animal reproduce(boolean mutate) {
        Animal child=null;
        if(energy>WorldConstants.LOBO_ENERGY_TO_REPRODUCE){
            energy-=WorldConstants.INI_LOBO_ENERGY;
            child=new Lobo(this,mutate, parent,plt,pO);
            if(mutate) child.mutateBehaviors();
        }
        return child;
    }

    @Override
    public void eat(Terrain terrain){
        for (Animal prey : pO.getAllAnimals()) {
            if (PVector.dist(this.pos, prey.pos) <= 0.3) {
                energy += WorldConstants.ENERGY_FROM_HUNT;
                prey.energy = 0;
            }
        }
    }


    @Override
    public void energy_consumption(float dt, Terrain terrain) {
        super.energy_consumption(dt, terrain);
    }

    @Override
    public boolean die() {
        return super.die();
    }
}
