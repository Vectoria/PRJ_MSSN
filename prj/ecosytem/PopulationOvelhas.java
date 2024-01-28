package prj.ecosytem;

import prj.aa.*;
import processing.core.PApplet;
import prj.tools.SubPlot;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class PopulationOvelhas {
    private List<Animal> allAnimals;

    private boolean mutate=true;
    private double[] window;
    private PImage img;

    public PopulationOvelhas(PApplet parent, SubPlot plt, Terrain terrain){
        window=plt.getWindow();
        allAnimals=new ArrayList<Animal>();

        List<Body> obstacles=terrain.getObstacles();

        for(int i = 0; i<WorldConstants.INI_OVELHA_POPULATION; i++){
            PVector pos = new PVector(parent.random((float) window[0], (float) window[1]), parent.random((float) window[2], (float) window[3]));
            int color = parent.color(WorldConstants.OVELHA_COLOR[0], WorldConstants.OVELHA_COLOR[1], WorldConstants.OVELHA_COLOR[2]);
            Animal a = new Ovelha(pos, WorldConstants.OVELHA_MASS, WorldConstants.OVELHA_SIZE, color, parent, plt);

            a.addBehavior(new Wander(1));
            a.addBehavior(new AvoidObstacle(0));
            Eye eye=new Eye(a,obstacles);
            a.setEye(eye);
            allAnimals.add(a);
        }
        img = parent.loadImage("data/small_sheep.png");
    }
    public void update(float dt, Terrain terrain){
        move(terrain,dt);
        eat(terrain);
        energy_consumption(dt,terrain);
        reproduce(mutate);
        die();
    }

    private void move(Terrain terrain, float dt){
        for (Animal a: allAnimals) a.applyBehaviors(dt);

    }

    private void eat(Terrain terrain){
        for(Animal a: allAnimals) a.eat(terrain);
    }

    private void energy_consumption( float dt,Terrain terrain){
        for (Animal a: allAnimals) a.energy_consumption(dt,terrain);
    }
    private void die(){
        for(int i=allAnimals.size()-1;i>=0;i--){
            Animal a=allAnimals.get(i);
            if(a.die()){
                allAnimals.remove(a);
            }
        }
    }

    private void reproduce(boolean mutate){
        for (int i=allAnimals.size()-1;i>=0;i--){
            Animal a= allAnimals.get(i);
            Animal child= a.reproduce(mutate);
            if(child!= null) allAnimals.add(child);
        }
    }

    public void display(PApplet p, SubPlot plt){
        for(Animal a: allAnimals){
            a.display(p,plt, img);
        }
    }
    public int getNumAnimals() {
        return allAnimals.size();
    }

    public float getMeanMaxSpeed(){
        float sum=0;
        for(Animal a: allAnimals)
            sum+=a.getDna().maxSpeed;
        return sum/allAnimals.size();
    }

    public float getStdMaxSpeed(){
        float mean=getMeanMaxSpeed();
        float sum=0;
        for(Animal a: allAnimals)
            sum+= Math.pow(a.getDna().maxSpeed-mean,2);
        return (float) Math.sqrt(sum/allAnimals.size()) ;
    }

    public float[] getMeanWeights(){
        float[] sums= new float[2];
        for(Animal a: allAnimals){
            sums[0]+= a.getBehaviors().get(0).getWeight();
            sums[1]+= a.getBehaviors().get(1).getWeight();
        }
        sums[0] /= allAnimals.size();
        sums[1] /= allAnimals.size();
        return sums;
    }

    public List<Animal> getAllAnimals() {
        return allAnimals;
    }

}