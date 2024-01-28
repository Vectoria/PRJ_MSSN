//parte fisica em movimento, 2º lei de Newton
package prj.aa;

import processing.core.PVector;

public class Mover {
    public PVector pos;
    protected PVector vel;
    protected PVector acc;
    protected float mass;

    private static double G= 6.67e-11;

    //public Mover(float mass, float radius) {
    //    this(new PVector(), new PVector(), new PVector(), mass);
    //}
    protected Mover(PVector pos, PVector vel, float mass){
        this.pos=pos.copy();
        this.vel=vel;
        this.mass=mass;
        acc=new PVector();
    }

    public void applyForce(PVector force) {
        acc.add(PVector.div(force, mass));
    }
    public PVector attraction(Mover m){
        PVector r= PVector.sub(pos,m.pos);
        float dist=r.mag();
        float strength=(float) (G*mass*m.mass/Math.pow(dist,2));
        return r.normalize().mult(strength);
    }
    public void move(float dt){
        vel.add(acc.mult(dt)); //guarda o resultado no acc, parece recursivo/static
        pos.add(PVector.mult(vel,dt)); //n guarda o resultado
        acc.mult(0); //n aplica as forças, é o embate na bola
    }
    public void setPos(PVector pos) {
        this.pos = pos;
    }

    public PVector getPos() {
        return pos;
    }

    public void setVel(PVector vel) {
        this.vel = vel;
    }

    public PVector getVel() {
        return vel;
    }
}
