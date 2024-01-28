package prj.aa;

public class DNA {
    /*public float maxSpeed, maxForce, visionDistance, visionSafeDistance, visionAngle,
            deltaTPursuit, raidusArrive, deltaTWander, radiusWander, deltaPhiWander;*/
    public float maxSpeed;
    public float maxForce;
    public float visionDistance;
    public float visionSafeDistance;
    public float visionAngle;
    public float deltaTPursuit;
    public float radiusArrive;
    public float deltaTWander;
    public float radiusWander;
    public float deltaPhiWander;

    public DNA() {
        //fisica
        maxSpeed = random(1f, 2f);
        maxForce = random(4f, 7f);
        //visao
        visionDistance = random(1.5f, 2.5f);
        visionSafeDistance = 0.25f * visionDistance;
        visionAngle = (float) Math.PI *0.3f;
        deltaTPursuit = random(0.5f, 1f);
        radiusArrive = random(3, 5);
        //Wander
        deltaTWander = random(0.3f,0.6f);
        radiusWander = random(1f, 3f);
        deltaPhiWander = (float) Math.PI / 8;
    }
    public DNA(DNA dna, boolean mutate){
        maxForce=dna.maxForce;
        maxSpeed=dna.maxSpeed;

        visionDistance=dna.deltaTPursuit;
        visionSafeDistance=dna.visionSafeDistance;
        visionAngle=dna.visionAngle;

        deltaTPursuit=dna.deltaTPursuit;
        radiusArrive=dna.radiusArrive;

        deltaTWander=dna.deltaTWander;
        deltaPhiWander=dna.deltaPhiWander;
        radiusWander=dna.radiusWander;

        if(mutate) mutate();
    }

    private void mutate(){
        maxSpeed+= random(-0.2f,0.2f);
        maxSpeed=Math.max(0,maxSpeed);
    }
   /* public float getRndMaxSpeed(){
        return  PSControl.getRnd(maxSpeed[0],maxSpeed[1]);
    }*/

    public static float random(float min, float max) {
        return (float) (min + (max - min) * Math.random());
    }
}
