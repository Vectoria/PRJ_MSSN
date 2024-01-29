package prj.ecosytem;

import prj.tools.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

public class Ovelha extends Animal {
    private PApplet parent;
    private SubPlot plt;
    public Ovelha(PVector pos, float mass, float radius, int color, PApplet parent, SubPlot plt) {
        super(pos,mass,radius,color,parent,plt);
        this.parent=parent;
        this.plt=plt;
        energy=WorldConstants.INI_OVELHA_ENERGY;
    }
    public Ovelha(Ovelha prey,boolean mutate, PApplet parent, SubPlot plt){
        super(prey,mutate,parent,plt);
        this.parent=parent;
        this.plt=plt;
        energy=WorldConstants.INI_OVELHA_ENERGY;
    }

    @Override
    public void eat(Terrain terrain) {
        Patch patch= (Patch) terrain.world2Cell(pos.x,pos.y);
        if (patch.getState() == WorldConstants.PatchType.FOOD.ordinal()) {
            energy += WorldConstants.ENERGY_FROM_PLANT;
            patch.setFertile(this.parent);
        }
    }

    @Override
    public Animal reproduce(boolean mutate) {
        Animal child=null;
        if(energy>WorldConstants.OVELHA_ENERGY_TO_REPRODUCE){
            energy-=WorldConstants.INI_OVELHA_ENERGY;
            child=new Ovelha(this,mutate, parent,plt);
            if(mutate) child.mutateBehaviors();
        }
        return child;
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