package algorithms.randomizer;

import algorithms.Algorithm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Randomizer extends Algorithm {

    protected boolean[][] bools;
    protected boolean color;
    protected Random r;
    //TODO options for color selection

    protected Randomizer(){
        r = new Random();
        color = false;
    }

    public void step(){
        init();
        Graphics g = IMG.createGraphics();
        g.setColor(Color.BLACK);
        step(g, IMG.getWidth(), IMG.getHeight());
    }

    public abstract void step(Graphics g, int width, int height);

    @Override
    public void reset() {
        bools = new boolean[IMG.getHeight()][IMG.getWidth()];
    }
}
