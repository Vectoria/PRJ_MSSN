package prj.aa;

import processing.core.PApplet;
import processing.core.PVector;
import prj.processing.IProcessingApp;
import prj.tools.SubPlot;

import java.util.ArrayList;
import java.util.List;

public class BehaviorMutuo implements IProcessingApp {

    private Boid presa, pursuit;
    private double[] window = {-10, 10, -10, 10};
    private float[] viewport = {0, 0, 1, 1};
    private SubPlot plt;
    private Body target;
    private List<Body> allTrackingBodies;


    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport, p.width, p.height);
        presa = new Boid(new PVector(p.random((float) window[0], (float) window[1]), p.random((float) window[2], (float) window[3])),
                0.1f, 0.6f, p.color(20, 10, 90), p, plt);
        presa.addBehavior(new Wander(1f));
        presa.addBehavior(new Flee(0.17f));

        pursuit = new Boid(new PVector(p.random((float) window[0], (float) window[1]), p.random((float) window[2], (float) window[3])),
                0.5f, 0.6f, p.color(255, 0, 0), p, plt);
        pursuit.addBehavior(new Arrive(1f));
        allTrackingBodies= new ArrayList<Body>();

        allTrackingBodies.add(presa);
        pursuit.setEye(new Eye(pursuit, allTrackingBodies));
        allTrackingBodies.clear();

        allTrackingBodies.add(pursuit);
        presa.setEye(new Eye(presa, allTrackingBodies));
        allTrackingBodies.clear();
    }

    @Override
    public void draw(PApplet p, float dt) {
        p.background(70, 255, 245);

        presa.applyBehaviors(dt);
        pursuit.applyBehaviors(dt);
        pursuit.display(p, plt);
        presa.display(p, plt);
        presa.getEye().dispay(p,plt);
        pursuit.getEye().dispay(p,plt);
    }

    @Override
    public void mousePressed(PApplet p) {

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
