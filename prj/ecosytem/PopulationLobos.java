package prj.ecosytem;

import prj.aa.*;
import prj.tools.SubPlot;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PopulationLobos {
    private List<Animal> allAnimals;
    private List<Body> allTrackingBodies;
    private boolean mutate = true;
    private double[] window;
    private Boid alvo;
    private PImage img;

    public PopulationLobos(PApplet parent, SubPlot plt, Terrain terrain, PopulationOvelhas pO, PopulationOurico populationOurico) {
        window = plt.getWindow();
        allAnimals = new ArrayList<Animal>();

        List<Body> obstacles = terrain.getObstacles();

        for (int i = 0; i < WorldConstants.INI_LOBO_POPULATION; i++) {
            PVector pos = new PVector(parent.random((float) window[0], (float) window[1]), parent.random((float) window[2], (float) window[3]));
            int color = parent.color(WorldConstants.LOBO_COLOR[0], WorldConstants.LOBO_COLOR[1], WorldConstants.LOBO_COLOR[2]);
            Animal a = new Lobo(pos, WorldConstants.LOBO_MASS, WorldConstants.LOBO_SIZE, color, parent, plt, pO, populationOurico);

            a.addBehavior(new Wander(1));
            //   a.addBehavior(new AvoidObstacle(0));
            allTrackingBodies = new ArrayList<Body>();
            alvo = pO.getAllAnimals().get((int) parent.random(0, pO.getNumAnimals()));
            allTrackingBodies.add(alvo);
            a.addBehavior(new Arrive(2));
            Eye eye = new Eye(a, allTrackingBodies);
            a.setEye(eye);
            allAnimals.add(a);
        }
        img = parent.loadImage(WorldConstants.LOBO_PATH);
    }

    public void update(float dt, Terrain terrain) {
        move(terrain, dt);
        eat(terrain);
        energy_consumption(dt, terrain);
        reproduce(mutate);
        die();
        lookAround();
    }

    private void lookAround(){
        // ordenar targets por distância, ordem crescente
        for (Animal animal : allAnimals) {
            float minDistance = Float.MAX_VALUE;

            for (Body body : allTrackingBodies) {
                float distance = PVector.dist(animal.pos, body.pos);

                if (distance < minDistance) {
                    minDistance = distance;
                }
            }

            Collections.sort(allTrackingBodies, Comparator.comparingDouble(body -> PVector.dist(animal.pos, body.pos)));
            Body target= allTrackingBodies.get(0);
            if(target instanceof Boid){
                for (Behavior behavior : animal.getBehaviors()) {
                    if (behavior instanceof Arrive) {
                        behavior.setWeight(4);
                    }
                    if (behavior instanceof AvoidObstacle) {
                        behavior.setWeight(1);
                    }
                }

            }
            else{
                for (Behavior behavior : animal.getBehaviors()) {
                    if (behavior instanceof AvoidObstacle) {
                        behavior.setWeight(10);
                    }
                    if (behavior instanceof Arrive) {
                        behavior.setWeight(1);
                    }
                }
            }
            Eye eye = new Eye(animal, allTrackingBodies);
            animal.setEye(eye);
            if(PVector.dist(target.pos,animal.pos)==0){
                allTrackingBodies.remove(0);
            }
           // System.out.println(target);
        }

        // ver se é lava ou sheep
        // aumentar avoid se for lava
        // aumentar pursuit se for sheep
    }

    private void move(Terrain terrain, float dt) {
        for (Animal a : allAnimals) a.applyBehaviors(dt);

    }

    private void eat(Terrain terrain) {
        for (Animal a : allAnimals) a.eat(terrain);
    }

    private void energy_consumption(float dt, Terrain terrain) {
        for (Animal a : allAnimals) a.energy_consumption(dt, terrain);
    }

    private void die() {
        for (int i = allAnimals.size() - 1; i >= 0; i--) {
            Animal a = allAnimals.get(i);
            if (a.die()) {
                allAnimals.remove(a);
            }
        }
    }

    private void reproduce(boolean mutate) {
        for (int i = allAnimals.size() - 1; i >= 0; i--) {
            Animal a = allAnimals.get(i);
            Animal child = a.reproduce(mutate);
            if (child != null) allAnimals.add(child);
        }
    }

    public void display(PApplet p, SubPlot plt) {
        for (Animal a : allAnimals) {
            a.display(p, plt, img);
        }
    }

    public int getNumAnimals() {
        return allAnimals.size();
    }

    public float getMeanMaxSpeed() {
        float sum = 0;
        for (Animal a : allAnimals)
            sum += a.getDna().maxSpeed;
        return sum / allAnimals.size();
    }

    public float getStdMaxSpeed() {
        float mean = getMeanMaxSpeed();
        float sum = 0;
        for (Animal a : allAnimals)
            sum += Math.pow(a.getDna().maxSpeed - mean, 2);
        return (float) Math.sqrt(sum / allAnimals.size());
    }

    public float[] getMeanWeights() {
        float[] sums = new float[2];
        for (Animal a : allAnimals) {
            sums[0] += a.getBehaviors().get(0).getWeight();
            sums[1] += a.getBehaviors().get(1).getWeight();
        }
        sums[0] /= allAnimals.size();
        sums[1] /= allAnimals.size();
        return sums;
    }

    public List<Animal> getAllAnimals() {
        return allAnimals;
    }
}