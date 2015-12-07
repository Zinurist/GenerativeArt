package algorithms.randomizer;

import algorithms.Algorithm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Randomizer extends Algorithm {

    protected boolean color;
    protected Random r;
    //TODO options for color selection

    protected Randomizer(){
        r = new Random();
        color = false;
    }

    public void step(){
        IMG.getGraphics().setColor(Color.BLACK);
        step(IMG.getGraphics(), IMG.getWidth(), IMG.getHeight());
    }

    public abstract void step(Graphics g, int width, int height);

}
