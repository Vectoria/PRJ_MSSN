package prj.aa;

import processing.core.*;
import prj.tools.SubPlot;

import java.util.ArrayList;
import java.util.List;

public class Boid extends Body {
    protected float phiWander;
    private double[] window;
    private float sumWeights;
    protected DNA dna;
    protected Eye eye;
    private PShape shape;
    protected List<Behavior> behaviors;

    protected Boid(PVector pos, float mass, float radius, int color, PApplet p, SubPlot plt) {
        super(pos, new PVector(), mass, radius, color);
        dna = new DNA();
        behaviors = new ArrayList<Behavior>();
        window = plt.getWindow();
        setShape(p, plt);
    }

    public void setShape(PApplet p, SubPlot plt, float radius, int color) {
        this.radius = radius;
        this.color = color;
        setShape(p, plt);
    }

    public void setShape(PApplet p, SubPlot plt) {
        float[] rr = plt.getDimInPixel(radius, radius);
        shape = p.createShape();
        shape.beginShape();
        shape.fill(color);
        shape.noStroke();
        shape.vertex(-rr[0], rr[0] / 2);
        shape.vertex(rr[0] / 3, -rr[0] / 3);
        shape.vertex(rr[0], 0);
        shape.vertex(rr[0] / 3, rr[0] / 3);
        shape.vertex(-rr[0], -rr[0] / 2);
        shape.vertex(-rr[0] / 2, 0);

        shape.endShape(PConstants.CLOSE);
    }

    private void updateSumWeights() {
        sumWeights = 0;
        for (Behavior beh : behaviors) {
            sumWeights += beh.getWeight();
        }
    }

    public void addBehavior(Behavior behavior) {
        behaviors.add(behavior);
        updateSumWeights();
    }

    public void removeBehavior(Behavior behavior) {
        if (behaviors.contains(behavior))
            behaviors.remove(behavior);
        updateSumWeights();
    }

    public List<Behavior> getBehaviors() {
        return behaviors;
    }

    public void applyBehavior(int i, float dt) {
        if (eye != null) eye.look();
        Behavior behavior = behaviors.get(i);
        PVector vd = behavior.getDesiredVelocity(this);
        move(dt, vd);
    }

    public void applyBehaviors(float dt) {
        if (eye != null) eye.look();
        PVector vd = new PVector();
        for (Behavior behavior : behaviors) {
            PVector vdd = behavior.getDesiredVelocity(this);
            vdd.mult(behavior.getWeight() / sumWeights);
            vd.add(vdd);
        }
        move(dt, vd);
    }

    private void move(float dt, PVector vd) {
        vd.normalize().mult(dna.maxSpeed);
        PVector fs = PVector.sub(vd, vel);
        applyForce(fs.limit(dna.maxForce));
        super.move(dt);
        if (pos.x < window[0])
            pos.x += window[1] - window[0];
        if (pos.y < window[2])
            pos.y += window[3] - window[2];
        if (pos.x >= window[1])
            pos.x -= window[1] - window[0];
        if (pos.y >= window[3])
            pos.y -= window[3] - window[2];
    }
/*
    public PVector Seek(PVector target){
        PVector vd= PVector.sub(target,pos);
        vd.normalize().mult(dna.maxSpeed); //velocidade max
        return PVector.sub(vd,vel);
    }*/

    public void mutateBehaviors() {
        for (Behavior behavior : behaviors) {
            if (behavior instanceof AvoidObstacle) {
                behavior.weight += DNA.random(-0.2f, 0.5f);
                behavior.weight = Math.max(0, behavior.weight);
            }

        }
        updateSumWeights();
    }

    public DNA getDna() {
        return dna;
    }

    public Eye getEye() {
        return eye;
    }

    public void setDna(DNA dna) {
        this.dna = dna;
    }

    public void setEye(Eye eye) {
        this.eye = eye;
    }

    @Override
    public void setVel(PVector vel) {
        super.setVel(vel);
    }

    @Override
    public void setColor(int color) {
        super.setColor(color);
    }

    //@Override
    public void display(PApplet p, SubPlot plt, PImage img) {
        if (img != null) {
            p.pushMatrix();
            float[] pp = plt.getPixelCoord(pos.x, pos.y);
            p.image(img, pp[0], pp[1]);
            p.popMatrix();
        } else {
            p.pushMatrix();
            float[] pp = plt.getPixelCoord(pos.x, pos.y);
            p.translate(pp[0], pp[1]);
            p.rotate(-vel.heading());
            p.shape(shape);
            p.popMatrix();
        }
    }

}