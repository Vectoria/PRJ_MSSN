package prj.ecosytem;

import prj.processing.IProcessingApp;
import prj.tools.*;
import processing.core.PApplet;

public class TestEcosystemApp  implements IProcessingApp {
    private float timeDuration=60;
    private float refPopulation1=720;
    private float refPopulation2 =720;
    private float refPopulation3 =20;
    private float[] viewport = { 0f, 0f, 0.7f, 1f };

    private double[] winGraph1={0,timeDuration,0,2*refPopulation1};
    private double[] winGraph2={0,timeDuration,0,2* refPopulation2};
    private double[] winGraph3={0,timeDuration,0,2* refPopulation3};

    private float[] viewGraph1= {.71f, .04f, .28f, .28f};
    private float[] viewGraph2= {.71f, .37f, .28f, .28f};
    private float[] viewGraph3= {.71f, .70f, .28f, .28f};
    private SubPlot plt, pltGraph1, pltGraph2, pltGraph3;
    private TimeGraph tg1,tg2,tg3;
    private Terrain terrain;
    private PopulationOvelhas populationOvelhas;
    private PopulationLobos populationLobos;
    private PopulationOurico populationOurico;
    private float timer, updateGraphTime;
    private float intervalUpdate=1;

    private PApplet parent;

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(WorldConstants.WINDOW, viewport, p.width, p.height);
        pltGraph1=new SubPlot(winGraph1,viewGraph1,p.width,p.height);
        pltGraph2=new SubPlot(winGraph2,viewGraph2,p.width,p.height);
        pltGraph3=new SubPlot(winGraph3,viewGraph3,p.width,p.height);

        tg1= new TimeGraph(p,pltGraph1, p.color(255,0,0),refPopulation1);
        tg2= new TimeGraph(p,pltGraph2, p.color(255,0,0), refPopulation2);
        tg3= new TimeGraph(p,pltGraph3, p.color(255,0,0), refPopulation3);

        terrain = new Terrain(p, plt);
        terrain.setStateColors(getColors(p));
        terrain.initRandomCustom(WorldConstants.PATCH_TYPE_PROB);

        for (int i = 0; i < 2; i++) terrain.majorityRule();
        populationOvelhas = new PopulationOvelhas(p, plt, terrain);

        populationOurico=new PopulationOurico(p,plt,terrain);
        populationLobos= new PopulationLobos(p,plt,terrain,populationOvelhas, populationOurico);

        timer=0;
        updateGraphTime =timer+ intervalUpdate;

        this.parent = p;
    }

    @Override
    public void draw(PApplet p, float dt) {
        //p.background(70, 255, 245);

        timer+=dt;


        terrain.regenerate(this.parent);
        populationOvelhas.update(dt, terrain);
        populationLobos.update(dt,terrain);
        populationOurico.update(dt,terrain);

        terrain.display(p);
        populationOvelhas.display(p, plt);
        populationLobos.display(p,plt);
        populationOurico.display(p,plt);

        if (timer > updateGraphTime) {
            System.out.println(String.format("Time = %ds", (int) timer));
            System.out.println("numAnimais = " + populationOvelhas.getNumAnimals());
            System.out.println("MeanMaxSpeed = "+ populationOvelhas.getMeanMaxSpeed());
            System.out.println("StdMaxSpeed = "+ populationOvelhas.getStdMaxSpeed());
            System.out.println("meanWeightWander = "+ populationOvelhas.getMeanWeights()[0]+ " meanWeightAvoid = " + populationOvelhas.getMeanWeights()[1]);
            System.out.println("");

            tg1.plot(timer, populationOvelhas.getNumAnimals());
            tg2.plot(timer, populationOurico.getNumAnimals());
            tg3.plot(timer, populationLobos.getNumAnimals());

            updateGraphTime = timer + intervalUpdate;
        }
        //  System.out.println("numAnimals = " + populationOvelhas.getNumAnimals());
    }

    private int[] getColors(PApplet p) {
        int[] colors = new int[WorldConstants.NSTATES];
        for(int i = 0; i< WorldConstants.NSTATES;i++)
            colors[i] = p.color(WorldConstants.TERRAIN_COLORS[i][0], WorldConstants.TERRAIN_COLORS[i][1], WorldConstants.TERRAIN_COLORS[i][2]);
        return colors;
    }

    @Override
    public void mousePressed(PApplet p) {
        if (p.mouseButton == PApplet.LEFT) {
            winGraph1[0]= timer;
            winGraph1[1]=timer+timeDuration;
            winGraph1[3]= 2* populationOvelhas.getNumAnimals();
            pltGraph1=new SubPlot(winGraph1,viewGraph1,p.width,p.height);
            tg1= new TimeGraph(p,pltGraph1,p.color(255,0,0), populationOvelhas.getNumAnimals());

            winGraph2[0]= timer;
            winGraph2[1]=timer+timeDuration;
            winGraph2[3]= 2* populationOurico.getNumAnimals();
            pltGraph2=new SubPlot(winGraph2,viewGraph2,p.width,p.height);
            tg2= new TimeGraph(p,pltGraph2,p.color(255,0,0), populationOurico.getNumAnimals());

            winGraph3[0]= timer;
            winGraph3[1]=timer+timeDuration;
            winGraph3[3]=2* populationLobos.getNumAnimals();
            pltGraph3=new SubPlot(winGraph3,viewGraph3,p.width,p.height);
            tg3= new TimeGraph(p,pltGraph3,p.color(255,0,0), populationLobos.getNumAnimals());
        } else if (p.mouseButton == PApplet.RIGHT) {

            populationOvelhas = new PopulationOvelhas(p, plt, terrain);

            populationOurico=new PopulationOurico(p,plt,terrain);
            populationLobos= new PopulationLobos(p,plt,terrain,populationOvelhas, populationOurico);
        }

    }

    @Override
    public void keyPressed(PApplet p) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(PApplet p) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(PApplet p) {
        // TODO Auto-generated method stub

    }
}