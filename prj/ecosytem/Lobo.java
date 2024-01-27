package prj.ecosytem;

import prj.tools.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

public class Lobo extends Animal{

    private PApplet parent;
    private SubPlot plt;
    public Lobo(PVector pos, float mass, float radius, int color, PApplet parent, SubPlot plt) {
        super(pos,mass,radius,color,parent,plt);
        this.parent=parent;
        this.plt=plt;
        energy=WorldConstants.INI_LOBO_ENERGY;
    }
    public Lobo(Lobo prey,boolean mutate, PApplet parent, SubPlot plt){
        super(prey,mutate,parent,plt);
        this.parent=parent;
        this.plt=plt;
        energy=WorldConstants.INI_LOBO_ENERGY;
    }

    public void eat(Terrain terrain, PopulationOvelhas pO) {
        for (Animal prey : pO.getAllAnimals()) {
            if (PVector.dist(this.pos, prey.pos) <= 0.3) {
                energy += WorldConstants.ENERGY_FROM_HUNT;
                prey.energy = 0;
            }
        }

    }

    @Override
    public Animal reproduce(boolean mutate) {
        Animal child=null;
        if(energy>WorldConstants.LOBO_ENERGY_TO_REPRODUCE){
            energy-=WorldConstants.INI_LOBO_ENERGY;
            child=new Lobo(this,mutate, parent,plt);
            if(mutate) child.mutateBehaviors();
        }
        return child;
    }

    @Override
    public void eat(Terrain terrain) {

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
