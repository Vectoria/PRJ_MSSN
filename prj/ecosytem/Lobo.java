package prj.ecosytem;

import prj.aa.*;
import prj.tools.SubPlot;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Lobo extends Animal{

    private PopulationOvelhas pO;
    private PopulationOurico populationOurico;
    private PApplet parent;
    private SubPlot plt;
    private Boid alvo;
    private List<Body> allTrackingBodies;
    public Lobo(PVector pos, float mass, float radius, int color, PApplet parent, SubPlot plt, PopulationOvelhas pO, PopulationOurico populationOurico) {
        super(pos,mass,radius,color,parent,plt);
        this.parent=parent;
        this.plt=plt;
        energy=WorldConstants.INI_LOBO_ENERGY;
        this.pO=pO;
        this.populationOurico=populationOurico;
        /*
        allTrackingBodies = new ArrayList<Body>();
        alvo= pO.getAllAnimals().get((int) parent.random(0,pO.getNumAnimals()));
        allTrackingBodies.add(alvo);
        addBehavior(new Wander(1));
        addBehavior(new Arrive(2));
        Eye eye=new Eye(this,allTrackingBodies);
        setEye(eye);*/
    }
    public Lobo(Lobo prey,boolean mutate, PApplet parent, SubPlot plt,PopulationOvelhas pO, PopulationOurico populationOurico){
        super(prey,mutate,parent,plt);
        this.parent=parent;
        this.plt=plt;
        this.pO=pO;
        this.populationOurico=populationOurico;
        energy=WorldConstants.INI_LOBO_ENERGY;
       /* allTrackingBodies = new ArrayList<Body>();
        alvo= pO.getAllAnimals().get((int) parent.random(0,pO.getNumAnimals()));
        allTrackingBodies.add(alvo);
        addBehavior(new Wander(1));
        addBehavior(new Arrive(2));
        Eye eye=new Eye(this,allTrackingBodies);
        setEye(eye);
        allTrackingBodies.clear();*/
    }


    @Override
    public Animal reproduce(boolean mutate) {
        Animal child=null;
        if(energy>WorldConstants.LOBO_ENERGY_TO_REPRODUCE){
            energy-=WorldConstants.INI_LOBO_ENERGY;
            child=new Lobo(this,mutate, parent,plt,pO, populationOurico);
            if(mutate) child.mutateBehaviors();
        }
        return child;
    }

    @Override
    public void eat(Terrain terrain){
        for (Animal prey : pO.getAllAnimals()) {
            if (PVector.dist(this.pos, prey.pos) <= 0.3) {
                prey.energy = 0;
                energy += WorldConstants.ENERGY_FROM_HUNT;
                /*
                allTrackingBodies.add(pO.getAllAnimals().get((int) parent.random(0,pO.getNumAnimals())));
                setEye(new Eye(this, allTrackingBodies));
                allTrackingBodies.clear();*/
            }
        }
        for (Animal prey : populationOurico.getAllAnimals()) {
            if (PVector.dist(this.pos, prey.pos) <= 0.3) {
                energy += WorldConstants.DEFENSE_HUNT;
                prey.energy = 0;
                /*
                allTrackingBodies.add(pO.getAllAnimals().get((int) parent.random(0,pO.getNumAnimals())));
                setEye(new Eye(this, allTrackingBodies));
                allTrackingBodies.clear();*/
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