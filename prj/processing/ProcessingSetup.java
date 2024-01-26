package prj.processing;

import prj.ca.*;
import prj.ecosytem.*;
import processing.core.PApplet;

public class ProcessingSetup extends PApplet {
    public static IProcessingApp app;
    private int lastUpdate;
    @Override
    public void settings() {
        size(900, 900);
    }
    @Override
    public void setup() {
        app.setup(this);
        lastUpdate = millis();
    }
    @Override
    public void draw() {
        int now = millis();
        float dt = (now - lastUpdate) / 1000f;
        lastUpdate = now;
        app.draw(this, dt);
    }

    @Override
    public void keyPressed() {
        app.keyPressed(this);
    }
    @Override
    public void mousePressed() {
        app.mousePressed(this);
    }
    public void mouseReleased() {
        app.mouseReleased(this);
    }
    public void mouseDragged() {
        app.mouseDragged(this);
    }

    // descomentar apenas a app que se vai dar run
    public static void main(String[] args) {
        //app =  new TestCA();
        //app= new TestMajorityCA();
        app= new TestTerrainApp();
        PApplet.main(ProcessingSetup.class);
    }
}