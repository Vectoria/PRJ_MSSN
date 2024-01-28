package prj.ecosytem;

import prj.aa.*;
import processing.core.PApplet;
import processing.core.PVector;
import prj.tools.SubPlot;

public abstract class Animal extends Boid implements IAnimal  {

    protected float energy;
    protected Animal(PVector pos, float mass, float radius, int color, PApplet p, SubPlot plt) {
        super(pos, mass, radius, color, p, plt);
    }

    protected Animal(Animal a, boolean mutate, PApplet p, SubPlot plt) {
        super(a.pos, a.mass, a.radius, a.color, p, plt);
        for (Behavior b : a.behaviors) {
            this.addBehavior(b);
        }
        if (a.eye != null) {
            eye = new Eye(this, a.eye);
        }
        dna = new DNA(a.dna, mutate);
    }



    @Override
    public void energy_consumption(float dt, Terrain terrain) {

        energy-= mass* Math.pow(vel.mag(),2)*dt;
        Patch patch=(Patch) terrain.world2Cell(pos.x,pos.y);
        if(patch.getState() == WorldConstants.PatchType.LAVA.ordinal())
            energy-=50*dt;
        if(patch.getState() == WorldConstants.PatchType.WATER.ordinal())
            energy-=3*dt;
        else{
            energy-=dt;
        }
    }

    @Override
    public boolean die() {
        return (energy<0);
    }


}
